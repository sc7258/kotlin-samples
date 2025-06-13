# Spring Batch Tasklet Sample

This module demonstrates how to implement tasklet-based batch processing in Spring Batch using Kotlin.

## Overview

Spring Batch tasklets provide a simple way to execute a single task within a batch step. Unlike chunk-oriented processing, tasklets are designed for tasks that:
- Perform a single unit of work
- Don't fit the read-process-write pattern
- Need to be executed as a single transaction

This module includes examples of tasklet implementation and configuration.

## Prerequisites

- JDK 17 or higher
- Gradle 8.0 or higher

## Key Components

This module demonstrates:
- Spring Boot 3.3.12
- Spring Batch
- H2 Database for job repository
- Multiple tasklet implementations
- Job and step configuration

### Implemented Tasklets

- **SimpleTasklet**: A basic tasklet implementation
- **Simple2Tasklet**: Another tasklet implementation demonstrating different functionality

### Configuration

- **SimpleTaskletConfig**: Configuration for the SimpleTasklet job
- **Simple2TaskletConfig**: Configuration for the Simple2Tasklet job

## Building and Running

To build the project:
```bash
./gradlew build
```

To run the application:
```bash
./gradlew bootRun
```

## Implementation Notes

To implement your own tasklet:

1. Create a class that implements the `Tasklet` interface
2. Implement the `execute` method to perform your task
3. Configure a job with a step that uses your tasklet

## References

- [Spring Batch Documentation](https://docs.spring.io/spring-batch/docs/current/reference/html/)
- [Spring Batch Tasklet Processing](https://docs.spring.io/spring-batch/docs/current/reference/html/step.html#taskletStep)