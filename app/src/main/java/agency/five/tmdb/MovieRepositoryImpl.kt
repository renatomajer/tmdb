package agency.five.tmdb

import agency.five.tmdb.ui.components.MovieItemViewState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*

class MovieRepositoryImpl(
    private val movieApi: MovieApi,
    private val movieDatabase: MovieDatabase
) : MovieRepository {

    private object RefreshEvent

    private val refreshMoviesPublisher = MutableSharedFlow<RefreshEvent>(replay = 1)

    private val popularMoviesFlow = refreshMoviesPublisher
        .onStart { refreshMoviesPublisher.emit(RefreshEvent) }
        .map {
            movieApi.getPopularMovies()
        }
        .map { it ->
            val favs = movieDatabase.getFavoriteMovies()
            it.map { list ->
                list.map {
                    var isFavorite = false
                    for (fav in favs) {
                        if (fav.id == it.id) {
                            isFavorite = true
                            break
                        }
                    }
                    it.copy(favorite = isFavorite)
                }
            }
        }
        .shareIn(
            CoroutineScope(Dispatchers.Default),
            SharingStarted.WhileSubscribed(),
            replay = 1
        )

    override fun getPopularMovies(): Flow<List<List<MovieItemViewState>>> = popularMoviesFlow

    private val trendingMoviesFlow = refreshMoviesPublisher
        .onStart { refreshMoviesPublisher.emit(RefreshEvent) }
        .map {
            movieApi.getTrendingMovies()
        }
        .map { it ->
            val favs = movieDatabase.getFavoriteMovies()
            it.map { list ->
                list.map {
                    var isFavorite = false
                    for (fav in favs) {
                        if (fav.id == it.id) {
                            isFavorite = true
                        }
                    }
                    it.copy(favorite = isFavorite)
                }
            }
        }
        .shareIn(
            CoroutineScope(Dispatchers.Default),
            SharingStarted.WhileSubscribed(),
            replay = 1
        )

    override fun getTrendingMovies(): Flow<List<List<MovieItemViewState>>> = trendingMoviesFlow

    private val freeMoviesFlow = refreshMoviesPublisher
        .onStart { refreshMoviesPublisher.emit(RefreshEvent) }
        .map {
            movieApi.getFreeMovies()
        }
        .map { it ->
            val favs = movieDatabase.getFavoriteMovies()
            it.map { list ->
                list.map {
                    var isFavorite = false
                    for (fav in favs) {
                        if (fav.id == it.id) {
                            isFavorite = true
                        }
                    }
                    it.copy(favorite = isFavorite)
                }
            }
        }
        .shareIn(
            CoroutineScope(Dispatchers.Default),
            SharingStarted.WhileSubscribed(),
            replay = 1
        )

    override fun getFreeMovies(): Flow<List<List<MovieItemViewState>>> = freeMoviesFlow

    override fun getMovie(movieId: Int): Flow<MovieItemViewState> {
        return flow { emit(movieApi.getMovie(movieId)) }
    }

    override fun getPersonFunctions(movieId: Int): Flow<List<PersonFunction>> {
        return flow { emit(movieApi.getPersonFunctions(movieId)) }
    }

    override fun getActors(movieId: Int): Flow<List<Actor>> {
        return flow { emit(movieApi.getActors(movieId)) }
    }

    private val favoriteMoviesFlow = refreshMoviesPublisher
        .map {
            movieDatabase.getFavoriteMovies()
        }
        .shareIn(
            CoroutineScope(Dispatchers.Default),
            SharingStarted.WhileSubscribed(),
            replay = 1
        )

    override fun getFavorites(): Flow<List<MovieItemViewState>> = favoriteMoviesFlow

    override suspend fun markMovieFavorite(movie: MovieItemViewState, isFavorite: Boolean) {
        movie.favorite = isFavorite
        movieDatabase.saveIsMovieFavorite(movie, isFavorite)
        refreshMoviesPublisher.emit(RefreshEvent)
    }
}