package es.rafapuig.movieapp.data

import es.rafapuig.movieapp.data.network.model.MovieResponse
import es.rafapuig.movieapp.domain.model.Movie

fun MovieResponse.toDomain(): Movie =
    Movie(id, title, posterPath)