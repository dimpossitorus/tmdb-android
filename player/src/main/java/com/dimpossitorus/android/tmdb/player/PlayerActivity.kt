package com.dimpossitorus.android.tmdb.player

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.google.android.play.core.splitcompat.SplitCompat
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import kotlinx.android.synthetic.main.activity_player.*

class PlayerActivity : YouTubeBaseActivity() {

    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(newBase)
        SplitCompat.installActivity(this)
    }

    companion object {
        val VIDEO_ID = "VIDEO_ID"

        fun startActivity(context: Context, videoId: String) {
            val intent = Intent(context, PlayerActivity::class.java).apply {
                putExtra(VIDEO_ID, videoId)
            }
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player)
        var videoId: String =
            intent.getStringExtra(VIDEO_ID) ?: savedInstanceState?.getString(VIDEO_ID, "").orEmpty()
        youtubePlayer.initialize(
            "AIzaSyASm6H_QZzjOQfGjATSHTfzc7dPencyvbw",
            object : YouTubePlayer.OnInitializedListener {
                override fun onInitializationSuccess(
                    provider: YouTubePlayer.Provider,
                    player: YouTubePlayer,
                    wasRestored: Boolean
                ) {
                    player.cueVideo(videoId)
                }

                override fun onInitializationFailure(
                    p0: YouTubePlayer.Provider?,
                    p1: YouTubeInitializationResult?
                ) {
                    Log.d("YOUTUBE_PLAYER", "Error")
                }

            })
    }

    fun initView() {

    }
}
