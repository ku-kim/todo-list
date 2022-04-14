package com.example.todolist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.todolist.data.Repository

class ViewModelFactory(private val repository: Repository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(TasksViewModel::class.java)) {
            TasksViewModel(repository) as T
        } else {
            throw Exception("클래스가 존재하지 않습니다.")
        }
    }
}