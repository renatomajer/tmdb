package agency.five.tmdb.domain.usecase

import agency.five.tmdb.domain.common.Movie
import agency.five.tmdb.domain.common.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException

class GetFreeMoviesUseCase(
    private val movieRepository: agency.five.tmdb.domain.repointerfaces.MovieRepository
) {

    operator fun invoke(): Flow<Resource<List<List<Movie>>>> = flow {

        try {
            emit(Resource.Loading())

            // Initial values with 'isFavorite' status unset
            val upcomingInitial =
                movieRepository.getUpcomingMovies().subList(0, 10)
            val topRatedInitial =
                movieRepository.getTopRatedMovies().subList(0, 10)

            // Continuously collect data from the database to check if the favorite status has changed
            // TODO: check how to collect the flow so that it is lifecycle aware
            movieRepository.getFavorites().collect { favoriteMovies ->

                val favoriteMoviesIds = favoriteMovies.map { it.id }

                val upcomingUpdated = agency.five.tmdb.domain.common.setMoviesFavoriteStatus(
                    upcomingInitial,
                    favoriteMoviesIds
                )

                val topRatedUpdated = agency.five.tmdb.domain.common.setMoviesFavoriteStatus(
                    topRatedInitial,
                    favoriteMoviesIds
                )

                val freeMovies = listOf(upcomingUpdated, topRatedUpdated)

                emit(Resource.Success(data = freeMovies))
            }
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }
    }
}