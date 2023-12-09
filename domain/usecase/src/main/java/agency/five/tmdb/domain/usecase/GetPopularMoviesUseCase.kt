package agency.five.tmdb.domain.usecase

import agency.five.tmdb.domain.common.Movie
import agency.five.tmdb.domain.common.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetPopularMoviesUseCase(
    private val movieRepository: agency.five.tmdb.domain.repointerfaces.MovieRepository
) {
    operator fun invoke(): Flow<Resource<List<List<Movie>>>> = flow {

        try {
            emit(Resource.Loading())

            // Initial values with 'isFavorite' status unset
            val forRentInitial =
                movieRepository.getPopularForRent().subList(0, 10)
            val inTheatersInitial =
                movieRepository.getPopularInTheaters().subList(0, 10)
            val onTVInitial = movieRepository.getPopularOnTV().subList(0, 10)
            val streamingInitial =
                movieRepository.getPopularStreaming().subList(0, 10)

            // Continuously collect data from the database to check if the favorite status has changed
            // TODO: check how to collect the flow so that it is lifecycle aware
            movieRepository.getFavorites().collect { favoriteMovies ->

                val favoriteMoviesIds = favoriteMovies.map { it.id }

                val forRentUpdated = agency.five.tmdb.domain.common.setMoviesFavoriteStatus(
                    forRentInitial,
                    favoriteMoviesIds
                )

                val inTheatersUpdated = agency.five.tmdb.domain.common.setMoviesFavoriteStatus(
                    inTheatersInitial,
                    favoriteMoviesIds
                )

                val onTVUpdated = agency.five.tmdb.domain.common.setMoviesFavoriteStatus(
                    onTVInitial,
                    favoriteMoviesIds
                )

                val streamingUpdated = agency.five.tmdb.domain.common.setMoviesFavoriteStatus(
                    streamingInitial,
                    favoriteMoviesIds
                )

                val popularMovies =
                    listOf(streamingUpdated, onTVUpdated, forRentUpdated, inTheatersUpdated)

                emit(Resource.Success(data = popularMovies))
            }

        } catch (e: Exception) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }
    }
}