package es.rafapuig.movieapp.domain

import es.rafapuig.movieapp.domain.model.Movie

interface MovieRepository {
    suspend fun fetchMovies(): List<Movie>
}