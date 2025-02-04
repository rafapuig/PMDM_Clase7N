package es.rafapuig.movieapp.data

import es.rafapuig.movieapp.data.local.entity.MovieEntity
import es.rafapuig.movieapp.data.network.model.MovieResponse
import es.rafapuig.movieapp.domain.model.Movie

fun MovieResponse.toDomain(): Movie =
    Movie(id, title, posterPath)

fun MovieResponse.toDatabase(): MovieEntity =
    MovieEntity(id, title, posterPath)

fun MovieEntity.toDomain() : Movie =
    Movie(id,title, posterPath)