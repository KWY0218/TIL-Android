package com.study.til

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.study.til.databinding.ActivityMainBinding
import com.study.til.flow.TAG
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView<ActivityMainBinding?>(this, R.layout.activity_main)
            .apply {
                vm = mainViewModel
            }
        test1()
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    mainViewModel.checkBox1State.collect { state1 ->
                        binding.checkbox1.isChecked = state1
                    }
                }
                launch {
                    mainViewModel.checkBox2State.collect { state2 ->
                        binding.checkbox2.isChecked = state2
                    }
                }
                launch {
                    mainViewModel.checkBox3State.collect { state3 ->
                        binding.checkbox3.isChecked = state3
                    }
                }
                launch {
                    mainViewModel.rating
                        .debounce(1000)
                        .collect {
                            mainViewModel.sendCheckBoxState()
                        }
                }
                launch {
                    mainViewModel.rating
                        .collect { rate ->
                            Log.d("MainActivity", "main $rate")
                        }
                }
            }
        }
        checkBoxOnClick()
    }

    private fun checkBoxOnClick() {
        with(binding) {
            checkbox1.setOnClickListener { mainViewModel.setCheckBox(1) }
            checkbox2.setOnClickListener { mainViewModel.setCheckBox(2) }
            checkbox3.setOnClickListener { mainViewModel.setCheckBox(3) }
        }
    }

    private fun test1() {
        val myFlow = flow {
            repeat(100) {
                Log.d(TAG, ">> emit -> $it")
                emit(it)
                delay(1000)
            }
        }

        val myStateFlow = myFlow.stateIn(
            scope = CoroutineScope(Dispatchers.Main),
            started = SharingStarted.Eagerly,
            initialValue = -1
        )

        CoroutineScope(Dispatchers.Main).launch {
            delay(5000)
            val job1 = launch(Dispatchers.IO) {
                myStateFlow.collect { Log.d(TAG, "#1st -> $it") }
            }
            delay(3000)
            val job2 = launch(Dispatchers.IO) {
                myStateFlow.collect { Log.d(TAG, "#2nd -> $it") }
            }

            delay(5000)
            job1.cancel()
            job2.cancel()

            delay(3000)
            val job3 = launch {
                myStateFlow.collect { Log.d(TAG, "#3rd -> $it") }
            }
        }
    }
}
