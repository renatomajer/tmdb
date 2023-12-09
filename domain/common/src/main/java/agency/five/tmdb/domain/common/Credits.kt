package agency.five.tmdb.domain.common

data class Credits(
    val id: Int,
    val cast: List<CastMember>,
    val crew: List<CrewMember>
)
