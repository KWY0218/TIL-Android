package com.study.til.flow

import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

fun main() {
    val myFlow = flow {
        // collect가 호출되기 전까지
        // 해당 코드는 실행되지 않음

        // 0부터 100까지 1초 간격으로 값을 방출(emit)
        repeat(100) {
            Log.d(TAG, ">> emit -> $it")
            emit(it)
            delay(1000)
        }
    }

    // Flow를 StateFlow로 변환
    val myStateFlow = myFlow.stateIn(
        scope = CoroutineScope(Dispatchers.Default),
        started = SharingStarted.Lazily,
        initialValue = -1
    )

    CoroutineScope(Dispatchers.Default).launch {
        delay(1000) // 1초 대기

        launch {
            // 가장 최근에 방출된 값부터 collect된다. (= 1)
            myStateFlow.collect { Log.d(TAG, "#1st -> $it") }
        }

        delay(2500) // 2.5초 대기

        launch {
            // 가장 최근에 방출된 값부터 collect된다. (= 3)
            myStateFlow.collect { Log.d(TAG, "#2nd -> $it") }
        }
    }
}

const val TAG = "FLOW_TEST"
