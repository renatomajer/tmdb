package agency.five.tmdb.presentation.viewmodel


import agency.five.tmdb.domain.common.Actor
import agency.five.tmdb.domain.common.PersonFunction
import agency.five.tmdb.domain.repointerfaces.MovieRepository
import agency.five.tmdb.presentation.ui.components.MovieItemViewState
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.Flow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject


class DetailsScreenViewModel(
    val id: Int //TODO: removed null
) : ViewModel(), KoinComponent {

    private val movieRepository: MovieRepository by inject()

    //TODO: refactor
    fun getMovie(movieId: Int): Flow<MovieItemViewState> {
        return loadMovie(movieId = movieId)
    }

    fun getPersonFunctions(movieId: Int): Flow<List<PersonFunction>> {
        return loadPersonFunctions(movieId)
    }

    fun getActors(movieId: Int): Flow<List<Actor>> {
        return loadActors(movieId)
    }

    private fun loadMovie(movieId: Int): Flow<MovieItemViewState> {
        return movieRepository.getMovie(movieId)
    }

    private fun loadPersonFunctions(movieId: Int): Flow<List<PersonFunction>> {
        return movieRepository.getPersonFunctions(movieId)
    }

    private fun loadActors(movieId: Int): Flow<List<Actor>> {
        return movieRepository.getActors(movieId)
    }
}