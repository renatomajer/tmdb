package agency.five.tmdb.data.api.dto.movie.details

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LanguageDto(
    @SerialName("english_name")
    val englishName: String,
    @SerialName("iso_639_1")
    val iso6391: String,
    val name: String
)
