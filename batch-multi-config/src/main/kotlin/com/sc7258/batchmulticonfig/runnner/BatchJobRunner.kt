//package com.sc7258.batchmulticonfig.runnner
//
//import org.springframework.batch.core.Job
//import org.springframework.batch.core.JobParametersBuilder
//import org.springframework.batch.core.launch.JobLauncher
//import org.springframework.boot.CommandLineRunner
//import org.springframework.stereotype.Component
//
//
//@Component
//class BatchJobRunner(private val jobLauncher: JobLauncher, private val jobA: Job, private val jobB: Job) :
//    CommandLineRunner {
//    @Throws(Exception::class)
//    override fun run(vararg args: String?) {
//        // 예: 애플리케이션 기동 시 JobA, JobB 순차 실행
//        // 필요에 따라 args나 환경변수에 따라 선택 실행 로직 추가 가능
//        val params = JobParametersBuilder()
//            .addLong("time", System.currentTimeMillis())
//            .toJobParameters()
//
//        println(">>> BatchJobRunner: JobA 실행")
//        jobLauncher.run(jobA, params)
//
//        // 새로운 파라미터로 재실행(동일 파라미터로 실행 시 이미 완료된 인스턴스라면 실패하므로 time 추가)
//        val params2 = JobParametersBuilder()
//            .addLong("time", System.currentTimeMillis() + 1)
//            .toJobParameters()
//
//        println(">>> BatchJobRunner: JobB 실행")
//        jobLauncher.run(jobB, params2)
//    }
//}