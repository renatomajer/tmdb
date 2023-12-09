package agency.five.tmdb.data.api.dto.movie.details

import agency.five.tmdb.domain.common.Genre
import kotlinx.serialization.Serializable

@Serializable
data class GenreDto(
    val id: Int,
    val name: String
)

fun GenreDto.toGenre(): Genre {
    return Genre(
        id = id,
        name = name
    )
}