package com.study.til

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.study.til.usecase.CalculateDateUseCase
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
            calculateDateUseCase(board.writeDate).collect { timeText ->
                tempBoard = board.copy(time = timeText)
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
                writeDate = "2022-08-11 21-55-22",
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
