package es.rafapuig.movieapp.data

import android.util.Log
import es.rafapuig.movieapp.data.network.api.MovieService
import es.rafapuig.movieapp.data.network.model.Movie
import retrofit2.HttpException

class MovieRepository(private val movieService: MovieService) {

    suspend fun fetchMovies(): List<Movie> {

        try {
            val moviesResponse = movieService.getNowPlayingMovies()
            return moviesResponse.movieList

        } catch (ex: HttpException) {
            Log.e("ERROR","Error de red")
        }
        return emptyList()
    }
}