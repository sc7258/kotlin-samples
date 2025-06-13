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
class SimpleTaskletConfig {

    @Bean
    fun simpleTasklet(): Tasklet {
        return SimpleTasklet()
    }


    @Bean
    open fun simpleTaskletStep(jobRepository: JobRepository,
                    simpleTasklet: Tasklet,
                    transactionManager: PlatformTransactionManager)
    : Step {
        return StepBuilder("simpleTaskletStep", jobRepository)
            .tasklet(simpleTasklet, transactionManager)
            .build()
    }

    @Bean
    fun simpleTaskletJob(jobRepository: JobRepository, simpleTaskletStep: Step): Job {
        return JobBuilder("simpleTaskletJob", jobRepository)
            .start(simpleTaskletStep)
            .build()
    }

}