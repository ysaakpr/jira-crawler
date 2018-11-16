**Instructions**
* Clone the repository
* Move to cloned directory
* Configure the Environment Variables 
* Run ./gradlew test, which runs the test
* Tow test are  added, one to check the events are generated agains the descriptive name, and another for checking
calling the api send the event in the sqs. Ensure the tests are running without any message in the queue.
* Run ./gradlew bootRun to run the application


**Note**
- Built using Spring Boot 2
- AWS SQS SDK Have been used with Endpoint configuration and random secret and access keys.
- In case to modifi them Please edit in BeanConfig class;
- QUEUE Url must be in the format http://localhost:9324/queue/cst-test-queue
- Used the Queue url as the endpoint url for SQS Endpoint configuration also.
- Provided MountBank imposters/jira-api.ejs config wont work unless you remove the query params from the path
- Jira Api doesnt provide paginated response, so watever the size of data that is returned by the server will be kept in
memory. Which may cause a memory overflow in case the size of response is too large.
 
