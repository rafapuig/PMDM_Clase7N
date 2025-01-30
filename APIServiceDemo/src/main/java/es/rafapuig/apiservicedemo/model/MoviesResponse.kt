package es.rafapuig.apiservicedemo.model

import com.squareup.moshi.Json

data class MoviesResponse(
    @Json(name = "page") val page: Int = 1,
    @Json(name = "total_pages") val totalPages: Int = 0,
    @Json(name = "total_results") val totalResults: Int = 0,
    @Json(name = "results") val movieList: List<Movie>
)
