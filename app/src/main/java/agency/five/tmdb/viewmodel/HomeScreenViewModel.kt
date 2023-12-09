package agency.five.tmdb.viewmodel

import agency.five.tmdb.domain.common.Movie
import agency.five.tmdb.domain.common.Resource
import agency.five.tmdb.domain.usecase.GetFreeMoviesUseCase
import agency.five.tmdb.domain.usecase.GetPopularMoviesUseCase
import agency.five.tmdb.domain.usecase.GetTrendingMoviesUseCase
import agency.five.tmdb.domain.usecase.UpdateIsMovieFavoriteUseCase
import agency.five.tmdb.ui.state.MoviesUiState
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch


class HomeScreenViewModel(
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase,
    private val getFreeMoviesUseCase: GetFreeMoviesUseCase,
    private val getTrendingMoviesUseCase: GetTrendingMoviesUseCase,
    private val updateIsMovieFavoriteUseCase: UpdateIsMovieFavoriteUseCase
) : ViewModel() {

    private val _popularMoviesState = mutableStateOf(MoviesUiState())
    val popularMoviesState: State<MoviesUiState> = _popularMoviesState

    private val _freeMoviesState = mutableStateOf(MoviesUiState())
    val freeMoviesState: State<MoviesUiState> = _freeMoviesState

    private val _trendingMoviesState = mutableStateOf(MoviesUiState())
    val trendingMoviesState: State<MoviesUiState> = _trendingMoviesState

    private val _isRefreshing = mutableStateOf(false)
    val isRefreshing: State<Boolean> = _isRefreshing

    init {
        getPopularMovies()
        getFreeMovies()
        getTrendingMovies()
    }

    /**
     * Returns a flow containing a list of a list of movies for 'Popular movies' section
     */
    private fun getPopularMovies() {
        getPopularMoviesUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _popularMoviesState.value =
                        MoviesUiState(movies = result.data ?: emptyList())
                }

                is Resource.Error -> {
                    _popularMoviesState.value = MoviesUiState(
                        error = result.message ?: "An unexpected error occurred"
                    )
                }

                is Resource.Loading -> {
                    _popularMoviesState.value = MoviesUiState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    /**
     * Returns a flow containing a list of a list of movies for 'Free to watch' movies section
     */
    private fun getFreeMovies() {
        getFreeMoviesUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _freeMoviesState.value = MoviesUiState(movies = result.data ?: emptyList())
                }

                is Resource.Error -> {
                    _freeMoviesState.value = MoviesUiState(
                        error = result.message ?: "An unexpected error occurred"
                    )
                }

                is Resource.Loading -> {
                    _freeMoviesState.value = MoviesUiState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    /**
     * Returns a flow containing a list of a list of movies for 'Trending movies' section
     */
    private fun getTrendingMovies() {
        getTrendingMoviesUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _trendingMoviesState.value =
                        MoviesUiState(movies = result.data ?: emptyList())
                }

                is Resource.Error -> {
                    _trendingMoviesState.value = MoviesUiState(
                        error = result.message ?: "An unexpected error occurred"
                    )
                }

                is Resource.Loading -> {
                    _trendingMoviesState.value = MoviesUiState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun movieFavoriteButtonClick(updatedMovie: Movie) {
        viewModelScope.launch(Dispatchers.IO) {
            updateIsMovieFavoriteUseCase(updatedMovie)
        }
    }

    /**
     * Refresh ui states by fetching new data from the api
     */
    fun refresh() {
        _isRefreshing.value = true

        viewModelScope.launch {
            delay(1500L)
            _isRefreshing.value = false
        }

        //TODO: Refactor this chunk of code so it does not start new flow collection - possible memory leak
        getPopularMovies()
        getFreeMovies()
        getTrendingMovies()
    }
}