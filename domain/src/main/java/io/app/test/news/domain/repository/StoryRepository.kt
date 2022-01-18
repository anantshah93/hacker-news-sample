package io.app.test.news.domain.repository

import io.app.test.news.domain.entity.StoryType

interface StoryRepository {
    suspend fun fetchStories(type: StoryType): Result<List<Int>>
}
