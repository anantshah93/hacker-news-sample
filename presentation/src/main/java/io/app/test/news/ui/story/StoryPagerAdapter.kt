package io.app.test.news.ui.story

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import io.app.test.news.domain.entity.StoryType

class StoryPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(
    fm,
    BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
) {
    override fun getItem(position: Int): Fragment {
        val type = StoryType.values()[position]
        return StoryComposeFragment.newInstance(type.name)
    }

    override fun getCount(): Int = StoryType.values().size

    override fun getPageTitle(position: Int): CharSequence {
        val type = StoryType.values()[position]
        return type.name
    }
}
