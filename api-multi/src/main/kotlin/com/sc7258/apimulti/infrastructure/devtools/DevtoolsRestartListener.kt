//package com.sc7258.apimulti.infrastructure.devtools
//
//import org.springframework.boot.devtools.restart.RestartListener
//import org.springframework.stereotype.Component
//import org.slf4j.LoggerFactory
//
//@Component
//class DevtoolsRestartListener : RestartListener {
//
//    companion object {
//        private val log = LoggerFactory.getLogger(DevtoolsRestartListener::class.java)
//    }
//
//    /**
//     * DevTools가 변경 감지를 통해 "컨텍스트 재시작"을 트리거하기 직전에 호출됩니다.
//     */
//    override fun beforeRestart() {
//        log.info(">>> DevTools가 애플리케이션을 재시작합니다")
//        // 또는 println(">>> DevTools 재시작") 해 보시고
//    }
//}