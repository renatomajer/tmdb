package agency.five.tmdb.ui.state

import agency.five.tmdb.domain.common.Movie

data class MoviesUiState(
    val isLoading: Boolean = false,
    val movies: List<List<Movie>>? = null,
    val error: String? = null
)
