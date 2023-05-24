package com.example.datastoreapp

import android.app.Application
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import com.squareup.moshi.Moshi
import com.squareup.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import timber.log.Timber
import timber.log.Timber.*
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory


@Suppress("unused")
class App : Application() {

    //TODO 7 create the single instance of the movieDataStore
    private val moviesDataStore: DataStore<MovieStore> by dataStore(
        fileName = "movies.pb",
        serializer = MovieStoreSerializer
    )

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(DebugTree())
        }

        // picasso
        val picasso: Picasso =
            Picasso.Builder(this).downloader(OkHttp3Downloader(cacheDir, 250000000)).build()
        Picasso.setSingletonInstance(picasso)

        //koin
        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@App)
            modules(
                //TODO 8 add the movieDataStore to the DI
                module { single { moviesDataStore } },
                mainModule,
                mainActivityModule
            )
        }

    }
}

val mainModule = module {
    single {
        Retrofit.Builder()
            .baseUrl("https://gist.githubusercontent.com/")
            .addConverterFactory(
               MoshiConverterFactory.create(
                   Moshi.Builder()
                       .add(KotlinJsonAdapterFactory())
                       .build()
               )
            )
            .build()
            .create(MovieInterface::class.java)
    }

    //TODO 9 configure the singleton of the Model with the moviesDataStore
    single { Model(moviesDataStore = get(), movieInterface = get()) }
}

val mainActivityModule = module {
    viewModel { MainActivityViewModel(model = get()) }
}