package com.sc7258.batchtasklet

import org.springframework.batch.core.Job
import org.springframework.batch.core.Step
import org.springframework.batch.core.job.builder.JobBuilder
import org.springframework.batch.core.repository.JobRepository
import org.springframework.batch.core.step.builder.StepBuilder
import org.springframework.batch.core.step.tasklet.Tasklet
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.transaction.PlatformTransactionManager

@Configuration
//@EnableBatchProcessing
class Simple2TaskletConfig {

    @Bean
    fun simple2Tasklet(): Tasklet {
        return Simple2Tasklet()
    }


    @Bean
    open fun simple2TaskletStep(jobRepository: JobRepository,
                    simple2Tasklet: Tasklet,
                    transactionManager: PlatformTransactionManager)
    : Step {
        return StepBuilder("simple2TaskletStep", jobRepository)
            .tasklet(simple2Tasklet, transactionManager)
            .build()
    }

    @Bean
    fun simple2TaskletJob(jobRepository: JobRepository, simple2TaskletStep: Step): Job {
        return JobBuilder("simple2TaskletJob", jobRepository)
            .start(simple2TaskletStep)
            .build()
    }

}