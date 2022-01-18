package io.app.test.news.data.repository

import io.app.test.news.data.local.ItemDao
import io.app.test.news.data.mapper.toDomain
import io.app.test.news.data.mapper.toLocal
import io.app.test.news.data.service.ItemService
import io.app.test.news.domain.entity.Item
import io.app.test.news.domain.helper.exception.DataNotFoundException
import io.app.test.news.domain.repository.ItemRepository
import javax.inject.Inject

class ItemRepositoryImpl @Inject constructor(
    private val service: ItemService,
    private val itemDao: ItemDao
) : ItemRepository {
    override suspend fun fetchOfflineItem(id: Int): Result<Item> {
        val localEntity = itemDao.fetch(id)
        return if (localEntity != null) {
            Result.success(localEntity.toDomain())
        } else {
            Result.failure(DataNotFoundException())
        }
    }

    override suspend fun fetchItem(id: Int): Result<Item> = runCatching {
        val item = service.fetchItem(id).toDomain()
        itemDao.insert(
            item.toLocal()
        )
        return@runCatching item
    }
}
