# poc-customer

## Prerequisites

* [Git](https://git-scm.com)
* [Java 11](https://www.java.com)
* [SpringBoot](https://spring.io/projects/spring-boot)
* [Maven](https://maven.apache.org/)
* [Docker](https://www.docker.com/products/docker-desktop)
* [Kafka](https://kafka.apache.org/)

## Diagrama
![image](https://user-images.githubusercontent.com/82905537/126642075-728a07f5-1471-4be8-af35-8fa68502b740.png)

## Running the application locally ###

There are several ways to run a Spring Boot application on your local machine. One way is to execute the `main` method in the `br.com.digio.platform.customer.Application` class from your IDE.

- Download the zip or clone the Git repository.
- Unzip the zip file (if you downloaded one)
- Open Command Prompt and Change directory (cd) to folder containing pom.xml
- Open Eclipse
  - File -> Import -> Existing Maven Project -> Navigate to the folder where you unzipped the zip
  - Select the project
- Choose the Spring Boot Application file (search for @SpringBootApplication)
- Right Click on the file and Run as Java Application

Alternatively you can use the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) like so:

```shell
mvn spring-boot:run
```


## Authors
See also the list of [contributors](https://github.com/jvnobrega/poc-customer/graphs/contributors) who participated in this project.
