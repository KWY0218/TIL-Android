package com.study.til

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn

class MainViewModel : ViewModel() {
    private val _checkBox1State = MutableStateFlow(false)
    private val _checkBox2State = MutableStateFlow(false)
    private val _checkBox3State = MutableStateFlow(false)
    val checkBox1State = _checkBox1State.asStateFlow()
    val checkBox2State = _checkBox2State.asStateFlow()
    val checkBox3State = _checkBox3State.asStateFlow()
    val rating = combine(checkBox1State, checkBox2State, checkBox3State) { c1, c2, c3 ->
        var temp = 0
        if (c1) temp += 33
        if (c2) temp += 33
        if (c3) temp += 33
        temp
    }.stateIn(
        scope = viewModelScope,
        initialValue = 0,
        started = SharingStarted.WhileSubscribed(3000L)
    )

    fun setCheckBox(key: Int) {
        when (key) {
            1 -> _checkBox1State.value = !_checkBox1State.value
            2 -> _checkBox2State.value = !_checkBox2State.value
            3 -> _checkBox3State.value = !_checkBox3State.value
        }
    }
}
