package agency.five.tmdb

import agency.five.tmdb.ui.components.MovieItemViewState

interface MovieDatabase {

    suspend fun getFavoriteMovies(): List<MovieItemViewState>

    suspend fun saveIsMovieFavorite(movie: MovieItemViewState, isFavorite: Boolean)
}