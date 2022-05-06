package agency.five.tmdb

import agency.five.tmdb.ui.components.MovieItemViewState
import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*

class MovieRepositoryImpl(
    private val movieApi: MovieApi,
    private val movieDatabase: MovieDatabase
) : MovieRepository {

    private object RefreshEvent

    private val refreshMoviesPublisher = MutableSharedFlow<RefreshEvent>(replay = 1)

    private val popularMoviesInitialFlow = flow {
        emit(movieApi.getPopularMovies())
    }.shareIn(
        CoroutineScope(Dispatchers.Default),
        SharingStarted.WhileSubscribed(),
        replay = 1
    )

    private val popularMoviesFlow = refreshMoviesPublisher
        .map {
            movieApi.getPopularMovies()
        }
        .shareIn(
            CoroutineScope(Dispatchers.Default),
            SharingStarted.WhileSubscribed(),
            replay = 1
        )


    override fun getPopularMovies(): Flow<List<List<MovieItemViewState>>> = merge(
        popularMoviesInitialFlow,
        popularMoviesFlow
    )

    private val trendingMoviesInitialFlow = flow {
        emit(movieApi.getPopularMovies())
    }
        .shareIn(
            CoroutineScope(Dispatchers.Default),
            SharingStarted.WhileSubscribed(),
            replay = 1
        )

    private val trendingMoviesFlow = refreshMoviesPublisher
        .map {
            movieApi.getPopularMovies()
        }
        .shareIn(
            CoroutineScope(Dispatchers.Default),
            SharingStarted.WhileSubscribed(),
            replay = 1
        )

    override fun getTrendingMovies(): Flow<List<List<MovieItemViewState>>> = merge(
        trendingMoviesInitialFlow,
        trendingMoviesFlow
    )

    private val freeMoviesInitialFlow = flow {
        emit(movieApi.getPopularMovies())
    }
        .shareIn(
            CoroutineScope(Dispatchers.Default),
            SharingStarted.WhileSubscribed(),
            replay = 1
        )

    private val freeMoviesFlow = refreshMoviesPublisher
        .map {
            movieApi.getPopularMovies()
        }
        .shareIn(
            CoroutineScope(Dispatchers.Default),
            SharingStarted.WhileSubscribed(),
            replay = 1
        )

    override fun getFreeMovies(): Flow<List<List<MovieItemViewState>>> = merge(
        freeMoviesInitialFlow,
        freeMoviesFlow
    )

    override fun getMovie(movieId: Int): Flow<MovieItemViewState> {
        return flow { emit(movieApi.getMovie(movieId)) }
    }

    override fun getPersonFunctions(movieId: Int): Flow<List<PersonFunction>> {
        return flow { emit(movieApi.getPersonFunctions(movieId)) }
    }

    override fun getActors(movieId: Int): Flow<List<Actor>> {
        return flow { emit(movieApi.getActors(movieId)) }
    }


    private val favouriteMoviesFlow = refreshMoviesPublisher
        .map {
            movieDatabase.getFavoriteMovies()
        }
        .shareIn(
            CoroutineScope(Dispatchers.Default),
            SharingStarted.WhileSubscribed(),
            replay = 1
        )

    override fun getFavorites(): Flow<List<MovieItemViewState>> = favouriteMoviesFlow

    override suspend fun markMovieFavorite(movie: MovieItemViewState, isFavorite: Boolean) {
        movieDatabase.saveIsMovieFavorite(movie, isFavorite)
        movieApi.updateMovie(movie, isFavorite) // update movie mocked in MovieApi
        refreshMoviesPublisher.emit(RefreshEvent)
    }
}