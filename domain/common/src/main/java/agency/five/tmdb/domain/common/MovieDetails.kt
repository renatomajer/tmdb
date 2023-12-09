package agency.five.tmdb.domain.common


data class MovieDetails(
    val id: Int,
    val title: String,
    val overview: String,
    val isFavorite: Boolean,
    val releaseDate: String,
    val voteAverage: Double,
    val originalLanguage: String,
    val runtime: Int?,
    val posterPath: String?,
    val genres: List<Genre>
)
