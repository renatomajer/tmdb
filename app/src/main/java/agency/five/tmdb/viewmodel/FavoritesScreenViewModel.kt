package agency.five.tmdb.viewmodel

import agency.five.tmdb.domain.repointerfaces.MovieRepository
import agency.five.tmdb.ui.components.MovieItemViewState
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject


class FavoritesScreenViewModel() : ViewModel(), KoinComponent {

    private val movieRepository: MovieRepository by inject()

    private val favoriteMovies: Flow<List<MovieItemViewState>> = loadFavoriteMovies()

    fun getFavoriteMovies(): Flow<List<MovieItemViewState>> {
        return favoriteMovies
    }

    private fun loadFavoriteMovies(): Flow<List<MovieItemViewState>> {
        return movieRepository.getFavorites()
    }

    fun movieFavoriteButtonClick(movie: MovieItemViewState, isFavorite: Boolean) {
        CoroutineScope(Dispatchers.Default).launch {
            movieRepository.markMovieFavorite(movie, isFavorite)
        }
    }
}