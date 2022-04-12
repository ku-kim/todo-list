package com.example.todo_list.history.data

import com.example.todo_list.Repository
import com.example.todo_list.network.NetworkModule
import retrofit2.Response

class HistoryRepository : Repository {
    private val network = NetworkModule.service

    override suspend fun getHistories(): Response<List<HistoryCard>> {
        return network.getHistories("histories")
    }
}