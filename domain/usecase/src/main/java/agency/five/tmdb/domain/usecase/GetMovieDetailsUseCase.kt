package agency.five.tmdb.domain.usecase

import agency.five.tmdb.domain.common.MovieDetails
import agency.five.tmdb.domain.common.Resource
import agency.five.tmdb.domain.repointerfaces.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException

class GetMovieDetailsUseCase(
    private val movieRepository: MovieRepository
) {
    operator fun invoke(movieId: Int): Flow<Resource<MovieDetails>> = flow {

        try {
            emit(Resource.Loading())

            val movieDetails = movieRepository.getMovieDetails(movieId)

            // Continuously collect data from the database to check if the favorite status has changed
            movieRepository.getFavorites().collect { favoriteMovies ->

                val favoriteMoviesIds = favoriteMovies.map { it.id }

                // The movie's initial (default) favorite value is 'false'
                if (favoriteMoviesIds.contains(movieDetails.id)) {
                    // If the movie is marked as favorite, emit its details and show the correct favorite status
                    emit(Resource.Success(data = movieDetails.copy(isFavorite = true)))
                } else {
                    emit(Resource.Success(data = movieDetails))
                }
            }

        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }
    }
}