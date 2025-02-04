package es.rafapuig.movieapp

import android.app.Application
import androidx.room.Room
import es.rafapuig.movieapp.data.MovieRepositoryImpl
import es.rafapuig.movieapp.data.local.MoviesDatabase
import es.rafapuig.movieapp.data.network.NetworkProvider
import es.rafapuig.movieapp.domain.MovieRepository

class MovieApplication : Application() {

    private val API_TOKEN = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI4YjYwYmIwMWM5M2FlZDZjNjQzY" +
            "TlmNThkNWVmNTAzMCIsIm5iZiI6MTczNzU1MDc2Ni45NjcsInN1YiI6IjY3OTBlYmFlMmQ2MWM" +
            "zM2U2M2RmZmI4OCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.FetknARzdQ" +
            "hKbAQXWyxiKAW4tH33zyzZNUIxUAQzbzM"

    lateinit var movieRepository: MovieRepository

    override fun onCreate() {
        super.onCreate()

        val db = Room
            .databaseBuilder(applicationContext, MoviesDatabase::class.java, "movies.db")
            .build()

        movieRepository = MovieRepositoryImpl(
            NetworkProvider(API_TOKEN).getMovieService(), db.movieDao())
    }

}