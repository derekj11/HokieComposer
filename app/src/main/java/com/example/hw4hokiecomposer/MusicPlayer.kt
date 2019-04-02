package com.example.hw4hokiecomposer

import android.content.Context
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.content.res.AssetFileDescriptor
import java.io.IOException


class MusicPlayer(val musicService: MusicService): MediaPlayer.OnCompletionListener {

    private val musicPath = arrayOf(R.raw.gotechgo, R.raw.GTG2, R.raw.GTG3)
    private val musicName = arrayOf("Go Tech Go", "Go Tech Go 2", "Go Tech Go 3")

    val mContext: Context
    private var musicIndex = 0
    
    // Before start: 0
    // Playing: 1
    // Paused: 2
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
            musicService.onUpdateMusicName(getMusicName())
        } catch (ex: IOException) {
            ex.printStackTrace()
        }

        musicStatus = 1
    }

    fun pauseMusic() {

    }

    fun resumeMusic() {

    }

    fun getMusicName(): String {
        return musicName[musicIndex]
    }

    override fun onCompletion(mp: MediaPlayer?) {
        musicIndex = (musicIndex + 1) % 3
        player.release()
        playMusic()
    }
}