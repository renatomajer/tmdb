package agency.five.tmdb.domain.usecase

import agency.five.tmdb.domain.common.Movie
import agency.five.tmdb.domain.repointerfaces.MovieRepository

class UpdateIsMovieFavoriteUseCase(
    private val movieRepository: MovieRepository
) {
    suspend operator fun invoke(updatedMovie: Movie) {
        if(updatedMovie.isFavorite) {
            movieRepository.insertFavoriteMovie(updatedMovie)
        } else {
            movieRepository.deleteFavoriteMovie(updatedMovie)
        }
    }
}