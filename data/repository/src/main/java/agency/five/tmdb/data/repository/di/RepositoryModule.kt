package agency.five.tmdb.data.repository.di

import agency.five.tmdb.data.repository.MovieRepositoryImpl
import agency.five.tmdb.domain.repointerfaces.MovieRepository
import org.koin.dsl.module

val repositoryModule = module {

    single<MovieRepository> {
        MovieRepositoryImpl(
            movieApi = get(),
            favoriteMoviesDao = get(),
            movieMapper = get()
        )
    }
}