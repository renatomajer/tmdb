package agency.five.tmdb.di

import agency.five.tmdb.domain.usecase.*
import org.koin.dsl.module

val useCaseModule = module {

    single {
        GetPopularMoviesUseCase(movieRepository = get())
    }

    single {
        GetFreeMoviesUseCase(movieRepository = get())
    }

    single {
        GetTrendingMoviesUseCase(movieRepository = get())
    }

    single {
        GetFavoriteMoviesUseCase(movieRepository = get())
    }

    single {
        UpdateIsMovieFavoriteUseCase(movieRepository = get())
    }

    single {
        GetCreditsUseCase(movieRepository = get())
    }

    single {
        GetMovieDetailsUseCase(movieRepository = get())
    }
}