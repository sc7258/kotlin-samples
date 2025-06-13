# Spring Batch Multi-Configuration Sample

This module demonstrates how to configure and run multiple Spring Batch jobs in a single application using Kotlin.

## Overview

In real-world applications, it's common to have multiple batch jobs with different configurations. This module shows how to:
- Define multiple job configurations in a single application
- Configure different job types with their own steps and components
- Manage job execution and scheduling

## Prerequisites

- JDK 17 or higher
- Gradle 8.0 or higher

## Key Components

This module demonstrates:
- Spring Boot 3.3.12
- Spring Batch
- H2 Database for job repository
- Multiple job configurations
- Job runners for execution control

### Job Configurations

- **JobAConfig**: Configuration for Job A
- **JobBConfig**: Configuration for Job B

### Job Components

The module includes various job components:
- Readers, processors, and writers for chunk-oriented processing
- Job runners for controlling job execution

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

When implementing multiple job configurations:

1. Create separate configuration classes for each job
2. Use unique bean names to avoid conflicts
3. Consider using job parameters to control which jobs run
4. Implement job runners or schedulers to manage job execution

## References

- [Spring Batch Documentation](https://docs.spring.io/spring-batch/docs/current/reference/html/)
- [Spring Batch Job Configuration](https://docs.spring.io/spring-batch/docs/current/reference/html/job.html)