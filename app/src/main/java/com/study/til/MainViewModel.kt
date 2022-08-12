package com.study.til

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.study.til.usecase.CalculateDateUseCase
import com.study.til.usecase.Time
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val calculateDateUseCase: CalculateDateUseCase
) : ViewModel() {
    private val _boardList = MutableStateFlow<List<Board>>(emptyList())
    val boardList = _boardList.asStateFlow()

    init {
        viewModelScope.launch {
            _boardList.value = fetchBoardList()
            refreshTime()
        }
    }

    private suspend fun refreshTime() {
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
    }

    private fun fetchBoardList(): List<Board> =
        listOf(
            Board(
                id = 1,
                writer = "맑은비",
                title = "몽실 베이커리",
                writeDate = "2022-08-12 00-55-22",
                content = "첫번째 케이스"
            ),
            Board(
                id = 2,
                writer = "맑은비",
                title = "몽실 베이커리",
                writeDate = "2022-08-11 15-20-22",
                content = "두번째 케이스"
            ),
            Board(
                id = 3,
                writer = "맑은비",
                title = "몽실 베이커리",
                writeDate = "2022-08-10 19-20-22",
                content = "세번째 케이스"
            )
        )
}
