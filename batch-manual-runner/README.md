# Batch Manual Runner

This module demonstrates how to configure and manually run Spring Batch jobs in a Spring Boot application.

## Features

- Definition of multiple batch jobs and steps using Kotlin
- Manual job execution control
- Configuration of job execution parameters
- Tasklet-based step implementation

## Implementation Details

The module contains:

- Two batch jobs (`jobA` and `jobB`), each with a simple tasklet step
- Configuration to disable automatic job execution at startup
- Commented code showing how to implement an ApplicationRunner for manual job execution

## Configuration

The application is configured with:

```yaml
spring:
  batch:
    job:
      enabled: true   # Set to false to disable automatic execution
      name: jobA      # Default job name to run
```

## Usage

To run a specific job manually, you can:

1. Uncomment the `runBothJobs` method in `BatchConfig.kt`
2. Modify the job execution parameters as needed
3. Start the application

Alternatively, you can use Spring Boot's job runner capabilities by specifying the job name as a command-line parameter:

```
./gradlew bootRun --args='--spring.batch.job.names=jobA'
```

## Prerequisites

- JDK 17 or higher
- Gradle 8.0 or higher