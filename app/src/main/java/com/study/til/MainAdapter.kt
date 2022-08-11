package com.study.til

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.study.til.databinding.ItemBoardBinding

class MainAdapter :
    ListAdapter<Board, MainAdapter.BoardViewHolder>(BOARD_COMPARATOR) {

    class BoardViewHolder(
        private val binding: ItemBoardBinding
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(board: Board) {
            binding.board = board
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BoardViewHolder {
        val view = ItemBoardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BoardViewHolder(view)
    }

    override fun onBindViewHolder(holder: BoardViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current)
    }

    companion object {
        private val BOARD_COMPARATOR = object : DiffUtil.ItemCallback<Board>() {
            override fun areItemsTheSame(oldItem: Board, newItem: Board): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(oldItem: Board, newItem: Board): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }
}
