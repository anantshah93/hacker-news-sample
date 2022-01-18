package io.app.test.news.data.service

import io.app.test.news.data.entity.UserResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface UserService {
    @GET("user/{id}.json")
    suspend fun fetchUser(@Path("id") id: String): UserResponse
}
