package com.study.til.di

import com.study.til.data.TestRepositoryImpl
import com.study.til.domain.repository.TestRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideTestRepository(impl: TestRepositoryImpl): TestRepository = impl
}
