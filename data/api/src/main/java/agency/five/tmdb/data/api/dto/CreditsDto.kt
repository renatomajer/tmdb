package agency.five.tmdb.data.api.dto

import agency.five.tmdb.domain.common.Credits
import kotlinx.serialization.Serializable

/**
 * Credits Data Transfer Object
 */
@Serializable
data class CreditsDto(
    val id: Int,
    val cast: List<CastMemberDto>,
    val crew: List<CrewMemberDto>
)

fun CreditsDto.toCredits(): Credits {
    return Credits(
        id = id,
        cast = cast.map { it.toCastMember() },
        crew = crew.map { it.toCrewMember() }
    )
}