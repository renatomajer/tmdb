package agency.five.tmdb

import agency.five.tmdb.ui.components.MovieItemViewState

class MovieDatabaseImpl() : MovieDatabase {
    private val favoriteMovies = mutableListOf<MovieItemViewState>()

    override suspend fun getFavoriteMovies(): List<MovieItemViewState> {
        return favoriteMovies.reversed()
    }

    override suspend fun saveIsMovieFavorite(movie: MovieItemViewState, isFavorite: Boolean) {
        if (isFavorite) {
            if (!favoriteMovies.contains(movie)) {
                favoriteMovies.add(movie)
            }
        } else {
            favoriteMovies.remove(movie)
        }
    }
}