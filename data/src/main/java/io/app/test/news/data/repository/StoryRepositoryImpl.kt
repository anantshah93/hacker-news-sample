package io.app.test.news.data.repository

import io.app.test.news.data.mapper.toData
import io.app.test.news.data.service.StoryService
import io.app.test.news.domain.entity.StoryType
import io.app.test.news.domain.repository.StoryRepository
import javax.inject.Inject

class StoryRepositoryImpl @Inject constructor(
    private val service: StoryService
) : StoryRepository {
    override suspend fun fetchStories(type: StoryType): Result<List<Int>> =
        runCatching { service.fetchStories(type.toData()).take(30) }
}
