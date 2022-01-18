package io.app.test.news.data.service

import io.app.test.news.data.entity.ItemResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ItemService {
    @GET("item/{id}.json")
    suspend fun fetchItem(@Path("id") id: Int): ItemResponse
}
