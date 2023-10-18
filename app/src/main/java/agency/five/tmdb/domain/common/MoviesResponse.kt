package agency.five.tmdb.domain.common

import agency.five.tmdb.presentation.ui.components.MovieItemViewState
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class MoviesResponse(
    @SerialName("results")
    val movies: List<MovieItemViewState>
)
