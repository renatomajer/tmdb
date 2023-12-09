package agency.five.tmdb.data.api.dto.movie

import agency.five.tmdb.domain.common.Movie
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Movie Data Transfer Object
 */
@Serializable
data class MovieDto(
    val id: Int,
    val title: String,
    @SerialName("genre_ids")
    val genreIds: List<Int>,
    val overview: String,
    @SerialName("release_date")
    val releaseDate: String,
    @SerialName("vote_average")
    val voteAverage: Double,
    @SerialName("original_language")
    val originalLanguage: String,
    @SerialName("poster_path")
    val posterPath: String?,
    val popularity: Double
)

fun MovieDto.toMovie(): Movie {
    return Movie(
        id = id,
        posterPath = posterPath,
        isFavorite = false
    )
}
