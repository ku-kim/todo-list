package com.example.todo_list.data

import com.example.todo_list.history.data.HistoryCard
import com.example.todo_list.tasks.data.Task
import retrofit2.Response

interface Repository {
    suspend fun getHistories(): Response<List<HistoryCard>>

    suspend fun getAllTasks(): Response<List<Task>>

    suspend fun getTask(id: Int): Response<Task>

    suspend fun createTask(title: String, contents: String, user: String, status: String): Response<Task>

    suspend fun deleteTask(id: Int)

    suspend fun updateTask(id: Int, param: HashMap<String, String>): Response<Task>
}