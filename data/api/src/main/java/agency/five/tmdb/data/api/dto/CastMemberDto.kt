package agency.five.tmdb.data.api.dto

import agency.five.tmdb.domain.common.CastMember
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CastMemberDto(
    val adult: Boolean,
    val gender: Int,
    val id: Int,
    @SerialName("known_for_department")
    val knownForDepartment: String,
    val name: String,
    @SerialName("original_name")
    val originalName: String,
    val popularity: Double,
    @SerialName("profile_path")
    val profilePath: String?,
    @SerialName("cast_id")
    val castId: Int,
    @SerialName("character")
    val character: String,
    @SerialName("credit_id")
    val creditId: String,
    val order: Int
)

fun CastMemberDto.toCastMember(): CastMember {
    return CastMember(
        name = name,
        profilePath = profilePath,
        character = character,
        popularity = popularity
    )
}
