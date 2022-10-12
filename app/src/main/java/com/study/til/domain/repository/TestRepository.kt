package com.study.til.domain.repository

interface TestRepository {
    suspend fun getStringItems(): List<String>
    suspend fun getIntItems(): List<Int>
    suspend fun getDoubleItems(): List<Double>
}
