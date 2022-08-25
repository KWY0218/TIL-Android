package com.study.til.flow

import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flow

@OptIn(FlowPreview::class)
suspend fun main() {
    flow {
        emit(1)
        delay(90)
        emit(2)
        delay(90)
        emit(3)
        delay(1010)
        emit(4)
        delay(1010)
        emit(5)
    }.debounce {
        if (it == 2) {
            0L
        } else {
            1000L
        }
    }.collect { println("Q1 $it") }

    flow {
        emit(1)
        delay(90)
        emit(2)
        delay(90)
        emit(3)
        delay(1010)
        emit(4)
        delay(1010)
        emit(5)
    }.debounce(1000)
        .collect { println("Q2 $it") }
}
