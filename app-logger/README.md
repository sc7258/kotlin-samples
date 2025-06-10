# App Logger Module

This module demonstrates logging in a Spring Boot application using various logging frameworks and configurations.

## Features

- Integration with SLF4J and Logback (Spring Boot's default logging framework)
- Different log levels (INFO, DEBUG, ERROR, etc.)
- Customizable log formats
- Console and file appenders

## Build Instructions

To build the app-logger module, run one of the following commands:

```bash
# From the project root
# For Linux/macOS
./gradlew :app-logger:build
# For Windows
.\gradlew.bat :app-logger:build

# Or from the app-logger directory
# For Linux/macOS
../gradlew build
# For Windows
..\gradlew.bat build
```

## Run Instructions

To run the app-logger module:

```bash
# From the project root
# For Linux/macOS
./gradlew :app-logger:bootRun
# For Windows
.\gradlew.bat :app-logger:bootRun
```

## Testing

To run tests for the app-logger module:

```bash
# From the project root
# For Linux/macOS
./gradlew :app-logger:test
# For Windows
.\gradlew.bat :app-logger:test

# Or from the app-logger directory
# For Linux/macOS
../gradlew test
# For Windows
..\gradlew.bat test
```

## Logging Configuration

The application uses Spring Boot's default logging configuration, which can be customized in the following ways:

- Using `application.properties` or `application.yml` to set logging levels and other properties
- Creating a `logback-spring.xml` file for more advanced configurations
- Setting environment variables or system properties

### Example Configuration

Here's an example of logging configuration in `application.yml`:

```yaml
logging:
  level:
    root: INFO
    com.sc7258.applogger: DEBUG
    org.springframework: WARN
  file:
    name: logs/app-logger.log
  pattern:
    console: "%d{yyyy-MM-dd HH:mm:ss} [%thread] %-5level %logger{36} - %msg%n"
    file: "%d{yyyy-MM-dd HH:mm:ss} [%thread] %-5level %logger{36} - %msg%n"
```

## Reference Documentation

For further reference, please consider the following sections:

* [Official Gradle documentation](https://docs.gradle.org)
* [Spring Boot Gradle Plugin Reference Guide](https://docs.spring.io/spring-boot/3.3.12/gradle-plugin)
* [Spring Boot Logging](https://docs.spring.io/spring-boot/docs/current/reference/html/features.html#features.logging)
* [SLF4J](http://www.slf4j.org/)
* [Logback](http://logback.qos.ch/)