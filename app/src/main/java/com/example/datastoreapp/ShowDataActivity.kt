package com.example.datastoreapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso

class ShowDataActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_data)
        val bundle = intent.extras

        //Show data tittleMovie
        val tittleMovie = bundle?.getString("tittleMovie")
        val txtViewTittleMovie = findViewById<TextView>(R.id.tittleMovie)
        txtViewTittleMovie.text = tittleMovie

        //Show data plotMovie
        val plotMovie = bundle?.getString("plotMovie")
        val txtViewPlotMovie = findViewById<TextView>(R.id.resumePlot)
        txtViewPlotMovie.text = plotMovie

        //Show data descriptionMovie
        val descriptionMovie = bundle?.getString("descriptionMovie")
        val txtViewDescriptionMovie = findViewById<TextView>(R.id.resumeDescriptionMovie)
        txtViewDescriptionMovie.text = descriptionMovie

        //Show data releaseMovie
        val releaseMovie = bundle?.getString("releaseMovie")
        val txtViewResumeMovie = findViewById<TextView>(R.id.resumeRelease)
        txtViewResumeMovie.text = releaseMovie

        //Show data playtimeMovie
        val playtimeMovie = bundle?.getString("playtimeMovie")
        val txtViewPlayTimeMovie = findViewById<TextView>(R.id.resumePlaytime)
        txtViewPlayTimeMovie.text = playtimeMovie

        //LOAD IMAGE
        val posterUrlMovie = bundle?.getString("posterUrlMovie")

        val imageView = findViewById<ImageView>(R.id.imageView)
        Picasso.get().load(posterUrlMovie).into(imageView)
    }
}