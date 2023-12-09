package agency.five.tmdb.viewmodel

import agency.five.tmdb.domain.common.Movie
import agency.five.tmdb.domain.common.Resource
import agency.five.tmdb.domain.usecase.GetFavoriteMoviesUseCase
import agency.five.tmdb.domain.usecase.UpdateIsMovieFavoriteUseCase
import agency.five.tmdb.ui.state.FavoriteMoviesUiState
import agency.five.tmdb.ui.state.MoviesUiState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch


class FavoritesScreenViewModel(
    private val getFavoriteMoviesUseCase: GetFavoriteMoviesUseCase,
    private val updateIsMovieFavoriteUseCase: UpdateIsMovieFavoriteUseCase
) : ViewModel() {

    private val _favoriteMoviesState = mutableStateOf(FavoriteMoviesUiState())
    val favoriteMoviesState: State<FavoriteMoviesUiState> = _favoriteMoviesState

    init {
        getFavoriteMovies()
    }

    private fun getFavoriteMovies() {
        getFavoriteMoviesUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _favoriteMoviesState.value =
                        FavoriteMoviesUiState(movies = result.data ?: emptyList())
                }
                is Resource.Error -> {
                    _favoriteMoviesState.value = FavoriteMoviesUiState(
                        error = result.message
                    )
                }
                is Resource.Loading -> {
                    _favoriteMoviesState.value = FavoriteMoviesUiState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun movieFavoriteButtonClick(updatedMovie: Movie) {
        viewModelScope.launch(Dispatchers.IO) {
            updateIsMovieFavoriteUseCase(updatedMovie)
        }
    }
}
