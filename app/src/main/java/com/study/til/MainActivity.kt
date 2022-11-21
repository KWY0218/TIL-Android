package com.study.til

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.study.til.databinding.ActivityMainBinding
import kotlinx.coroutines.delay

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val a = binding.test.text

        binding.btn.setOnClickListener {
            Log.d("sfkhsalhf", "text $a")
        }
    }

    private fun test1() {
        Log.d("taggg", "test1 start")
        Thread.sleep(8000)
        Log.d("taggg", "test1 finish")
    }

    private suspend fun test2() {
        Log.d("taggg", "test2 start")
        delay(6000)
        Log.d("taggg", "test2 finish")
    }

    private suspend fun test3() {
        Log.d("taggg", "test3 start")
        delay(1000)
        Log.d("taggg", "test3 finish")
    }
}
