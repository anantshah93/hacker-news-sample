package io.app.test.news.data.mapper

import io.app.test.news.data.entity.ItemResponse
import io.app.test.news.data.local.ItemEntity
import io.app.test.news.domain.entity.Item
import io.app.test.news.domain.entity.StoryType
import java.util.Locale

fun StoryType.toData() = name.lowercase(Locale.getDefault())

fun ItemResponse.toDomain() =
    Item(id, type, by.orEmpty(), time ?: 0, text, url, score, title, descendants)

fun ItemEntity.toDomain() =
    Item(id, type, by.orEmpty(), time ?: 0, text, url, score, title, descendants)

fun Item.toLocal() = ItemEntity(id, type, by, time, text, url, score, title, descendants)
