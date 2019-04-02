package com.example.hw4hokiecomposer

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder

class MusicService: Service() {

    companion object {
        const val COMPLETE_INTENT = "complete intent"
        const val MUSIC_NAME = "music name"
    }

    private val iBinder = MyBinder()

    override fun onBind(intent: Intent?): IBinder? {
        return iBinder
    }

    inner class MyBinder: Binder() {
        fun getService(): MusicService {
            return this@MusicService
        }
    }

    fun onUpdateMusicName(musicName: String) {
        val intent = Intent(COMPLETE_INTENT)
        intent.putExtra(MUSIC_NAME, musicName)
        sendBroadcast(intent)
    }
}