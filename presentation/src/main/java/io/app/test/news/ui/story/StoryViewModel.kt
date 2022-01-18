package io.app.test.news.ui.story

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.app.test.news.domain.entity.Item
import io.app.test.news.domain.entity.StoryType
import io.app.test.news.domain.usecase.GetItemUseCase
import io.app.test.news.domain.usecase.GetStoriesUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StoryViewModel @Inject constructor(
    private val getItemUseCase: GetItemUseCase,
    private val getStoriesUseCase: GetStoriesUseCase
) : ViewModel() {
    private val modifiableStoryIds = MutableLiveData<Result<List<Int>>>()
    val storyIds: LiveData<Result<List<Int>>> = modifiableStoryIds

    private val modifiableItems = MutableLiveData<Map<Int, Item>>(mapOf())
    val items: LiveData<Map<Int, Item>> = modifiableItems

    fun fetchItems(type: StoryType) {
        viewModelScope.launch {
            val stories = getStoriesUseCase.execute(type)
            modifiableStoryIds.postValue(stories)
        }
    }

    fun fetchItem(id: Int) {
        viewModelScope.launch {
            val item = getItemUseCase.execute(id).getOrNull() ?: return@launch
            val items = modifiableItems.value?.toMutableMap() ?: return@launch
            items[id] = item
            modifiableItems.postValue(items)
        }
    }
}
