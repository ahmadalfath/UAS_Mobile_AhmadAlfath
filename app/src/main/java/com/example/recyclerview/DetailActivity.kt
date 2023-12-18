package com.example.recyclerview

import android.annotation.SuppressLint
import android.content.Intent
import android.media.MediaPlayer
import android.os.Build
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DetailActivity : AppCompatActivity() {

    private var mediaPlayer: MediaPlayer? = null

    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val receivedData = if (Build.VERSION.SDK_INT >= 33) {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra<audio>("shadow")
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra<audio>("shadow")
        }

        val gambar: ImageView = findViewById(R.id.img_gambar)
        val judul: TextView = findViewById(R.id.tv_Judul)
        val description: TextView = findViewById(R.id.tv_description)

        if (receivedData != null) {
            gambar.setImageResource(receivedData.gambar)
            judul.text = receivedData.judul
            description.text = receivedData.data_deskripsi

            // Inisialisasi MediaPlayer dengan file musik dari res/raw sesuai dengan audioId
            mediaPlayer = MediaPlayer.create(this, receivedData.audioId)

            // Set pengaturan tambahan jika diperlukan
            mediaPlayer?.isLooping = true // Untuk memutar musik secara terus-menerus (loop)
            mediaPlayer?.setVolume(0.5f, 0.5f) // Sesuaikan volume (0.0 - 1.0)

            // Memulai pemutaran musik
            mediaPlayer?.start()

        }
    }


    // Metode onStop digunakan untuk menghentikan pemutaran musik saat aktivitas tidak terlihat
    override fun onStop() {
        super.onStop()
        if (mediaPlayer != null && mediaPlayer!!.isPlaying) {
            mediaPlayer?.pause() // Hentikan pemutaran musik jika sedang berlangsung
        }
    }

    // Metode onDestroy digunakan untuk memastikan pembebasan sumber daya
    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer?.release() // Bebaskan sumber daya MediaPlayer
    }
}
