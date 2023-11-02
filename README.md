# bulletin

# Getting Started

### Reference Documentation
For further reference, please consider the following sections:

* [Official Gradle documentation](https://docs.gradle.org)
* [Spring Boot Gradle Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/3.1.4/gradle-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/3.1.4/gradle-plugin/reference/html/#build-image)
* [Quartz Scheduler](https://docs.spring.io/spring-boot/docs/3.1.4/reference/htmlsingle/index.html#io.quartz)
* [Spring for Apache Pulsar (Reactive)](https://docs.spring.io/spring-boot/docs/3.1.4/reference/htmlsingle/index.html#messaging.pulsar)
* [Spring for RabbitMQ](https://docs.spring.io/spring-boot/docs/3.1.4/reference/htmlsingle/index.html#messaging.amqp)
* [Resilience4J](https://docs.spring.io/spring-cloud-circuitbreaker/docs/current/reference/html/#configuring-resilience4j-circuit-breakers)
* [Spring Web](https://docs.spring.io/spring-boot/docs/3.1.4/reference/htmlsingle/index.html#web)
* [Spring Data JDBC](https://docs.spring.io/spring-boot/docs/3.1.4/reference/htmlsingle/index.html#data.sql.jdbc)

### Guides
The following guides illustrate how to use some features concretely:

* [Messaging with RabbitMQ](https://spring.io/guides/gs/messaging-rabbitmq/)
* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)
* [Using Spring Data JDBC](https://github.com/spring-projects/spring-data-examples/tree/master/jdbc/basics)

### Additional Links
These additional references should also help you:

* [Gradle Build Scans – insights for your project's build](https://scans.gradle.com#gradle)


### Instructions
Requirements

Docker:
Java 17


Run a rabbit mq exhange

docker pull rabbitmq:3-management

docker run -d -p 5672:5672 -p 15672:15672 --name my-rabbit rabbitmq:3-management

run the app
java -jar build.jar.


BetPublisher

this service simulates a bet booking houses updating the game odds
it updates odds every 5 seconds
this service uses an AMQP exchange to publish new odds for a game. Upon a failure on the exchange it will fallback to a legacy Rest service to update the odds via POST
This fallback is done using resillience4j circuitBreaker

BetBulletinService

This service allow for the query of games, booking bet odds and updating booker bet odds for a game.

This service allows for the publishing of new Odds via AMQP or Rest.

To test this stop the docker container and you will see trying to post via rest instead.
If you deploy the container again, it resumes to send via amqp
