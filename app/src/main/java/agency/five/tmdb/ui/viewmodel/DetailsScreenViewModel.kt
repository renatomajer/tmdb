package agency.five.tmdb.ui.viewmodel

import agency.five.tmdb.Actor
import agency.five.tmdb.MovieApiImpl
import agency.five.tmdb.MovieRepository
import agency.five.tmdb.PersonFunction
import agency.five.tmdb.ui.components.MovieItemViewState
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class DetailsScreenViewModel(
    val id: Int //TODO: removed null
) : ViewModel(), KoinComponent {

/*    private val movie: Flow<MovieItemViewState> = loadMovie(id)
    private val personFunctions: Flow<List<PersonFunction>> = loadPersonFunctions(id)
    private val actors: Flow<List<Actor>> = loadActors(id) //"Top Billed Cast"
    */

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
        //TODO: load movie with id
        return movieRepository.getMovie(movieId)
    }

    private fun loadPersonFunctions(movieId: Int): Flow<List<PersonFunction>> {
        //TODO: load persons for the movie
        return movieRepository.getPersonFunctions(movieId)    //TODO: edit
    }

    private fun loadActors(movieId: Int): Flow<List<Actor>> {
        //TODO: load movie roles
        return movieRepository.getActors(movieId)
    }
}