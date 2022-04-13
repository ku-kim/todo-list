package com.example.todo_list.todo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.todo_list.R
import com.example.todo_list.databinding.TodoItemBinding
import com.example.todo_list.todo.data.TodosCard

class TodoAdapter : ListAdapter<TodosCard, TodoAdapter.TodoViewHolder>(diffUtil) {
    inner class TodoViewHolder(private val binding: TodoItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(card: TodosCard) {
            binding.todosCard = card
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        DataBindingUtil.inflate<TodoItemBinding>(
            LayoutInflater.from(parent.context),
            R.layout.todo_item,
            parent,
            false
        ).let {
            TodoViewHolder(it)
        }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }
}

private val diffUtil = object : DiffUtil.ItemCallback<TodosCard>() {
    override fun areItemsTheSame(oldItem: TodosCard, newItem: TodosCard): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: TodosCard, newItem: TodosCard): Boolean {
        return oldItem == newItem
    }

}