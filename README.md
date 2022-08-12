# 시간, 분 비교하는 UseCase

[UseCase 로직](https://github.com/KWY0218/TIL/blob/calculate-date-usecase/app/src/main/java/com/study/til/usecase/CalculateDateUseCase.kt)
``` kotlin
class CalculateDateUseCase @Inject constructor() {
    suspend operator fun invoke(writeDate: String): Flow<Int> = flow {
        val curDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
        val (_, _, curDay, curHour, curMin) = curDate.split("-", " ", ":")
        val (writeYear, writeMonth, writeDay, writeHour, writeMin) = writeDate.split("-", " ", ":")

        val curDateSec =
            curDay.toInt() * ONE_DAY + curHour.toInt() * ONE_HOUR + curMin.toInt() * ONE_MIN
        val writeDateSec =
            writeDay.toInt() * ONE_DAY + writeHour.toInt() * ONE_HOUR + writeMin.toInt() * ONE_MIN

        val diffDate = curDateSec - writeDateSec

        if (diffDate < ONE_HOUR) emit(Time.Min(diffDate / ONE_MIN))
        else if (diffDate < ONE_DAY) emit(Time.Hour(diffDate / ONE_HOUR))
        else emit(Time.Day(writeYear.toInt(), writeMonth.toInt(), writeDay.toInt()))
    }

    companion object {
        const val ONE_DAY = 86_400
        const val ONE_HOUR = 3_600
        const val ONE_MIN = 60
    }
}

sealed class Time {
    data class Min(val text: Int) : Time()
    data class Hour(val text: Int) : Time()
    data class Day(val writeYear: Int, val writeMonth: Int, val writeDay: Int) : Time()
}
```

[UseCase 활용 로직](https://github.com/KWY0218/TIL/blob/calculate-date-usecase/app/src/main/java/com/study/til/MainViewModel.kt)
``` kotlin
val tempBoardList = mutableListOf<Board>()
boardList.value.forEach { board ->
    var tempBoard = Board()
    calculateDateUseCase(board.writeDate).collect { time ->
        tempBoard = when (time) {
            is Time.Min -> board.copy(time = "${time.text}분 전")
            is Time.Hour -> board.copy(time = "${time.text}시간 전")
            is Time.Day -> board.copy(time = "${time.writeYear}년 ${time.writeMonth}월 ${time.writeDay}일")
        }
    }
    tempBoardList.add(tempBoard)
}
_boardList.value = tempBoardList
```

## 결과
![KakaoTalk_20220811_221337686](https://user-images.githubusercontent.com/82709044/184410609-c48e237d-fd0e-4252-a988-45faaa446e83.jpg)
