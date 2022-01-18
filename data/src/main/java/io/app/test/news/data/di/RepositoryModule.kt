package io.app.test.news.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped
import io.app.test.news.data.repository.ItemRepositoryImpl
import io.app.test.news.data.repository.StoryRepositoryImpl
import io.app.test.news.domain.repository.ItemRepository
import io.app.test.news.domain.repository.StoryRepository

@Module
@InstallIn(ActivityRetainedComponent::class)
abstract class RepositoryModule {
    @Binds
    @ActivityRetainedScoped
    abstract fun provideItemRepository(
        itemRepositoryImpl: ItemRepositoryImpl
    ): ItemRepository

    @Binds
    @ActivityRetainedScoped
    abstract fun provideStoryRepository(
        storyRepositoryImpl: StoryRepositoryImpl
    ): StoryRepository
}
