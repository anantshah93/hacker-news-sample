package io.app.test.news.domain.repository

import io.app.test.news.domain.entity.Item

interface ItemRepository {
    suspend fun fetchItem(id: Int): Result<Item>

    suspend fun fetchOfflineItem(id: Int): Result<Item>
}
