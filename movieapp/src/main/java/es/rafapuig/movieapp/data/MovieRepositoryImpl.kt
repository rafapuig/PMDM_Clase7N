package es.rafapuig.movieapp.data

import android.util.Log
import es.rafapuig.movieapp.data.local.dao.MovieDao
import es.rafapuig.movieapp.data.network.api.MovieService
import es.rafapuig.movieapp.domain.MovieRepository
import es.rafapuig.movieapp.domain.model.Movie

import retrofit2.HttpException

class MovieRepositoryImpl(
    private val movieService: MovieService, val movieDao: MovieDao) : MovieRepository {

    override suspend fun fetchMovies(): List<Movie> {

        try {
            val moviesResponse = movieService.getNowPlayingMovies()
            val movieEntities = moviesResponse.movieList.map { it.toDatabase() }
            movieDao.insertAll(movieEntities)
            return movieDao.getAll().map { it.toDomain() }
            //return moviesResponse.movieList.map { it.toDomain() }

        } catch (ex: HttpException) {
            Log.e("ERROR","Error de red")
        }
        return emptyList()
    }
}