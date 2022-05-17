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
    ).onEach { it ->
        val favs = movieDatabase.getFavoriteMovies()
        if (favs.isNotEmpty()) {
            it.forEach { list ->
                list.forEach {
                    var isFavorite = false
                    for (fav in favs) {
                        if (fav.id == it.id) {
                            isFavorite = true
                        }
                    }
                    it.favorite = isFavorite
                }
            }
        }
    }

    private val trendingMoviesInitialFlow = flow {
        emit(movieApi.getTrendingMovies())
    }
        .shareIn(
            CoroutineScope(Dispatchers.Default),
            SharingStarted.WhileSubscribed(),
            replay = 1
        )

    private val trendingMoviesFlow = refreshMoviesPublisher
        .map {
            movieApi.getTrendingMovies()
        }
        .shareIn(
            CoroutineScope(Dispatchers.Default),
            SharingStarted.WhileSubscribed(),
            replay = 1
        )

    override fun getTrendingMovies(): Flow<List<List<MovieItemViewState>>> = merge(
        trendingMoviesInitialFlow,
        trendingMoviesFlow
    ).onEach { it ->
        val favs = movieDatabase.getFavoriteMovies()
        if (favs.isNotEmpty()) {
            it.forEach { list ->
                list.forEach {
                    var isFavorite = false
                    for (fav in favs) {
                        if (fav.id == it.id) {
                            isFavorite = true
                        }
                    }
                    it.favorite = isFavorite
                }
            }
        }
    }

    private val freeMoviesInitialFlow = flow {
        emit(movieApi.getFreeMovies())
    }
        .shareIn(
            CoroutineScope(Dispatchers.Default),
            SharingStarted.WhileSubscribed(),
            replay = 1
        )

    private val freeMoviesFlow = refreshMoviesPublisher
        .map {
            movieApi.getFreeMovies()
        }
        .shareIn(
            CoroutineScope(Dispatchers.Default),
            SharingStarted.WhileSubscribed(),
            replay = 1
        )

    override fun getFreeMovies(): Flow<List<List<MovieItemViewState>>> = merge(
        freeMoviesInitialFlow,
        freeMoviesFlow
    ).onEach { it ->
        val favs = movieDatabase.getFavoriteMovies()
        if (favs.isNotEmpty()) {
            it.forEach { list ->
                list.forEach {
                    var isFavorite = false
                    for (fav in favs) {
                        if (fav.id == it.id) {
                            isFavorite = true
                        }
                    }
                    it.favorite = isFavorite
                }
            }
        }
    }

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
        movie.favorite = isFavorite
        movieDatabase.saveIsMovieFavorite(movie, isFavorite)
        refreshMoviesPublisher.emit(RefreshEvent)
    }
}