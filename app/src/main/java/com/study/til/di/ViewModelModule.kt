package com.study.til.di

import com.study.til.MainViewModel
import com.study.til.domain.repository.TestRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ViewModelModule {
    @Provides
    @Singleton
    fun provideMainViewModel(testRepository: TestRepository): MainViewModel = MainViewModel(
        getDoubleItemsUseCase = testRepository::getDoubleItems,
        getIntItemsUseCase = testRepository::getIntItems,
        getStringItemsUseCase = testRepository::getStringItems
    )
}
