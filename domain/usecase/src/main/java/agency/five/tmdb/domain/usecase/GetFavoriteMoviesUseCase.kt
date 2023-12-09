package agency.five.tmdb.domain.usecase

import agency.five.tmdb.domain.common.Movie
import agency.five.tmdb.domain.common.Resource
import agency.five.tmdb.domain.repointerfaces.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map

class GetFavoriteMoviesUseCase(
    private val movieRepository: MovieRepository
) {
    operator fun invoke(): Flow<Resource<List<Movie>>> {
        return movieRepository.getFavorites().map { movies ->
            Resource.Success(movies)
        }
    }
}