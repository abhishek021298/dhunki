package com.example.technomusic.mvvm.view.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.technomusic.mvvm.view.fragment.AlbumFragment
import com.example.technomusic.mvvm.view.fragment.ArtistFragment
import com.example.technomusic.mvvm.view.fragment.GenresFragment
import com.example.technomusic.mvvm.view.fragment.PlaylistsFragment
import com.example.technomusic.mvvm.view.fragment.SongsFragment

class ViewPagerAdapter(manager: FragmentManager, lifecycle: Lifecycle) : FragmentStateAdapter(manager, lifecycle) {
    private val TOTAL_FRAGMENTS = 5

    override fun getItemCount(): Int {
        return TOTAL_FRAGMENTS
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> PlaylistsFragment()
            1 -> SongsFragment()
            2 -> ArtistFragment()
            3 -> AlbumFragment()
            4 -> GenresFragment()
            else -> PlaylistsFragment()
        }
    }
}