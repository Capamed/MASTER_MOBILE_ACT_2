package com.example.datastoreapp

import com.squareup.moshi.Json

data class Movie(
    val id: Int,
    val name: String,
    val release: String,
    val playtime: String,
    val description: String,
    val plot: String,
    @Json(name = "poster")
    val posterUrl: String,
    @Json(name = "gif")
    val gifUrl: String
) {
    companion object {
        fun fromStoredMovie(storedMovie: StoredMovie): Movie {
            return Movie(
                storedMovie.id,
                storedMovie.name,
                storedMovie.release,
                storedMovie.playtime,
                storedMovie.description,
                storedMovie.plot,
                storedMovie.posterUrl,
                storedMovie.gifUrl
            )
        }

    }

    fun asStoredMovie(): StoredMovie{
        return StoredMovie.newBuilder()
            .setId(id)
            .setName(name)
            .setRelease(release)
            .setPlaytime(playtime)
            .setDescription(description)
            .setPlot(plot)
            .setPosterUrl(posterUrl)
            .setGifUrl(gifUrl)
            .build()
    }
}


