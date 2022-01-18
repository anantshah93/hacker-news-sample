package io.app.test.news.data.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.app.test.news.data.local.AppDatabase
import io.app.test.news.data.local.ItemDao
import io.app.test.news.data.repository.ItemRepositoryImpl
import io.app.test.news.data.repository.StoryRepositoryImpl
import io.app.test.news.data.service.ItemService
import io.app.test.news.data.service.StoryService
import io.app.test.news.domain.repository.ItemRepository
import io.app.test.news.domain.repository.StoryRepository
import io.app.test.news.domain.usecase.GetItemUseCase
import io.app.test.news.domain.usecase.GetStoriesUseCase
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {
    @Provides
    @Singleton
    fun provideAppDatabase(
        @ApplicationContext context: Context
    ): AppDatabase = AppDatabase.getInstance(context)

    @Provides
    @Singleton
    fun provideItemDao(
        appDatabase: AppDatabase
    ): ItemDao = appDatabase.itemDao()

    @Provides
    @Singleton
    fun provideItemService(
        retrofit: Retrofit
    ): ItemService = createApiService(retrofit)

    @Provides
    @Singleton
    fun provideStoryService(
        retrofit: Retrofit
    ): StoryService = createApiService(retrofit)

    @Provides
    fun provideItemRepository(
        itemService: ItemService,
        itemDao: ItemDao
    ): ItemRepository = ItemRepositoryImpl(itemService, itemDao)

    @Provides
    fun provideStoryRepository(
        storyService: StoryService
    ): StoryRepository = StoryRepositoryImpl(storyService)

    @Provides
    fun provideGetItemUseCase(itemRepository: ItemRepository): GetItemUseCase =
        GetItemUseCase(itemRepository)

    @Provides
    fun provideGetStoriesUseCase(storyRepository: StoryRepository): GetStoriesUseCase =
        GetStoriesUseCase(storyRepository)

    private inline fun <reified T> createApiService(retrofit: Retrofit): T {
        return retrofit.create(T::class.java)
    }
}
