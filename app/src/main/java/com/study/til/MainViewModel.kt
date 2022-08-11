package com.study.til

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.study.til.usecase.CalculateDateUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlinx.coroutines.delay

@HiltViewModel
class MainViewModel @Inject constructor(
    private val calculateDateUseCase: CalculateDateUseCase
) : ViewModel() {
    private val _boardList = MutableStateFlow<List<Board>>(emptyList())
    val boardList = _boardList.asStateFlow()

    init {
        viewModelScope.launch {
            fetchBoardList()
                .collect { result ->
//                    val tempResult = mutableListOf<Board>()
//                    result.forEach { board ->
//                        var tempBoard = board.copy()
//                        calculateDateUseCase(board.writeDate).collect {
//                            tempBoard = board.copy(time = it)
//                        }
//                        tempResult.add(tempBoard)
//                    }
                    _boardList.value = result
                }
        }
    }

    private fun fetchBoardList(): Flow<List<Board>> = flow {
        delay(300)
        emit(
            listOf(
                Board(
                    id = 1,
                    writer = "맑은비",
                    title = "몽실 베이커리",
                    writeDate = "2022-08-11 19-30-22",
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
        )
    }
}
