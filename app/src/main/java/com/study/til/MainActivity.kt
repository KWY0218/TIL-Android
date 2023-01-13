package com.study.til

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.google.gson.Gson
import com.study.til.databinding.ActivityMainBinding
import io.socket.client.IO
import io.socket.client.Socket
import io.socket.emitter.Emitter
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.net.URISyntaxException

class MainActivity : AppCompatActivity() {
    private lateinit var adapter: ChatAdapter
    private lateinit var binding: ActivityMainBinding
    private lateinit var socket: Socket
    private val gson = Gson()
    private val chatList = MutableStateFlow<List<Chat>>(emptyList())
    private val userType = 2
    private val userAuthor = "LLL"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        initAdapter()
        initSocket()
        sendMessage()
        onMessage()
        collectChat()
    }

    private fun collectChat() {
        chatList.flowWithLifecycle(lifecycle)
            .onEach { chat -> adapter.submitList(chat) }
            .launchIn(lifecycleScope)
    }

    private fun initSocket() {
        try {
            socket = IO.socket("http://10.0.2.2:3000")
        } catch (e: URISyntaxException) {
            Log.e("MainActivity111", "error ${e.reason}")
        }
        socket.connect()
        Log.d("tesssss", "connect ${socket.connected()}")
    }

    private fun initAdapter() {
        adapter = ChatAdapter(userType)
        binding.recyclerview.adapter = adapter
    }

    private fun sendMessage() {
        with(binding) {
            button.setOnClickListener {
                val chat = Chat(
                    id = userType,
                    author = userAuthor,
                    content = editText.text.toString()
                )
                val jsonChat = gson.toJson(chat)
                socket.emit("newMessage", jsonChat)
                editText.text.clear()
            }
        }
    }

    private fun onMessage() {
        val msg = Emitter.Listener { data ->
            val chat = gson.fromJson(data[0].toString(), Chat::class.java)
            val tempList = chatList.value.plus(chat)
            chatList.value = tempList
        }
        socket.on("resMsg", msg)
    }

    override fun onDestroy() {
        super.onDestroy()
        socket.disconnect()
    }
}
