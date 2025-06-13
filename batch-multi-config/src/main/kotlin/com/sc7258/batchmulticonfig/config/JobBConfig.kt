package com.sc7258.batchmulticonfig.config

import org.springframework.batch.core.Job
import org.springframework.batch.core.Step
import org.springframework.batch.core.StepContribution
import org.springframework.batch.core.job.builder.JobBuilder
import org.springframework.batch.core.repository.JobRepository
import org.springframework.batch.core.scope.context.ChunkContext
import org.springframework.batch.core.step.builder.StepBuilder
import org.springframework.batch.core.step.tasklet.Tasklet
import org.springframework.batch.repeat.RepeatStatus
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.transaction.PlatformTransactionManager


@Configuration
class JobBConfig(
    private val jobRepository: JobRepository,
    private val transactionManager: PlatformTransactionManager,
) {
    @Bean
    fun jobB(): Job {
        val stepB: Step = StepBuilder("stepB", jobRepository)
            .tasklet(Tasklet { contribution: StepContribution?, chunkContext: ChunkContext? ->
                println(">>> Job B의 Tasklet 실행: 간단한 작업 처리")
                RepeatStatus.FINISHED
            }, transactionManager)
            .build()

        return JobBuilder("jobB", jobRepository)
            .start(stepB)
            .build()
    }
}