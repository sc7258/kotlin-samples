package com.sc7258.batchtasklet

import mu.KotlinLogging
import org.springframework.batch.core.StepContribution
import org.springframework.batch.core.scope.context.ChunkContext
import org.springframework.batch.core.step.tasklet.Tasklet
import org.springframework.batch.repeat.RepeatStatus
import kotlin.math.log

class Simple2Tasklet : Tasklet {
    private val logger = KotlinLogging.logger {}

    override fun execute(
        contribution: StepContribution,
        chunkContext: ChunkContext,
    ): RepeatStatus? {
        logger.info("Simple2Tasklet:: Tasklet is executing! Processing some data...")

        // Job 매개변수 읽기
        val param = chunkContext.stepContext.jobParameters["param"]?.toString()
        logger.info("Simple2Tasklet:: Job Parameter: $param")

        return RepeatStatus.FINISHED
    }
}