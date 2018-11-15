package com.interview.atlassian;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.ReceiveMessageResult;
import com.interview.atlassian.controller.IssueController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AtlassianApplicationTests {

	@Autowired private IssueController controller;
	@Autowired private AmazonSQS queue;
	@Autowired private MockMvc mockMvc;
	@Autowired private Environment env;

	@Test
	public void contexLoads() throws Exception {
		assertThat(controller).isNotNull();
		assertThat(queue).isNotNull();
	}

	@Test
	public void checkMessageWithNameNAME() throws Exception {
		MvcResult mvcResult = this.mockMvc.perform(get("/api/issue/sum?query=test&name=NAME")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(containsString("{\"name\":\"NAME\",\"totalPoints\"")))
				.andReturn();

		ReceiveMessageResult receiveMessageResult = queue.receiveMessage(env.getProperty("queue.url"));
		for(Message message : receiveMessageResult.getMessages()) {
			assertThat(message.getBody()).isEqualTo(mvcResult.getResponse().getContentAsString());
			break;
		}
	}

	@Test
	public void checkMessageWithNameTEST() throws Exception {
		this.mockMvc.perform(get("/api/issue/sum?query=test&name=TEST")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(containsString("{\"name\":\"TEST\",\"totalPoints\"")));
	}

}
