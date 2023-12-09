package agency.five.tmdb.domain.usecase

import agency.five.tmdb.domain.common.Credits
import agency.five.tmdb.domain.common.Resource
import agency.five.tmdb.domain.repointerfaces.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException

class GetCreditsUseCase(
    private val movieRepository: MovieRepository
) {
    operator fun invoke(movieId: Int): Flow<Resource<Credits>> = flow {

        try {
            emit(Resource.Loading())

            val creditsInitial = movieRepository.getCredits(movieId)

            val mostPopularCastMembers = creditsInitial.cast.sortedBy { -it.popularity }

            val mostPopularCrewMembers = creditsInitial.crew.sortedBy { -it.popularity }

            val credits = Credits(
                id = creditsInitial.id,
                cast = if (mostPopularCastMembers.size > 10)
                    mostPopularCastMembers.subList(0, 10)
                else mostPopularCastMembers,
                crew = if (mostPopularCrewMembers.size > 6)
                    mostPopularCrewMembers.subList(0, 6)
                else mostPopularCrewMembers
            )

            emit(Resource.Success(data = credits))

        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }
    }
}