package com.sc7258.batchmultidb

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.runApplication

@SpringBootApplication
//@EnableBatchProcessing //?? 이게 필요한가??
@EntityScan(basePackages = ["com.sc7258.batchmultidb"])
class BatchMultiDbApplication

fun main(args: Array<String>) {
	runApplication<BatchMultiDbApplication>(*args)
}
