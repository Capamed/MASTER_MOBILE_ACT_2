package com.example.datastoreapp

import androidx.lifecycle.ViewModel

class MainActivityViewModel(private val model: Model) : ViewModel() {

    val movies = model.movies

    fun removeMovie(movie: Movie) {
        model.removeMovie(movie)
    }

}