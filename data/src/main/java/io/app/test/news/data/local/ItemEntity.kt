package io.app.test.news.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ItemEntity")
data class ItemEntity(
    @PrimaryKey
    val id: Int,
    val type: String?,
    val `by`: String?,
    val time: Int?,
    val text: String?,
    val url: String?,
    val score: Int?,
    val title: String?,
    val descendants: Int?
)
