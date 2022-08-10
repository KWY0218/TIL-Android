package com.study.til.flow

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.zip

suspend fun main() {
    val a = flowOf("A", "B", "C", "D").onEach { delay(100) }
    val b = flowOf(1, 2, 3, 4).onEach { delay(200) }

    a.combine(b) { a1, b1 -> "combine2 : $a1$b1" }
        .collect { result -> println(result) }

    combine(b, b, b) { b1, b2, b3 -> "combine3 $b1 $b2 $b3" }
        .collect { result -> println(result) }

    combine(b, b, b, b) { b1, b2, b3, b4 -> "combine4 $b1 $b2 $b3 $b4" }
        .collect { result -> println(result) }

    combine(b, b, b, b, b) { b1, b2, b3, b4, b5 -> "combine5 $b1 $b2 $b3 $b4 $b5" }
        .collect { result -> println(result) }

    b.combine(b) { b1, b2 -> "$b1$b2" }
        .combine(a) { b12, a1 -> "$b12$a1" }
        .collect { result -> println(result) }

    a.zip(b) { a1, b1 -> "zip : $a1$b1" }.collect { result -> println(result) }
}
