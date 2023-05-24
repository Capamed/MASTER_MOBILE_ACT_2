package com.example.datastoreapp


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.bumptech.glide.Glide

class ShowGifMovie : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_gif_movie)
        val bundle = intent.extras
        val gifUrlMovie = bundle?.getString("gifUrlMovie")
        val imageView = findViewById<ImageView>(R.id.imageGif)
        val gifUrl = gifUrlMovie
        Glide.with(this)
            .asGif()
            .load(gifUrl)
            .into(imageView)
    }
}