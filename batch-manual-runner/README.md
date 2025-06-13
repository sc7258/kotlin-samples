# Batch Manual Runner

This module demonstrates how to configure and manually run Spring Batch jobs in a Spring Boot application.

## Features

- Definition of multiple batch jobs and steps using Kotlin
- Manual job execution control
- Configuration of job execution parameters
- Tasklet-based step implementation
- Database configuration options (MariaDB and H2)

## Implementation Details

The module contains:

- Two batch jobs (`jobA` and `jobB`), each with a simple tasklet step
- Configuration for job execution control
- Commented code showing how to implement an ApplicationRunner for manual job execution

## Configuration

The application is configured with:

```yaml
spring:
  application:
    name: batch-manual-runner

  batch:
    job:
      enabled: true   # Controls automatic job execution at startup
      name: jobA      # Default job name to run
    jdbc:
      initialize-schema: always   # For development/testing purposes

  datasource:
    # MariaDB configuration
    url: jdbc:mariadb://localhost:3306/BATCHDB?serverTimezone=Asia/Seoul&useUnicode=true&characterEncoding=UTF-8
    username: orcmes
    password: P@ssw0rd
    hikari:
      maximum-pool-size: 10   # Adjust based on concurrent step count

    # H2 configuration (commented out by default)
    # url: jdbc:h2:file:./data/batchdb;AUTO_SERVER=TRUE
    # driver-class-name: org.h2.Driver
    # username: sa
    # password:
```

## Database Options

The application supports two database options:

1. **MariaDB** (default): Requires a running MariaDB instance with the specified credentials
2. **H2 Database** (commented out): Can be enabled for development/testing without external dependencies

To switch to H2, comment out the MariaDB configuration and uncomment the H2 configuration in `application.yml`.

## Usage

To run a specific job manually, you can:

1. Uncomment the `runBothJobs` method in `BatchConfig.kt`
2. Modify the job execution parameters as needed
3. Start the application

Alternatively, you can use Spring Boot's job runner capabilities by specifying the job name as a command-line parameter:

```
./gradlew bootRun --args='--spring.batch.job.names=jobA'
```

Or to run a different job:

```
./gradlew bootRun --args='--spring.batch.job.names=jobB'
```

## Prerequisites

- JDK 17 or higher
- Gradle 8.0 or higher
- MariaDB (if using the default configuration)
