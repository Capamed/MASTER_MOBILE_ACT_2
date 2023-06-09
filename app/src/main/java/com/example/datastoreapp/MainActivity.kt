package com.example.datastoreapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.RecyclerView
import com.github.ajalt.timberkt.d
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: MainActivityViewModel by viewModel()

    private lateinit var movieAdapter: MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        d { "onCreate" }

        // crate the adapter
        movieAdapter = MovieAdapter(
            movieSelected = {
                d { "Selected movie $it!!!" }
                selectedMovie(it)
            },
            removeMovie = {
                d { "Remove movie $it !!!" }
                removeMovie(it)
            }
        )

        // set the adapter
        findViewById<RecyclerView>(R.id.movieList).adapter = movieAdapter

        // subscribe to data changes
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.RESUMED){

                // collect list of Movie's
                viewModel.movies.collect {
                    // submit list
                    movieAdapter.submitList(it)
                }

            }
        }

    }

    private fun removeMovie(movie: Movie) {
        viewModel.removeMovie(movie)
    }

    private fun selectedMovie(movie: Movie){
        val intent = Intent(this, ShowDataActivity:: class.java);
        intent.putExtra("tittleMovie",movie.name);
        intent.putExtra("releaseMovie",movie.release);
        intent.putExtra("playtimeMovie",movie.playtime);
        intent.putExtra("plotMovie",movie.plot);
        intent.putExtra("descriptionMovie",movie.description);
        intent.putExtra("gifUrlMovie",movie.gifUrl);
        intent.putExtra("posterUrlMovie",movie.posterUrl);
        startActivity(intent);
    }
}