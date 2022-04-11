package com.example.todo_list.network

import com.example.todo_list.history.data.HistoryCard
import com.example.todo_list.tasks.data.Task
import retrofit2.Response
import retrofit2.http.*

interface TodoService {

    @GET("api/{histories}")
    suspend fun getHistories(
        @Path("histories") variable: String
    ): Response<List<HistoryCard>>

    @GET("api/todos")
    suspend fun getAllTasks(): Response<List<Task>>

    @GET("api/todos/{id}")
    suspend fun getTask(
        @Path("id") id: Int
    ): Response<Task>

    @FormUrlEncoded
    @POST("api/todos")
    suspend fun createTask(
        @Field("title") title: String,
        @Field("contents") contents: String,
        @Field("user") user: String,
        @Field("status") status: String,
    ): Response<Task>

    @DELETE("api/todos/{id}")
    suspend fun deleteTask(
        @Path("id") id: Int
    ): Response<Unit>

    @FormUrlEncoded
    @PATCH("api/todos/{id}")
    suspend fun updateTask(
        @Path("id") id: Int,
        @FieldMap param: HashMap<String, String>
    ): Response<Task>
}