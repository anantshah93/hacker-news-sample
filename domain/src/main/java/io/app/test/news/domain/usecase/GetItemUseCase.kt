package io.app.test.news.domain.usecase

import io.app.test.news.domain.entity.Item
import io.app.test.news.domain.repository.ItemRepository

class GetItemUseCase(private val itemRepository: ItemRepository) {
    suspend fun execute(id: Int): Result<Item> =
        itemRepository.fetchItem(id)
}
