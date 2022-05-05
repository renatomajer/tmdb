package agency.five.tmdb.module


import agency.five.tmdb.*
import agency.five.tmdb.ui.viewmodel.DetailsScreenViewModel
import agency.five.tmdb.ui.viewmodel.FavoritesScreenViewModel
import agency.five.tmdb.ui.viewmodel.HomeScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val movieModule = module {
    single<MovieApi> { MovieApiImpl() }
    single<MovieDatabase> { MovieDatabaseImpl() }

    single<MovieRepository> { MovieRepositoryImpl(movieApi = get(), movieDatabase = get()) }

    viewModel {
        FavoritesScreenViewModel()
    }

    viewModel { params ->
        DetailsScreenViewModel(id = params.get())
    }

    viewModel {
        HomeScreenViewModel()
    }
}