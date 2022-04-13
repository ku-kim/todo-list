package com.example.todo_list.todo.data

data class TodosCard(
    val id: Int,
    val title: String,
    val contents: String,
    val user: String,
    val status: String,
    val createdDateTime: String,
    val updatedDateTime: String
)
