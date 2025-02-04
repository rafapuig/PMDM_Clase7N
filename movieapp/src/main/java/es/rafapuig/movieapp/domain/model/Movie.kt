package es.rafapuig.movieapp.domain.model

data class Movie(
    val id: Int,
    val title: String,
    val posterPath: String
) {
    val posterURL = "https://image.tmdb.org/t/p/w185/$posterPath"
}
