package com.example.datastoreapp

import com.squareup.moshi.Json

data class Movie(
    val id: Int,
    val name: String,
    @Json(name = "latin_name")
    val latinName: String,
    @Json(name = "geo_range")
    val geoRange: String,
    val diet: String,
    @Json(name = "image_link")
    val imageUrl: String
) {
    companion object {
        fun fromStoredMovie(storedAnimal: StoredMovie): Movie {
            return Movie(
                storedAnimal.id,
                storedAnimal.name,
                storedAnimal.latinName,
                storedAnimal.geoRange,
                storedAnimal.diet,
                storedAnimal.imageUrl
            )
        }

    }

    fun asStoredMovie(): StoredMovie{
        return StoredMovie.newBuilder()
            .setId(id)
            .setName(name)
            .setLatinName(latinName)
            .setGeoRange(geoRange)
            .setImageUrl(imageUrl)
            .build()
    }
}


