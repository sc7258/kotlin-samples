package com.sc7258.batchmulticonfig.config

import com.sc7258.batchmulticonfig.job.processor.UpperCaseProcessor
import com.sc7258.batchmulticonfig.job.reader.SampleItemReader
import com.sc7258.batchmulticonfig.job.writer.ConsoleItemWriter
import org.springframework.batch.core.Job
import org.springframework.batch.core.Step
import org.springframework.batch.core.job.builder.JobBuilder
import org.springframework.batch.core.repository.JobRepository
import org.springframework.batch.core.step.builder.StepBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.transaction.PlatformTransactionManager

@Configuration
class JobAConfig(
    private val jobRepository: JobRepository,
    private val transactionManager: PlatformTransactionManager,
) {
    @Bean
    fun jobA(): Job {
        val stepA: Step = StepBuilder("stepA", jobRepository)
            .chunk<String?, String?>(5, transactionManager)
            .reader(SampleItemReader()) // SampleItemReader 빈 대신 직접 new;
            .processor(UpperCaseProcessor()) // 마찬가지로 new
            .writer(ConsoleItemWriter()) // new
            .build()

        return JobBuilder("jobA", jobRepository)
            .start(stepA)
            .build()
    }
}