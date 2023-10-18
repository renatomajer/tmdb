package agency.five.tmdb.data.database

import agency.five.tmdb.presentation.ui.components.MovieItemViewState

interface MovieDatabase {

    suspend fun getFavoriteMovies(): List<MovieItemViewState>

    suspend fun saveIsMovieFavorite(movie: MovieItemViewState, isFavorite: Boolean)
}