package agency.five.tmdb.domain.common

data class CastMember(
    val name: String,
    val profilePath: String?,
    val character: String,
    val popularity: Double
)

