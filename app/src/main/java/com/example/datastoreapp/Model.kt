package com.example.datastoreapp

import androidx.datastore.core.DataStore
import com.github.ajalt.timberkt.d
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

class Model(private val moviesDataStore: DataStore<MovieStore>) {

    companion object {
        val MOVIES = """
[
    {
        "name": "Little Blue Penguin",
        "latin_name": "Eudyptula minor",
        "animal_type": "Bird",
        "active_time": "Nocturnal",
        "length_min": "1.3",
        "length_max": "1.5",
        "weight_min": "3",
        "weight_max": "3.3",
        "lifespan": "25",
        "habitat": "Ocean and coastal shores",
        "diet": "Fish and marine invertebrates",
        "geo_range": "Australia and New Zealand",
        "image_link": "https://upload.wikimedia.org/wikipedia/commons/f/f5/Little_Penguin_Feb09.jpg",
        "id": 106
    },
    {
        "name": "Buff-Cheeked Gibbon",
        "latin_name": "Hylobales gabriellae",
        "animal_type": "Mammal",
        "active_time": "Diurnal",
        "length_min": "1.5",
        "length_max": "2",
        "weight_min": "10",
        "weight_max": "12.5",
        "lifespan": "50",
        "habitat": "Tropical forest",
        "diet": "Fruit, leaves, and invertebrates",
        "geo_range": "Southeast Asia",
        "image_link": "https://upload.wikimedia.org/wikipedia/commons/c/c6/Nomascus_gabriellae_25.JPG",
        "id": 46
    },
    {
        "name": "Fire-bellied newt",
        "latin_name": "Cynops pyrrhogaster pyrrhogaster",
        "animal_type": "Amphibian",
        "active_time": "Nocturnal",
        "length_min": "0.35",
        "length_max": "0.5",
        "weight_min": "0.004",
        "weight_max": "0.004",
        "lifespan": "25",
        "habitat": "Ponds, stream pools and lakes",
        "diet": "Aquatic invertebrates",
        "geo_range": "Japan",
        "image_link": "https://upload.wikimedia.org/wikipedia/commons/3/3f/Cynops_ensicauda_popei.jpg",
        "id": 71
    },
    {
        "name": "White Lion",
        "latin_name": "Panthera leo",
        "animal_type": "Mammal",
        "active_time": "Diurnal",
        "length_min": "5",
        "length_max": "10",
        "weight_min": "270",
        "weight_max": "530",
        "lifespan": "18",
        "habitat": "Savannah,  woodland and desert",
        "diet": "Hoofed mammals, hares, small birds and reptiles",
        "geo_range": "Timbavati, South Africa",
        "image_link": "https://upload.wikimedia.org/wikipedia/commons/2/2f/White_Lion444.jpg",
        "id": 186
    },
    {
        "name": "Ornate Monitor",
        "latin_name": "Varanus ornatus",
        "animal_type": "Reptile",
        "active_time": "Diurnal",
        "length_min": "6",
        "length_max": "6.5",
        "weight_min": "11",
        "weight_max": "13",
        "lifespan": "13",
        "habitat": "Woodland, savanna, scrubland, swamps, lakes, and rivers",
        "diet": "Insects, fish, frogs, reptiles, birds, mammals, and carrion",
        "geo_range": "Central and Southern Africa",
        "image_link": "https://upload.wikimedia.org/wikipedia/commons/9/90/OrnateMonitor.jpg",
        "id": 129
    },
    {
        "name": "Common Eider",
        "latin_name": "Somateria mollissima",
        "animal_type": "Bird",
        "active_time": "Diurnal",
        "length_min": "1.7",
        "length_max": "2",
        "weight_min": "4",
        "weight_max": "4.5",
        "lifespan": "20",
        "habitat": "Marine waters near rocky seacoasts",
        "diet": "Fish, crustaceans, small animals",
        "geo_range": "Northern coasts of Europe, North America and eastern Siberia",
        "image_link": "https://upload.wikimedia.org/wikipedia/commons/c/cd/Common_eider_male_at_Jones_Beach_%2804702%29.jpg",
        "id": 56
    },
    {
        "name": "Fairy Bluebird",
        "latin_name": "Irena puella",
        "animal_type": "Bird",
        "active_time": "Diurnal",
        "length_min": "0.75",
        "length_max": "0.9",
        "weight_min": "0.03",
        "weight_max": "0.18",
        "lifespan": "12",
        "habitat": "Forest",
        "diet": "Fruit, nectar, and insects",
        "geo_range": "South and Southeast Asia",
        "image_link": "https://upload.wikimedia.org/wikipedia/commons/3/3f/Fairy_bluebird_male_-_Irena_puella.jpg",
        "id": 69
    },
    {
        "name": "White-throated Monitor",
        "latin_name": "Varanus albigularis",
        "animal_type": "Reptile",
        "active_time": "Diurnal",
        "length_min": "3.3",
        "length_max": "6",
        "weight_min": "20",
        "weight_max": "25",
        "lifespan": "20",
        "habitat": "Savanna",
        "diet": "Birds, insects, snails, and invertebrates",
        "geo_range": "Central and Southern Africa",
        "image_link": "https://upload.wikimedia.org/wikipedia/commons/0/0e/Rock_monitor%2C_Varanus_albigularis%2C_also_called_commonly_the_white-throated_monitor_or_leguaan._%2848206382456%29.jpg",
        "id": 192
    },
    {
        "name": "Mini-Juliana Pig",
        "latin_name": "Sus domesticus",
        "animal_type": "Mammal",
        "active_time": "Diurnal",
        "length_min": "2",
        "length_max": "2.2",
        "weight_min": "35",
        "weight_max": "55",
        "lifespan": "18",
        "habitat": "Domestic habitat",
        "diet": "Pellets, fruits and veggies",
        "geo_range": "Raised domestically around the world",
        "image_link": "https://americanminipigassociation.com/wp-content/uploads/2015/03/image-3-e1426360082120.jpeg",
        "id": 119
    },
    {
        "name": "Madagascar Giant Day Gecko",
        "latin_name": "Phelsuma madagascariensis grandis",
        "animal_type": "Reptile",
        "active_time": "Diurnal",
        "length_min": "0.75",
        "length_max": "1",
        "weight_min": "0.13",
        "weight_max": "0.15",
        "lifespan": "15",
        "habitat": "Forest",
        "diet": "Insects and other small animals, fruit, and honey",
        "geo_range": "Northern Madagascar",
        "image_link": "https://upload.wikimedia.org/wikipedia/commons/8/8b/Madagascar_giant_day_gecko_%28Phelsuma_grandis%29_Nosy_Komba.jpg",
        "id": 109
    },
    {
        "name": "Black-and-White Colobus Monkey",
        "latin_name": "Colobus guereza",
        "animal_type": "Mammal",
        "active_time": "Diurnal",
        "length_min": "1.7",
        "length_max": "1.9",
        "weight_min": "18",
        "weight_max": "30",
        "lifespan": "14",
        "habitat": "Forest",
        "diet": "Primarily leaves, some fruit",
        "geo_range": "Central and Eastern Africa",
        "image_link": "https://upload.wikimedia.org/wikipedia/commons/6/6b/Colubusmonkey.JPG",
        "id": 34
    },
    {
        "name": "Andean Bear",
        "latin_name": "Tremarctos ornatus",
        "animal_type": "Mammal",
        "active_time": "Nocturnal",
        "length_min": "4.3",
        "length_max": "6.6",
        "weight_min": "220",
        "weight_max": "440",
        "lifespan": "26",
        "habitat": "Mountain forests, grasslands, and scrub dessert",
        "diet": "Primarily fruits, bromeliads, bamboo, cactus and other plants, some small animals",
        "geo_range": "Northwestern South America",
        "image_link": "https://upload.wikimedia.org/wikipedia/commons/f/fa/Tremarctos_ornatus_25.jpg",
        "id": 13
    },
    {
        "name": "African Pygmy Falcon",
        "latin_name": "Polihierax semitorquatus",
        "animal_type": "Bird",
        "active_time": "Diurnal",
        "length_min": "0.6",
        "length_max": "0.7",
        "weight_min": "0.1",
        "weight_max": "0.16",
        "lifespan": "7",
        "habitat": "Savannah",
        "diet": "Insects, lizards, birds and rodents",
        "geo_range": "Eastern and Southern Africa",
        "image_link": "https://upload.wikimedia.org/wikipedia/commons/7/74/African_Pygmy_Falcon_Female.jpg",
        "id": 6
    },
    {
        "name": "Fire-bellied newt",
        "latin_name": "Cynops pyrrhogaster pyrrhogaster",
        "animal_type": "Amphibian",
        "active_time": "Nocturnal",
        "length_min": "0.35",
        "length_max": "0.5",
        "weight_min": "0.004",
        "weight_max": "0.004",
        "lifespan": "25",
        "habitat": "Ponds, stream pools and lakes",
        "diet": "Aquatic invertebrates",
        "geo_range": "Japan",
        "image_link": "https://upload.wikimedia.org/wikipedia/commons/3/3f/Cynops_ensicauda_popei.jpg",
        "id": 71
    },
    {
        "name": "Mini-Juliana Pig",
        "latin_name": "Sus domesticus",
        "animal_type": "Mammal",
        "active_time": "Diurnal",
        "length_min": "2",
        "length_max": "2.2",
        "weight_min": "35",
        "weight_max": "55",
        "lifespan": "18",
        "habitat": "Domestic habitat",
        "diet": "Pellets, fruits and veggies",
        "geo_range": "Raised domestically around the world",
        "image_link": "https://americanminipigassociation.com/wp-content/uploads/2015/03/image-3-e1426360082120.jpeg",
        "id": 119
    },
    {
        "name": "Pascagoula Map Turtle",
        "latin_name": "Graptemys gibbonsi",
        "animal_type": "Reptile",
        "active_time": "Diurnal",
        "length_min": "0.42",
        "length_max": "0.96",
        "weight_min": "0.75",
        "weight_max": "6.94",
        "lifespan": "40",
        "habitat": "Creeks and rivers",
        "diet": "Insects, snails, and clams",
        "geo_range": "Pascagoula River system, Mississippi ",
        "image_link": "https://upload.wikimedia.org/wikipedia/commons/1/1c/Graptemys.gibbonsi.jpg",
        "id": 134
    },
    {
        "name": "Greater Flamingo",
        "latin_name": "Phoenicopterus roseus",
        "animal_type": "Bird",
        "active_time": "Diurnal",
        "length_min": "3.5",
        "length_max": "5",
        "weight_min": "6",
        "weight_max": "7",
        "lifespan": "35",
        "habitat": "Wetlands",
        "diet": "Seeds, algae, and small invertebrates",
        "geo_range": "Africa, Southwest Europe, and Asia",
        "image_link": "https://upload.wikimedia.org/wikipedia/commons/a/a9/Flamant_rose_Salines_de_Thyna.jpg",
        "id": 78
    },
    {
        "name": "Red-eared Slider",
        "latin_name": "Trachemys scripta elegans",
        "animal_type": "Reptile",
        "active_time": "Diurnal",
        "length_min": "0.42",
        "length_max": "0.92",
        "weight_min": "1.5",
        "weight_max": "2",
        "lifespan": "20",
        "habitat": "Freshwater ponds and streams",
        "diet": "Fish, mollusks, and insects",
        "geo_range": "Southern and Midwestern United States",
        "image_link": "https://upload.wikimedia.org/wikipedia/commons/9/96/RedEaredSlider05.jpg",
        "id": 149
    },
    {
        "name": "King Penguin",
        "latin_name": "Aptenodytes patagonius",
        "animal_type": "Bird",
        "active_time": "Diurnal",
        "length_min": "2.8",
        "length_max": "3.1",
        "weight_min": "20",
        "weight_max": "45",
        "lifespan": "25",
        "habitat": "Ocean and islands",
        "diet": "Squid, crustaceans, and fish",
        "geo_range": "Sub-Antarctic islands",
        "image_link": "https://upload.wikimedia.org/wikipedia/commons/b/be/SGI-2016-South_Georgia_%28Fortuna_Bay%29%E2%80%93King_penguin_%28Aptenodytes_patagonicus%29_04.jpg",
        "id": 99
    },
    {
        "name": "Black-footed Cat",
        "latin_name": "Felis nigripes",
        "animal_type": "Mammal",
        "active_time": "Nocturnal",
        "length_min": "1.1",
        "length_max": "1.7",
        "weight_min": "3.3",
        "weight_max": "6.5",
        "lifespan": "5",
        "habitat": "Desert, savannah, and scrubland",
        "diet": "Mice, insects, spiders, lizards, and birds",
        "geo_range": "Southern Africa",
        "image_link": "https://upload.wikimedia.org/wikipedia/commons/d/da/Zoo_Wuppertal_Schwarzfusskatze.jpg",
        "id": 36
    }
]
        """.trimIndent()
    }

    private val coroutineScope = CoroutineScope(Job() + Dispatchers.Default)

    private val _movies = MutableStateFlow<List<Movie>>(listOf())
    val movies = _movies as StateFlow<List<Movie>>

    init {
        coroutineScope.launch {
            //TODO 10 use the moviesDataSTore as will (with coroutines)
            moviesDataStore.data
                .map { it.initialized }
                .filter { !it }
                .first {
                    d { "Initialize data store..." }
                    initDataStore()
                    return@first true
                }
        }

        coroutineScope.launch {
            moviesDataStore.data
                .collect { movieStore ->
                    d { "Animals count: ${movieStore.moviesCount}" }
                    val movies = movieStore.moviesList.map { Movie.fromStoredMovie(it) }
                    _movies.emit(movies)
                }
        }

    }

    private fun initDataStore() {
        // create moshi parser
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
        val type = Types.newParameterizedType(List::class.java, Movie::class.java)
        val adapter = moshi.adapter<List<Movie>>(type)

        // read the json
        val moviesFromJson: List<Movie> = adapter.fromJson(Model.MOVIES)!!

        // create the storedAnimals list
        val moviesToStore = moviesFromJson.map { it.asStoredMovie() }

        // save data to data store
        coroutineScope.launch {
            moviesDataStore.updateData { movieStore ->
                movieStore.toBuilder()
                    .addAllMovies(moviesToStore)
                    .setInitialized(true)
                    .build()
            }
        }

    }

    fun removeMovie(movie: Movie) {
        val toStoreAnimals = movies.value
            .filter { it.id != movie.id }
            .map { it.asStoredMovie() }

        coroutineScope.launch {
            moviesDataStore.updateData { movieStore ->
                movieStore.toBuilder()
                    .clearMovies()
                    .addAllMovies(toStoreAnimals)
                    .build()
            }
        }
    }

}