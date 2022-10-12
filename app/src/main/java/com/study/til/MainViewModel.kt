package com.study.til

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.study.til.domain.usecase.GetDoubleItemsUseCase
import com.study.til.domain.usecase.GetIntItemsUseCase
import com.study.til.domain.usecase.GetStringItemsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getStringItemsUseCase: GetStringItemsUseCase,
    private val getIntItemsUseCase: GetIntItemsUseCase,
    private val getDoubleItemsUseCase: GetDoubleItemsUseCase
) : ViewModel() {
    fun test() {
        viewModelScope.launch {
            Log.d("testUseCase", "double ${getDoubleItemsUseCase()}")
            Log.d("testUseCase", "int ${getIntItemsUseCase()}")
            Log.d("testUseCase", "string ${getStringItemsUseCase()}")
        }
    }
}
