package com.example.hw4hokiecomposer


import android.content.*
import android.os.Bundle
import android.os.IBinder
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.workDataOf
import kotlinx.android.synthetic.main.fragment_edit.*


/**
 * A simple [Fragment] subclass.
 *
 */
class PlayFragment : Fragment(), View.OnClickListener {

    companion object {
        const val TAG = "HW4_TAG"
        const val USER_ID = "derekj11"
        const val URL = "https://posthere.io/"
        const val ROUTE = "29d9-4b78-833d"
        const val INITIALIZE_STATUS = "initialization status"
        const val MUSIC_PLAYING = "music playing"
    }

    override fun onClick(v: View?) {
        if (isBound) {
            if (v == playButton) {
                when (musicService?.getPlayingStatus()) {
                    0 -> {
                        musicService?.startMusic()
                        playButton?.text = "Pause"
                        appendEvent("pressed play")
                    }
                    1 -> {
                        musicService?.pauseMusic()
                        playButton?.text = "Resume"
                        appendEvent("pressed pause")
                    }
                    2 -> {
                        musicService?.resumeMusic()
                        playButton?.text = "Pause"
                        appendEvent("pressed resume")
                    }
                }
            } else if (v == restartButton) {
                musicService?.restartMusic()
            }
        }
    }

    private val images = arrayOf(
        R.drawable.gotechgo,
        R.drawable.gtg2,
        R.drawable.gtg3,
        R.drawable.clapping,
        R.drawable.cheering,
        R.drawable.letsgohokies)

    var music: TextView? = null
    var playButton: Button? = null
    var restartButton: Button? = null
    var image: ImageView? = null

    var song: String? = null
    var effect1: String? = null
    var position1: Int? = 0
    var effect2: String? = null
    var position2: Int? = 0
    var effect3: String? = null
    var position3: Int? = 0

    var isBound = false
    var musicService: MusicService? = null
    var musicCompletionReceiver: MusicCompletionReceiver? = null
    var startMusicServiceIntent: Intent? = null
    var isInitialized = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_play, container, false)
    }

    private val musicServiceConnection = object : ServiceConnection {
        override fun onServiceConnected(componentName: ComponentName, iBinder: IBinder) {

            val binder = iBinder as MusicService.MyBinder
            musicService = binder.getService()
            isBound = true
            when (musicService?.getPlayingStatus()) {
                0 -> playButton?.text = "Start"
                1 -> playButton?.text = "Pause"
                2 -> playButton?.text = "Resume"
            }
        }

        override fun onServiceDisconnected(componentName: ComponentName) {
            musicService = null
            isBound = false
        }
    }

    override fun onPause() {
        super.onPause()
        if (isBound) {
            context?.unbindService(musicServiceConnection)
            isBound = false
        }
        context?.unregisterReceiver(musicCompletionReceiver)

        appendEvent("onPause")
    }

    override fun onResume() {
        super.onResume()
        if (isInitialized && !isBound) {
            context?.bindService(startMusicServiceIntent, musicServiceConnection, Context.BIND_AUTO_CREATE)
        }
        context?.registerReceiver(musicCompletionReceiver, IntentFilter(MusicService.COMPLETE_INTENT))
        appendEvent("onResume")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        song = this.arguments?.getString("song")
        effect1 = this.arguments?.getString("effect1")
        position1 = this.arguments?.getInt("position1")
        effect2 = this.arguments?.getString("effect2")
        position2 = this.arguments?.getInt("position2")
        effect3 = this.arguments?.getString("effect3")
        position3 = this.arguments?.getInt("position3")

        playButton = view.findViewById(R.id.playPauseButton)
        restartButton = view.findViewById(R.id.restartButton)
        music = view.findViewById(R.id.songName)
        playButton?.setOnClickListener(this)
        restartButton?.setOnClickListener(this)

        if (savedInstanceState != null) {
            isInitialized = savedInstanceState.getBoolean(INITIALIZE_STATUS)
            music?.text = savedInstanceState.getString(MUSIC_PLAYING)
        }
        startMusicServiceIntent = Intent(context, MusicService::class.java)
        if (!isInitialized) {
            context?.startService(startMusicServiceIntent)
            isInitialized = true
        }
        musicCompletionReceiver = MusicCompletionReceiver(this)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putBoolean(INITIALIZE_STATUS, isInitialized)
        outState.putString(MUSIC_PLAYING, music?.text.toString())
        super.onSaveInstanceState(outState)
    }

    private fun appendEvent(event: String) {
        WorkManager.getInstance().beginUniqueWork(TAG, ExistingWorkPolicy.KEEP, OneTimeWorkRequestBuilder<UploadWorker>().setInputData(
            workDataOf("userID" to USER_ID, "event" to event)
        ).build()).enqueue()
    }

    fun updatePic(musicName: String) {

    }

}
