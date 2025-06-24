package com.sc7258.batchmultidb.config

import com.sc7258.batchmultidb.entity.SampleEntity
import com.sc7258.batchmultidb.repository.SampleRepository
import org.springframework.batch.core.Job
import org.springframework.batch.core.Step
import org.springframework.batch.core.job.builder.JobBuilder
import org.springframework.batch.core.repository.JobRepository
import org.springframework.batch.core.step.builder.StepBuilder
import org.springframework.batch.repeat.RepeatStatus
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.transaction.PlatformTransactionManager
import org.springframework.transaction.support.TransactionTemplate
import javax.sql.DataSource

@Configuration
class BatchJobConfig(
    private val jobRepository: JobRepository,
    private val sampleRepository: SampleRepository,
    @Qualifier("batchTransactionManager") private val batchTransactionManager: PlatformTransactionManager,
    @Qualifier("appJpaTransactionManager") private val appTransactionManager: PlatformTransactionManager
) {

    @Bean
    fun batchStep(): Step =
        StepBuilder("batchStep", jobRepository)
            .tasklet({ _, _ ->
                println("Executing batch step")
                // This step only performs operations on the batch database
                RepeatStatus.FINISHED
            }, batchTransactionManager)
            .build()

    @Bean
    fun appStep(): Step =
        StepBuilder("appStep", jobRepository)
            .tasklet({ _, _ ->
                println("Executing app step using JPA repository")
                // This step uses the app transaction manager for JPA operations
                sampleRepository.save(SampleEntity(name = "TestUser"))
                RepeatStatus.FINISHED
            }, appTransactionManager)
            .build()

    @Bean
    fun sampleJob(batchStep: Step, appStep: Step): Job =
        JobBuilder("sampleJob", jobRepository)
            .start(batchStep)
            .next(appStep)
            .build()
}
