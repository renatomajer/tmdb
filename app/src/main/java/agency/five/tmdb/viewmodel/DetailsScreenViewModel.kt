package agency.five.tmdb.viewmodel


import agency.five.tmdb.domain.common.Credits
import agency.five.tmdb.domain.common.Movie
import agency.five.tmdb.domain.common.MovieDetails
import agency.five.tmdb.domain.common.Resource
import agency.five.tmdb.domain.usecase.GetCreditsUseCase
import agency.five.tmdb.domain.usecase.GetMovieDetailsUseCase
import agency.five.tmdb.domain.usecase.UpdateIsMovieFavoriteUseCase
import agency.five.tmdb.navigation.RootScreen
import agency.five.tmdb.ui.state.CreditsUiState
import agency.five.tmdb.ui.state.MovieDetailsUiState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch


class DetailsScreenViewModel(
    private val getCreditsUseCase: GetCreditsUseCase,
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase,
    private val updateIsMovieFavoriteUseCase: UpdateIsMovieFavoriteUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _movieDetailsState = mutableStateOf(MovieDetailsUiState())
    val movieDetailsState: State<MovieDetailsUiState> = _movieDetailsState

    private val _creditsState = mutableStateOf(CreditsUiState())
    val creditsState: State<CreditsUiState> = _creditsState

    init {
        savedStateHandle.get<Int>(RootScreen.DetailsScreen.ARGUMENT_ID)?.let { movieId ->
            getMovieDetails(movieId)
            getCredits(movieId)
        }
    }

    private fun getMovieDetails(movieId: Int) {
        getMovieDetailsUseCase(movieId).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _movieDetailsState.value = MovieDetailsUiState(movie = result.data)
                }
                is Resource.Error -> {
                    _movieDetailsState.value = MovieDetailsUiState(error = result.message)
                }
                is Resource.Loading -> {
                    _movieDetailsState.value = MovieDetailsUiState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun getCredits(movieId: Int) {
        getCreditsUseCase(movieId).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _creditsState.value = CreditsUiState(credits = result.data)
                }
                is Resource.Error -> {
                    _creditsState.value = CreditsUiState(error = result.message)
                }
                is Resource.Loading -> {
                    _creditsState.value = CreditsUiState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun movieFavoriteButtonClick(updatedMovieDetails: MovieDetails) {
        viewModelScope.launch(Dispatchers.IO) {

            val updatedMovie = Movie(
                id = updatedMovieDetails.id,
                isFavorite = updatedMovieDetails.isFavorite,
                posterPath = updatedMovieDetails.posterPath
            )

            updateIsMovieFavoriteUseCase(updatedMovie)
        }
    }
}