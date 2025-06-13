package com.sc7258.batchmulticonfig.job.reader

import org.springframework.batch.item.ItemReader


class SampleItemReader : ItemReader<String?> {
    private val dataIterator: MutableIterator<String?>

    init {
        // 예시 데이터: 실제로는 DB 조회, 파일 읽기 등으로 대체
        val data = mutableListOf<String?>("apple", "banana", "cherry", "date", "elderberry", "fig", "grape")
        this.dataIterator = data.iterator()
    }

    override fun read(): String? {
        if (dataIterator.hasNext()) {
            return dataIterator.next()
        }
        return null // null 반환 시 청크 반복 종료
    }
}