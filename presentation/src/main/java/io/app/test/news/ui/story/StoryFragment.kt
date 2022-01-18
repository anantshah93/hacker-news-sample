package io.app.test.news.ui.story

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewmodel.compose.viewModel
import dagger.hilt.android.AndroidEntryPoint
import io.app.test.news.domain.entity.StoryType
import io.app.test.news.helper.DateHelper
import io.app.test.news.helper.openLink

@AndroidEntryPoint
class StoryComposeFragment : Fragment() {
    private val storyViewModel: StoryViewModel by viewModels()

    private lateinit var storyType: StoryType

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        storyType = StoryType.valueOf(arguments?.getString("type") ?: return)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = ComposeView(requireContext()).apply {
        setContent {
            StoryList { url ->
                context?.openLink(url)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fetchData()
    }

    private fun fetchData() = storyViewModel.fetchItems(storyType)

    companion object {
        fun newInstance(type: String) = StoryComposeFragment()
            .apply {
                arguments = Bundle().apply {
                    this.putString("type", type)
                }
            }
    }
}

@Composable
fun StoryList(
    storyViewModel: StoryViewModel = viewModel(),
    onUrlClick: (String) -> Unit
) {
    val storyIds = storyViewModel.storyIds.observeAsState().value?.getOrNull()
    if (storyIds == null) {
        Box(modifier = Modifier.width(64.dp), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
        return
    }
    LazyColumn(
        contentPadding = PaddingValues(horizontal = 8.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items(storyIds) { storyId ->
            StoryItem(storyId = storyId, onUrlClick = onUrlClick)
        }
    }
}

@Composable
fun StoryItem(
    storyId: Int,
    storyViewModel: StoryViewModel = viewModel(),
    onUrlClick: (String) -> Unit
) {
    val items = storyViewModel.items.observeAsState().value ?: return

    val item = items[storyId]
    if (item == null) {
        storyViewModel.fetchItem(storyId)
        return
    }
    Card {
        Column(modifier = Modifier.padding(8.dp)) {
            Text(text = item.title.orEmpty(), color = Color.Black, fontSize = 16.sp, fontWeight = FontWeight.Bold)
            val url = item.url
            if (url != null) {
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = url.orEmpty(),
                    color = Color.Black,
                    fontSize = 14.sp,
                    modifier = Modifier.clickable {
                        onUrlClick.invoke(url)
                    }
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Row {
                Text(text = item.by, color = Color.Blue, fontSize = 14.sp)
                Spacer(modifier = Modifier.width(8.dp))
                val time = DateHelper.formatTime(item.time)
                Text(text = time, color = Color.Gray, fontSize = 14.sp)
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}
