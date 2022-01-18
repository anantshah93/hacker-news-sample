package io.app.test.news.domain.usecase

import io.app.test.news.domain.entity.StoryType
import io.app.test.news.domain.repository.StoryRepository

class GetStoriesUseCase(private val storyRepository: StoryRepository) {
    suspend fun execute(storyType: StoryType): Result<List<Int>> =
        storyRepository.fetchStories(storyType)
}
