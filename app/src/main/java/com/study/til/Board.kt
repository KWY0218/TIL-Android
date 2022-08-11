package com.study.til

data class Board(
    val id: Int,
    val title: String,
    val content: String,
    val writer: String,
    val writeDate: String,
    val time: String = "로딩"
)
