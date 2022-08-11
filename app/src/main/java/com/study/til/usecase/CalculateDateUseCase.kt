package com.study.til.usecase

import kotlinx.coroutines.flow.flow
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

class CalculateDateUseCase @Inject constructor() {
    suspend operator fun invoke(writeDate: String) = flow {
        val curDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
        val (_, _, curDay, curHour, curMin) = curDate.split("-", " ", ":")
        val (writeYear, writeMonth, writeDay, writeHour, writeMin) = writeDate.split("-", " ", ":")

        val curDateSec =
            curDay.toInt() * ONE_DAY + curHour.toInt() * ONE_HOUR + curMin.toInt() * ONE_MIN
        val writeDateSec =
            writeDay.toInt() * ONE_DAY + writeHour.toInt() * ONE_HOUR + writeMin.toInt() * ONE_MIN

        val diffDate = curDateSec - writeDateSec

        if (diffDate < ONE_HOUR) emit("${diffDate / ONE_MIN}분 전")
        else if (diffDate < ONE_DAY) emit("${diffDate / ONE_HOUR}시간 전")
        else emit("$writeYear 년 $writeMonth 월 $writeDay 일")
    }

    companion object {
        const val ONE_DAY = 86_400
        const val ONE_HOUR = 3_600
        const val ONE_MIN = 60
    }
}
