package agency.five.tmdb.data.api.dto

import agency.five.tmdb.data.api.dto.movie.MovieDto
import kotlinx.serialization.Serializable


@Serializable
data class MoviesResponse(
    val results: List<MovieDto>
)