package agency.five.tmdb.ui.state

import agency.five.tmdb.domain.common.Movie

data class FavoriteMoviesUiState(
    val isLoading: Boolean = false,
    val movies: List<Movie>? = null,
    val error: String? = null
)