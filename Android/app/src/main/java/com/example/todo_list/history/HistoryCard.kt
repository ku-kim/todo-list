package com.example.todo_list.history

data class HistoryCard(
    val index: Int,
    val username: String,
    val content: String,
    val time: String
)
