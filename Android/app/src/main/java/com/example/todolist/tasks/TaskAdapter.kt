package com.example.todolist.tasks

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.R
import com.example.todolist.databinding.TodoItemBinding
import com.example.todolist.tasks.data.Task
import java.util.*

class TaskAdapter : ListAdapter<Task, TaskAdapter.TodoViewHolder>(diffUtil) {
    inner class TodoViewHolder(private val binding: TodoItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(task: Task) {
            binding.task = task
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
        holder.itemView.translationX = 0f
    }

    fun moveItem(fromPos: Int, toPos: Int) {
        val newList = currentList.toMutableList()
        Collections.swap(newList, fromPos, toPos)
        notifyItemMoved(fromPos, toPos)
    }
}

private val diffUtil = object : DiffUtil.ItemCallback<Task>() {
    override fun areItemsTheSame(oldItem: Task, newItem: Task): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Task, newItem: Task): Boolean {
        return oldItem == newItem
    }

}