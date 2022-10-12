package com.study.til.data

import com.study.til.domain.repository.TestRepository
import javax.inject.Inject

class TestRepositoryImpl @Inject constructor() : TestRepository {
    override suspend fun getStringItems(): List<String> {
        return listOf("run")
    }

    override suspend fun getIntItems(): List<Int> {
        return listOf(1)
    }

    override suspend fun getDoubleItems(): List<Double> {
        return listOf(1.0)
    }
}
