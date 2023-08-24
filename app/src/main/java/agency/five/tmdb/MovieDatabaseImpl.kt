package agency.five.tmdb

import agency.five.tmdb.ui.components.MovieItemViewState
import android.util.Log

class MovieDatabaseImpl() : MovieDatabase {
    private val favoriteMovies = mutableListOf<MovieItemViewState>()

    override suspend fun getFavoriteMovies(): List<MovieItemViewState> {
        return favoriteMovies.reversed()
    }

    override suspend fun saveIsMovieFavorite(movie: MovieItemViewState, isFavorite: Boolean) {
        // isFavorite - new value
        var index = -1
        for (fav in favoriteMovies) {
            if (fav.id == movie.id) {
                index = favoriteMovies.indexOf(fav)
            }
        }

        if (index != -1) {
            favoriteMovies.removeAt(index)
        } else {
            favoriteMovies.add(movie)
        }
    }
}