package agency.five.tmdb.di


import agency.five.tmdb.viewmodel.DetailsScreenViewModel
import agency.five.tmdb.viewmodel.FavoritesScreenViewModel
import agency.five.tmdb.viewmodel.HomeScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    viewModel {
        FavoritesScreenViewModel(
            getFavoriteMoviesUseCase = get(),
            updateIsMovieFavoriteUseCase = get()
        )
    }

    viewModel {
        DetailsScreenViewModel(
            getCreditsUseCase = get(),
            getMovieDetailsUseCase = get(),
            updateIsMovieFavoriteUseCase = get(),
            savedStateHandle = get()
        )
    }

    viewModel {
        HomeScreenViewModel(
            getPopularMoviesUseCase = get(),
            getFreeMoviesUseCase = get(),
            getTrendingMoviesUseCase = get(),
            updateIsMovieFavoriteUseCase = get()
        )
    }
}