package io.app.test.news.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import io.app.test.news.databinding.ActivityMainBinding
import io.app.test.news.domain.entity.StoryType
import io.app.test.news.ui.story.StoryPagerAdapter

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private lateinit var adapter: StoryPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()
    }

    private fun initViews() {
        adapter = StoryPagerAdapter(supportFragmentManager)
        binding.apply {
            storyViewPager.adapter = adapter
            storyViewPager.offscreenPageLimit = StoryType.values().size
            storyTabLayout.setupWithViewPager(storyViewPager)
        }
    }
}
