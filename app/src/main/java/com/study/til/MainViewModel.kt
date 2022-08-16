package com.study.til

import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainViewModel : ViewModel() {
    private val _rules = MutableStateFlow(
        listOf(
            Rule(id = 1, ruleText = "규칙1"),
            Rule(id = 2, ruleText = "규칙2"),
            Rule(id = 3, ruleText = "규칙3"),
            Rule(id = 4, ruleText = "규칙4"),
            Rule(id = 5, ruleText = "규칙5"),
            Rule(id = 6, ruleText = "규칙6"),
            Rule(id = 7, ruleText = "규칙7"),
            Rule(id = 8, ruleText = "규칙8")
        )
    )

    val rules = _rules.asStateFlow()

    fun setIsChecked(id: Int) {
        val tempList = _rules.value.toMutableList()
        tempList[id - 1] = tempList[id - 1].copy(isChecked = !tempList[id - 1].isChecked)
        Log.d("sdfhkjsdf", "$tempList")
        _rules.value = tempList
    }
}

data class Rule(
    val id: Int,
    val isChecked: Boolean = false,
    val ruleText: String
)
