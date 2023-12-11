package com.example.magicacompleto.HistoriasAma

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.magicacompleto.R

class Historia1Ama : AppCompatActivity() {
    private var mediaPlayer: MediaPlayer? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_historia1_ama)
        val playButton = findViewById<Button>(R.id.playButton)

        mediaPlayer = MediaPlayer.create(this, R.raw.musicafondo)

        playButton.setOnClickListener {
            if (mediaPlayer?.isPlaying == true) {
                mediaPlayer?.pause()
                playButton.text = "Reproducir Audio"
            } else {
                mediaPlayer?.start()
                playButton.text = "Pausar Audio"
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer?.release()
    }
}