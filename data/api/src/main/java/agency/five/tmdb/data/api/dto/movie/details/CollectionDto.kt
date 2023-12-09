package agency.five.tmdb.data.api.dto.movie.details

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CollectionDto(
    val id: Int,
    val name: String,
    @SerialName("poster_path")
    val posterPath: String?,
    @SerialName("backdrop_path")
    val backdropPath: String?
)
