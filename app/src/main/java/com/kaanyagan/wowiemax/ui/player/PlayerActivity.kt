package com.kaanyagan.wowiemax.ui.player

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.kaanyagan.wowiemax.R
import com.kaanyagan.wowiemax.databinding.ActivityPlayerBinding
import com.kaanyagan.wowiemax.ui.main.MainActivity

class PlayerActivity : AppCompatActivity() {
    lateinit var binding: ActivityPlayerBinding
    lateinit var exoPlayer:ExoPlayer
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlayerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        exoPlayer = ExoPlayer.Builder(this).build()
        binding.videoPlayer.player = exoPlayer
        exoPlayer.setMediaItem(MediaItem.fromUri(Uri.parse(getString(R.string.url))))
        exoPlayer.prepare()
        exoPlayer.play()

        binding.ivBack.setOnClickListener {
            exoPlayer.stop()
            finish()
        }
        binding.videoPlayer.setOnClickListener {
            binding.ivBack.isVisible = !binding.ivBack.isVisible
        }
    }


    override fun onBackPressed() {
        super.onBackPressed()
        exoPlayer.stop()
    }
}