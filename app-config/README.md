# App Config Module

This module demonstrates Spring Boot configuration management with different configuration approaches and profiles.

## Features

- Configuration using `application.yml`
- Profile-specific configurations (default, dev, prod)
- Configuration properties binding
- Environment-specific configuration

## Build Instructions

To build the app-config module, run one of the following commands:

```bash
# From the project root
# For Linux/macOS
./gradlew :app-config:build
# For Windows
.\gradlew.bat :app-config:build

# Or from the app-config directory
# For Linux/macOS
../gradlew build
# For Windows
..\gradlew.bat build
```

## Run Instructions

The app-config module can be run with different profiles to demonstrate configuration switching:

```bash
# From the project root
# For Linux/macOS
# Run with default profile
./gradlew :app-config:bootRun
# Run with dev profile
./gradlew :app-config:bootRun --args='--spring.profiles.active=dev'
# Run with prod profile
./gradlew :app-config:bootRun --args='--spring.profiles.active=prod'

# For Windows
# Run with default profile
.\gradlew.bat :app-config:bootRun
# Run with dev profile
.\gradlew.bat :app-config:bootRun --args="--spring.profiles.active=dev"
# Run with prod profile
.\gradlew.bat :app-config:bootRun --args="--spring.profiles.active=prod"
```

## Testing

To run tests for the app-config module:

```bash
# From the project root
# For Linux/macOS
./gradlew :app-config:test
# For Windows
.\gradlew.bat :app-config:test

# Or from the app-config directory
# For Linux/macOS
../gradlew test
# For Windows
..\gradlew.bat test
```

## Configuration Details

The application uses the following configuration properties:

- `app.name`: The name of the application
- `app.timeout`: Timeout value in seconds

These properties have different values depending on the active profile:

| Profile | app.name | app.timeout |
|---------|----------|-------------|
| default | MyKotlinApp | 30 |
| dev | MyKotlinApp-Dev | 60 |
| prod | MyKotlinApp-Prod | 120 |

## Reference Documentation

For further reference, please consider the following sections:

* [Official Gradle documentation](https://docs.gradle.org)
* [Spring Boot Gradle Plugin Reference Guide](https://docs.spring.io/spring-boot/3.3.12/gradle-plugin)
* [Spring Boot Configuration Properties](https://docs.spring.io/spring-boot/docs/current/reference/html/features.html#features.external-config)