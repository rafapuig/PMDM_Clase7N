package es.rafapuig.movieapp.data.network.model

import com.squareup.moshi.Json

data class Movie(
    val adult: Boolean = false,
    @Json(name = "backdrop_path") val backdropPath: String = "",
    @Json(name = "genre_ids") val genreIds: List<Int> = emptyList(),
    val id: Int = 0,
    @Json(name = "original_language") val originalLanguage: String = "",
    @Json(name = "original_title") val originalTitle: String = "",
    val overview: String = "",
    val popularity: Double = 0.0,
    @Json(name = "poster_path") val posterPath: String = "",
    @Json(name = "release_date") val releaseDate: String = "",
    val title: String = "",
    val video: Boolean = false,
    @Json(name = "vote_average") val voteAverage: Double = 0.0,
    @Json(name = "vote_count") val voteCount: Int = 0
)