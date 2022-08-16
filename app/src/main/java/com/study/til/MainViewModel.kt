package com.study.til

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

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

    val progressRating: StateFlow<Float> = rules.map { rules ->
        var rating = 0.0f
        rules.forEach { rule -> if (rule.isChecked) rating += (1.0 / _rules.value.size).toFloat() }
        rating
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(3000L), 0.0f)

    fun setIsChecked(id: Int) {
        val tempList = _rules.value.toMutableList()
        tempList[id - 1] = tempList[id - 1].copy(isChecked = !tempList[id - 1].isChecked)
        _rules.value = tempList
    }
}

data class Rule(
    val id: Int,
    val isChecked: Boolean = false,
    val ruleText: String
)
