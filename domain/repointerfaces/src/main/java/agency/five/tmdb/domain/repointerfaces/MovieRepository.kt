package agency.five.tmdb.domain.repointerfaces


import agency.five.tmdb.domain.common.Credits
import agency.five.tmdb.domain.common.Movie
import agency.five.tmdb.domain.common.MovieDetails
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    suspend fun getMovieDetails(movieId: Int): MovieDetails

    suspend fun getCredits(movieId: Int): Credits

    fun getFavorites(): Flow<List<Movie>>

    suspend fun insertFavoriteMovie(movie: Movie)

    suspend fun deleteFavoriteMovie(movie: Movie)

    suspend fun getPopularForRent(): List<Movie>

    suspend fun getPopularInTheaters(): List<Movie>

    suspend fun getPopularStreaming(): List<Movie>

    suspend fun getPopularOnTV(): List<Movie>

    suspend fun getUpcomingMovies(): List<Movie>

    suspend fun getTopRatedMovies(): List<Movie>

    suspend fun getTodayTrendingMovies(): List<Movie>

    suspend fun getThisWeekTrendingMovies(): List<Movie>
}