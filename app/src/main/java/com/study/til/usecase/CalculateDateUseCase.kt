package com.study.til.usecase

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

class CalculateDateUseCase @Inject constructor() {
    suspend operator fun invoke(writeDate: String) = flow {
        val curDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH-mm-ss"))
        val (_, _, curDay, curHour, curMin) = curDate.split("-", " ")

        val (writeYear, writeMonth, writeDay, writeHour, writeMin) = writeDate.split("-", " ")

        if (curDay.toInt() - writeDay.toInt() == 0 && curHour.toInt() - writeHour.toInt() == 0) {
            while (true) {
                emit("${curMin.toInt() - writeMin.toInt()}")
                if (curHour.toInt() - writeHour.toInt() == 0) break else delay(6000)
            }
        } else if (curDay.toInt() - writeDay.toInt() == 0) {
            while (true) {
                emit("${curHour.toInt() - writeHour.toInt()}")
                if (curHour.toInt() - writeHour.toInt() == 0) break else delay(60000)
            }
        } else emit("$writeYear 년 $writeMonth 월 $writeDay 일")
    }
}
