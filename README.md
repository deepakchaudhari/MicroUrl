# MicroURL is a URL-shortener service.

This is the Spring Boot MicroURL application provides a REST API for a URL-shortener service.
Its deployed in the cloud can access with below location

<a href="https://polar-taiga-45768.herokuapp.com/microurl/swagger-ui.html#/">https://polar-taiga-45768.herokuapp.com/microurl/swagger-ui.html#/</a>

## Running MicroUrl on local
```
	git clone https://github.intuit.com/dchaudhari/microurl.git
	cd microurl
	./mvnw spring-boot:run
	
```
## Swagger REST API documentation presented here (after application start):
<a href="http://localhost:9966/microurl/swagger-ui.html">http://localhost:9966/microurl/swagger-ui.html</a>

## Check application status
<a href="http://localhost:9966/microurl/actuator/health">http://localhost:9966/microurl/actuator/health</a>

## Working with MicroURL in Eclipse

### prerequisites
The following items should be installed in your system:
* Java 8
* Maven 3 (http://www.sonatype.com/books/mvnref-book/reference/installation.html)
* git command line tool (https://help.github.com/articles/set-up-git)
* Eclipse with the m2e plugin (m2e is installed by default when using the STS (http://www.springsource.org/sts) distribution of Eclipse)

Note: when m2e is available, there is an m2 icon in Help -> About dialog.
If m2e is not there, just follow the install process here: http://eclipse.org/m2e/download/


### Steps:

1) In the command line
```
git clone https://github.intuit.com/dchaudhari/microurl.git
```
2) Inside Eclipse
```
File -> Import -> Maven -> Existing Maven project
```
## Looking for something in particular?

| Layer | Source |
|--|--|
| Dependency | [pom.xml](pom.xml)|
| REST API controllers | [REST folder](src/main/java/com/intuit/martech/microurl/web/rest) |
| Service | [URLServiceImpl.java](src/main/java/com/intuit/martech/microurl/service/URLServiceImpl.java) |
| Tests | [UserRestControllerTests.java](src/test/java/com/intuit/martech/microurl/web/rest) |

## DB Configuration

Local H2 DB can be access with below url

<a href="http://localhost:9966/microurl/h2-console/">http://localhost:9966/microurl/h2-console/</a>


MicroURL uses three separate profile configuration for DEV, TEST and PROD respectively.

application-dev.properties

application-test.properties

application-prod.properties


Please use spring.profiles.active property to set profile

## Testing

To launch your application's tests, run:

    ./mvnw clean test
