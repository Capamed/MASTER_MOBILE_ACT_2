package com.example.datastoreapp

import android.app.Application
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import com.squareup.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.dsl.module
import timber.log.Timber
import timber.log.Timber.*


@Suppress("unused")
class App : Application() {

    //TODO 7 create the single instance of the animalDataStore
    private val animalsDataStore: DataStore<AnimalStore> by dataStore(
        fileName = "animals.pb",
        serializer = AnimalStoreSerializer
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
                //TODO 8 add the animalDataStore to the DI
                module { single { animalsDataStore } },
                mainModule,
                mainActivityModule
            )
        }

    }
}

val mainModule = module {
    //TODO 9 configure the singleton of the Model with the animalsDataStore
    single { Model(animalsDataStore = get()) }
}

val mainActivityModule = module {
    viewModel { MainActivityViewModel(model = get()) }
}