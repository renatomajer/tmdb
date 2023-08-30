package agency.five.tmdb.domain.repointerfaces


import agency.five.tmdb.domain.common.Actor
import agency.five.tmdb.domain.common.PersonFunction
import agency.five.tmdb.ui.components.MovieItemViewState
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun getPopularMovies(): Flow<List<List<MovieItemViewState>>>

    fun getTrendingMovies(): Flow<List<List<MovieItemViewState>>>

    fun getFreeMovies(): Flow<List<List<MovieItemViewState>>>

    fun getMovie(movieId: Int): Flow<MovieItemViewState>

    fun getPersonFunctions(movieId: Int): Flow<List<PersonFunction>>

    fun getActors(movieId: Int): Flow<List<Actor>>

    fun getFavorites(): Flow<List<MovieItemViewState>>

    suspend fun markMovieFavorite(movie: MovieItemViewState, isFavorite: Boolean)
}