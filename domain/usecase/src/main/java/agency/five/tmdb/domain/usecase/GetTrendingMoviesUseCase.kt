package agency.five.tmdb.domain.usecase

import agency.five.tmdb.domain.common.Movie
import agency.five.tmdb.domain.common.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetTrendingMoviesUseCase(
    private val movieRepository: agency.five.tmdb.domain.repointerfaces.MovieRepository
) {

    operator fun invoke(): Flow<Resource<List<List<Movie>>>> = flow {

        try {
            emit(Resource.Loading())

            // Initial values with 'isFavorite' status unset
            val trendingTodayInitial = movieRepository.getTodayTrendingMovies().subList(0, 10)
            val trendingThisWeekInitial =
                movieRepository.getThisWeekTrendingMovies().subList(0, 10)

            // Continuously collect data from the database to check if the favorite status has changed
            // TODO: check how to collect the flow so that it is lifecycle aware
            movieRepository.getFavorites().collect { favoriteMovies ->

                val favoriteMoviesIds = favoriteMovies.map { it.id }

                val trendingTodayUpdated =
                    agency.five.tmdb.domain.common.setMoviesFavoriteStatus(
                        trendingTodayInitial,
                        favoriteMoviesIds
                    )

                val trendingThisWeekUpdated =
                    agency.five.tmdb.domain.common.setMoviesFavoriteStatus(
                        trendingThisWeekInitial,
                        favoriteMoviesIds
                    )

                val trendingMovies = listOf(trendingTodayUpdated, trendingThisWeekUpdated)

                emit(Resource.Success(data = trendingMovies))
            }

        } catch (e: Exception) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }
    }
}