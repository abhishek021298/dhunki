package com.example.technomusic.mvvm.view.activity

import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import com.example.technomusic.R
import com.example.technomusic.data.model.Song
import com.example.technomusic.databinding.ActivityPlayerBinding
import com.example.technomusic.mvvm.base.BaseActivity

class PlayerActivity : BaseActivity<ActivityPlayerBinding>() {

    override fun getLayoutId(): Int = R.layout.activity_player

    override fun observeViewModel() {}

    override fun initViews() {

        val player = ExoPlayer.Builder(this).build()
// Attach player to the view.
        binding.player.player = player
        val mediaFile = intent.getSerializableExtra("data") as Song
// Set the media item to be played.
        player.setMediaItem(MediaItem.fromUri(mediaFile.mediaUri))
// Prepare the player.
        player.prepare()
    }


}