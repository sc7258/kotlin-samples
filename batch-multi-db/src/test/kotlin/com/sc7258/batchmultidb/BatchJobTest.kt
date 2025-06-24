package com.sc7258.batchmultidb

import com.sc7258.batchmultidb.repository.SampleRepository
import org.junit.jupiter.api.Test
import org.springframework.batch.core.Job
import org.springframework.batch.core.JobParametersBuilder
import org.springframework.batch.test.JobLauncherTestUtils
import org.springframework.batch.test.context.SpringBatchTest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@SpringBootTest
@SpringBatchTest
class BatchJobTest {

    @Autowired
    private lateinit var jobLauncherTestUtils: JobLauncherTestUtils

    @Autowired
    private lateinit var sampleRepository: SampleRepository

    @Autowired
    private lateinit var sampleJob: Job

    @Test
    fun testSampleJob() {
        // Set up job launcher test utils
        jobLauncherTestUtils.job = sampleJob

        // Run the job with a unique parameter to avoid job instance already exists exception
        val jobParameters = JobParametersBuilder()
            .addLong("time", System.currentTimeMillis())
            .toJobParameters()

        // Execute the job
        val jobExecution = jobLauncherTestUtils.launchJob(jobParameters)

        // Verify job execution status
        println("[DEBUG_LOG] Job execution status: ${jobExecution.status}")
        println("[DEBUG_LOG] Job execution exit status: ${jobExecution.exitStatus}")

        // Verify that data was saved to the database
        val allEntities = sampleRepository.findAll()
        println("[DEBUG_LOG] Number of entities found: ${allEntities.size}")
        allEntities.forEach { 
            println("[DEBUG_LOG] Entity: id=${it.id}, name=${it.name}")
        }

        // Assert that at least one entity was saved
        assertTrue(allEntities.isNotEmpty(), "No entities were saved to the database")
        
        // Check if there's an entity with name "TestUser"
        val testUser = allEntities.find { it.name == "TestUser" }
        assertTrue(testUser != null, "TestUser entity was not found in the database")
    }
}