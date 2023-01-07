package com.study.til

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.study.til.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var adapter: ChatAdapter
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        initAdapter()
    }

    private fun initAdapter() {
        adapter = ChatAdapter()
        binding.recyclerview.adapter = adapter
        adapter.submitList(dummy)
    }

    companion object {
        val dummy = listOf(
            Chat(
                text = "kwy",
                isPerson = Person.ME
            ),
            Chat(
                text = "kwy",
                isPerson = Person.OTHER
            ),
            Chat(
                text = "kwy",
                isPerson = Person.ME
            ),
            Chat(
                text = "kwy",
                isPerson = Person.ME
            ),
            Chat(
                text = "kwy",
                isPerson = Person.OTHER
            ),
            Chat(
                text = "kwy",
                isPerson = Person.ME
            )
        )
    }
}
