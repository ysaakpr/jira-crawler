**Instructions**
* Clone the repository
* Move to cloned directory
* Configure the Environment Variables 
* Run ./gradlew test, which runs the test
* Run ./gradlew bootRun to run the application


**Note**
- Built using Spring Boot 2
- AWS SQS SDK Have been used with Endpoint configuration and random secret and access keys.
- In case to modifi them Please edit in BeanConfig class;
- QUEUE Url must be in the format http://localhost:9324/queue/cst-test-queue
- Provided MountBank imposters/jira-api.ejs config wont work unless you remove the query params from the path
 
