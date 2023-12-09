package agency.five.tmdb.data.api.dto.movie.details

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CountryDto(
    @SerialName("iso_3166_1")
    val iso31661: String,
    val name: String
)
