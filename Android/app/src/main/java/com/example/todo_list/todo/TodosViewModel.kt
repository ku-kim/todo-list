package com.example.todo_list.todo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.todo_list.data.Repository
import com.example.todo_list.todo.data.TodosCard

class TodosViewModel(private val repository: Repository): ViewModel() {
    var _todosList = MutableLiveData<List<TodosCard>>()
    val todosList: LiveData<List<TodosCard>> get() = _todosList

    private var _checkLoading = MutableLiveData<Boolean>()
    val checkLoading: LiveData<Boolean> get() = _checkLoading

//    fun getTodos() {
//        _checkLoading.value = true
//        viewModelScope.launch {
//            val response = repository.getHistories()
//            if (response.isSuccessful) {
//                _todosList.value = response.body()
//                _checkLoading.value = false
//            }
//        }
//    }
}