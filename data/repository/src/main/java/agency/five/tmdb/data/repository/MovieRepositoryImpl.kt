package agency.five.tmdb.data.repository


import agency.five.tmdb.data.api.MovieApi
import agency.five.tmdb.data.api.dto.movie.details.toMovieDetails
import agency.five.tmdb.data.api.dto.movie.toMovie
import agency.five.tmdb.data.api.dto.toCredits
import agency.five.tmdb.data.database.IFavoriteMoviesDao
import agency.five.tmdb.data.database.mapper.MovieMapper
import agency.five.tmdb.domain.common.Credits
import agency.five.tmdb.domain.common.Movie
import agency.five.tmdb.domain.common.MovieDetails
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MovieRepositoryImpl(
    private val movieApi: MovieApi,
    private val favoriteMoviesDao: IFavoriteMoviesDao,
    private val movieMapper: MovieMapper
) : agency.five.tmdb.domain.repointerfaces.MovieRepository {

    override suspend fun getMovieDetails(movieId: Int): MovieDetails {
        return movieApi.getMovieDetails(movieId).toMovieDetails()
    }

    override suspend fun getCredits(movieId: Int): Credits {
        val creditsDto = movieApi.getCredits(movieId)

        return creditsDto.toCredits()
    }

    override fun getFavorites(): Flow<List<Movie>> {
        val favoriteMoviesEntities = favoriteMoviesDao.getAll()

        return favoriteMoviesEntities.map { movieMapper.fromEntities(it) }
    }

    override suspend fun insertFavoriteMovie(movie: Movie) {
        val entity = movieMapper.toEntity(movie)

        favoriteMoviesDao.insertMovie(entity)
    }

    override suspend fun deleteFavoriteMovie(movie: Movie) {
        val entity = movieMapper.toEntity(movie)

        favoriteMoviesDao.deleteMovie(entity)
    }

    override suspend fun getPopularForRent(): List<Movie> {
        val moviesDto = movieApi.getPopularForRent().results

        return moviesDto.map { it.toMovie() }
    }

    override suspend fun getPopularInTheaters(): List<Movie> {
        val moviesDto = movieApi.getPopularInTheaters().results

        return moviesDto.map { it.toMovie() }
    }

    override suspend fun getPopularStreaming(): List<Movie> {
        val moviesDto = movieApi.getPopularStreaming().results

        return moviesDto.map { it.toMovie() }
    }

    override suspend fun getPopularOnTV(): List<Movie> {
        val moviesDto = movieApi.getPopularOnTV().results

        return moviesDto.map { it.toMovie() }
    }

    override suspend fun getUpcomingMovies(): List<Movie> {
        val moviesDto = movieApi.getUpcomingMovies().results

        return moviesDto.map { it.toMovie() }
    }

    override suspend fun getTopRatedMovies(): List<Movie> {
        val moviesDto = movieApi.getTopRatedMovies().results

        return moviesDto.map { it.toMovie() }
    }

    override suspend fun getTodayTrendingMovies(): List<Movie> {
        val moviesDto = movieApi.getTodayTrendingMovies().results

        return moviesDto.map { it.toMovie() }
    }

    override suspend fun getThisWeekTrendingMovies(): List<Movie> {
        val moviesDto = movieApi.getThisWeekTrendingMovies().results

        return moviesDto.map { it.toMovie() }
    }
}