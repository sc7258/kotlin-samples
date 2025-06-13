package com.sc7258.batchmulticonfig.job.processor

import org.springframework.batch.item.ItemProcessor
import java.util.*


class UpperCaseProcessor : ItemProcessor<String?, String?> {

//    override fun process(item: String?): String? {
//        if (item == null) {
//            return null
//        }
//        return item.uppercase(Locale.getDefault()) // 소문자를 대문자로 변환
//    }

    override fun process(item: String): String? {
        if (item == null) {
            return null
        }
        return item.uppercase(Locale.getDefault()) // 소문자를 대문자로 변환
    }
}