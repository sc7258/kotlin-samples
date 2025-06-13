package com.sc7258.batchmanualrunner

import org.springframework.batch.core.Job
import org.springframework.batch.core.JobParametersBuilder
import org.springframework.batch.core.Step
import org.springframework.batch.core.StepContribution
import org.springframework.batch.core.job.builder.JobBuilder
import org.springframework.batch.core.launch.JobLauncher
import org.springframework.batch.core.repository.JobRepository
import org.springframework.batch.core.scope.context.ChunkContext
import org.springframework.batch.core.step.builder.StepBuilder
import org.springframework.batch.core.step.tasklet.Tasklet
import org.springframework.batch.repeat.RepeatStatus
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.transaction.PlatformTransactionManager


@Configuration
class BatchConfig {
    /* ---------- STEP 정의 ---------- */
    @Bean
    fun step1(
        jobRepository: JobRepository,
        txManager: PlatformTransactionManager,
    ): Step? {
        return StepBuilder("step1", jobRepository)
            .tasklet(Tasklet { contribution: StepContribution?, chunkCtx: ChunkContext? ->
                println(">> step1 executed")
                RepeatStatus.FINISHED
            }, txManager)
            .build()
    }

    @Bean
    fun step2(
        jobRepository: JobRepository,
        txManager: PlatformTransactionManager,
    ): Step? {
        return StepBuilder("step2", jobRepository)
            .tasklet(Tasklet { contribution: StepContribution?, chunkCtx: ChunkContext? ->
                println(">> step2 executed")
                RepeatStatus.FINISHED
            }, txManager)
            .build()
    }

    /* ---------- JOB 정의 ---------- */
    @Bean
    fun jobA(jobRepository: JobRepository, step1: Step): Job {
        return JobBuilder("jobA", jobRepository)
            .start(step1)
            .build()
    }

    @Bean
    fun jobB(jobRepository: JobRepository, step2: Step): Job {
        return JobBuilder("jobB", jobRepository)
            .start(step2)
            .build()
    }

//    /* ---------- 다중 Job 실행 ---------- */
//    @Bean
//    fun runBothJobs(
//        jobLauncher: JobLauncher,
//        jobA: Job,
//        jobB: Job,
//    ): ApplicationRunner {
//        return ApplicationRunner {
//            val params = JobParametersBuilder()
//                .addLong("run.id", System.currentTimeMillis())
//                .toJobParameters()
//
//            jobLauncher.run(jobA, params) // 첫 번째 Job
//            jobLauncher.run(jobB, params) // 두 번째 Job
//        }
//    }
}