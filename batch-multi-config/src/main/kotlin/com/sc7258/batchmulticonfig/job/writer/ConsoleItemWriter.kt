package com.sc7258.batchmulticonfig.job.writer

import org.springframework.batch.item.Chunk
import org.springframework.batch.item.ItemWriter


class ConsoleItemWriter : ItemWriter<String?> {
//    public override fun write(items: MutableList<out String?>) {
//        for (item in items) {
//            println(">>> 처리된 항목: " + item)
//        }
//    }

    override fun write(chunk: Chunk<out String?>) {
        for (item in chunk) {
            println(">>> 처리된 항목: " + item)
        }
    }
}