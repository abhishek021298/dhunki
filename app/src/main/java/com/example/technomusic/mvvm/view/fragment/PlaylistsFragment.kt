package com.example.technomusic.mvvm.view.fragment

import android.view.View
import com.example.technomusic.R
import com.example.technomusic.databinding.FragmentPlaylistsBinding
import com.example.technomusic.mvvm.base.BaseFragment

class PlaylistsFragment : BaseFragment<FragmentPlaylistsBinding>(), View.OnClickListener {


    override fun getLayoutId(): Int = R.layout.fragment_playlists

    override fun observeViewModel() {}
    override fun init() {



    }



    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.btGivePermission -> {

            }
        }
    }

}