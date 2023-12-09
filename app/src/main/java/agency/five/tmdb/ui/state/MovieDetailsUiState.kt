package agency.five.tmdb.ui.state

import agency.five.tmdb.domain.common.MovieDetails

data class MovieDetailsUiState(
    val isLoading: Boolean = false,
    val movie: MovieDetails? = null,
    val error: String? = null
)