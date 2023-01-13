package com.study.til

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.study.til.databinding.ItemMeChatBinding
import com.study.til.databinding.ItemOtherChatBinding

class ChatAdapter(
    private val type: Int
) : ListAdapter<Chat, RecyclerView.ViewHolder>(chatComparator) {
    private lateinit var inflater: LayoutInflater

    override fun getItemViewType(position: Int): Int {
        return if (type == currentList[position].id) Person.ME.ordinal
        else Person.OTHER.ordinal
    }

    class ChatMeViewHolder(
        private val binding: ItemMeChatBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(chat: Chat) {
            binding.content.text = chat.content
            binding.author.text = chat.author
        }
    }

    class ChatOtherViewHolder(
        private val binding: ItemOtherChatBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(chat: Chat) {
            binding.content.text = chat.content
            binding.author.text = chat.author
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (!::inflater.isInitialized) inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            Person.ME.ordinal -> ChatMeViewHolder(
                ItemMeChatBinding.inflate(
                    inflater,
                    parent,
                    false
                )
            )
            else -> ChatOtherViewHolder(ItemOtherChatBinding.inflate(inflater, parent, false))
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val current = getItem(position)
        when (holder) {
            is ChatMeViewHolder -> holder.bind(current)
            is ChatOtherViewHolder -> holder.bind(current)
        }
    }

    companion object {
        private val chatComparator = object : DiffUtil.ItemCallback<Chat>() {
            override fun areItemsTheSame(oldItem: Chat, newItem: Chat): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Chat, newItem: Chat): Boolean {
                return oldItem == newItem
            }
        }
    }
}
