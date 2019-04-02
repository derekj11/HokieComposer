package com.example.hw4hokiecomposer

import android.content.Context
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.content.res.AssetFileDescriptor


class MusicPlayer(val musicService: MusicService): MediaPlayer.OnCompletionListener {

    private val musicPath = arrayOf(R.raw.gotechgo, R.raw.GTG2, R.raw.GTG3)

    val mContext: Context
    private var musicIndex = 0
    private var musicStatus = 0
    lateinit var player: MediaPlayer

    fun playMusic() {
        player = MediaPlayer()
        player.setAudioAttributes(
            AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_MEDIA)
                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                .build())
        val assetFileDescriptor = mContext.resources.openRawResourceFd(musicPath[musicIndex])
        try {
            player.setDataSource(assetFileDescriptor)
            player.prepare()
            player.setOnCompletionListener(this)
            player.start()
        }
    }

    fun pauseMusic() {

    }

    fun resumeMusic() {

    }

    override fun onCompletion(mp: MediaPlayer?) {
        musicIndex = (musicIndex + 1) % 3
        player.release()
        playMusic()
    }
}