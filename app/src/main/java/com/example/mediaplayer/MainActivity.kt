package com.example.mediaplayer

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.ImageView
import android.widget.SeekBar
import android.widget.TextView
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    private lateinit var mediaPlayer: MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val playBtn = findViewById<ImageView>(R.id.play)
        val pauseBtn = findViewById<ImageView>(R.id.pause)
        val stopBtn = findViewById<ImageView>(R.id.stop)
        val seekBar = findViewById<SeekBar>(R.id.seekBar)
        val currentDuration = findViewById<TextView>(R.id.currentDuration)
        val duration = findViewById<TextView>(R.id.duration)

        mediaPlayer = MediaPlayer.create(this, R.raw.avijog)

        playBtn.setOnClickListener {
            mediaPlayer.start()
        }
        pauseBtn.setOnClickListener {
            mediaPlayer.pause()
        }
        stopBtn.setOnClickListener {
            mediaPlayer.stop()
            mediaPlayer.reset()
            mediaPlayer.release()

        }

        seekBar.max = mediaPlayer.duration
        duration.text = mediaPlayer.duration.toString()
        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {

                if (fromUser){
                    mediaPlayer.seekTo(progress)
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}

            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        val handler= Handler()
        handler.postDelayed(object : Runnable{
            override fun run() {

                try {
                    seekBar.progress = mediaPlayer.currentPosition
                    handler.postDelayed(this, 1000)
                    currentDuration.text = mediaPlayer.currentPosition.toString()
                } catch (exception: Exception){
                    seekBar.progress = 0
                }

            }

        }, 0)

    }
}