package com.example.todo_list.network

import com.example.todo_list.history.data.HistoryCard
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

class NetworkModule {
    companion object RetrofitApiObject {
        private const val BASE_URL = "https://f278a12c-c825-466b-aa01-65337bbdf28a.mock.pstmn.io/"

        private val retrofit =
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        val service: TodoService = retrofit.create(TodoService::class.java)
    }
}
