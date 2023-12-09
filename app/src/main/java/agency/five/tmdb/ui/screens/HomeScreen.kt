package agency.five.tmdb.ui.screens

import agency.five.tmdb.R
import agency.five.tmdb.navigation.RootScreen
import agency.five.tmdb.ui.components.MoviesSection
import agency.five.tmdb.ui.components.SearchBar
import agency.five.tmdb.viewmodel.HomeScreenViewModel
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import org.koin.androidx.compose.getViewModel


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    rootNavHostController: NavHostController
) {

    val homeScreenViewModel = getViewModel<HomeScreenViewModel>()

    val popularMoviesState by homeScreenViewModel.popularMoviesState

    val trendingMoviesState by homeScreenViewModel.trendingMoviesState

    val freeMoviesState by homeScreenViewModel.freeMoviesState

    val pullRefreshState = rememberPullRefreshState(
        refreshing = homeScreenViewModel.isRefreshing.value,
        onRefresh = { homeScreenViewModel.refresh() })


    // list of tabs for the "What's popular" section
    val tabsPopular = listOf(
        stringResource(id = R.string.streaming_tab),
        stringResource(id = R.string.on_tv_tab),
        stringResource(id = R.string.for_rent_tab),
        stringResource(id = R.string.in_theaters_tab)
    )

    // list of tabs for the "Free to watch" section
    val tabsFree = listOf(
        stringResource(id = R.string.movies_tab),
        stringResource(id = R.string.tv_tab)
    )

    // list of tabs for the "Trending" section
    val tabsTrending = listOf(
        stringResource(id = R.string.today_tab),
        stringResource(id = R.string.this_week_tab)
    )


    Box(
        modifier = modifier.pullRefresh(pullRefreshState)
    ) {

        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {

            item {
                SearchBar()
            }

            // What's popular
            item {
                MoviesSection(
                    title = stringResource(id = R.string.whats_popular),
                    moviesUiState = popularMoviesState,
                    tabData = tabsPopular,
                    onMovieClick = { movieId ->
                        rootNavHostController.navigate(RootScreen.DetailsScreen.route + "/${movieId}") {
                            // Do not navigate to the same route on double click
                            launchSingleTop = true
                        }
                    },
                    onFavoriteButtonClick = { updatedMovie ->
                        homeScreenViewModel.movieFavoriteButtonClick(updatedMovie)
                    }
                )
            }

            // Free to watch
            item {
                MoviesSection(
                    title = stringResource(id = R.string.free_to_watch),
                    moviesUiState = freeMoviesState,
                    tabData = tabsFree,
                    onMovieClick = { movieId ->
                        rootNavHostController.navigate(RootScreen.DetailsScreen.route + "/${movieId}") {
                            // Do not navigate to the same route on double click
                            launchSingleTop = true
                        }
                    },
                    onFavoriteButtonClick = { updatedMovie ->
                        homeScreenViewModel.movieFavoriteButtonClick(updatedMovie)
                    }
                )
            }

            // Trending
            item {
                MoviesSection(
                    title = stringResource(id = R.string.trending),
                    moviesUiState = trendingMoviesState,
                    tabData = tabsTrending,
                    onMovieClick = { movieId ->
                        rootNavHostController.navigate(RootScreen.DetailsScreen.route + "/${movieId}") {
                            // Do not navigate to the same route on double click
                            launchSingleTop = true
                        }
                    },
                    onFavoriteButtonClick = { updatedMovie ->
                        homeScreenViewModel.movieFavoriteButtonClick(updatedMovie)
                    }
                )
            }
        }

        PullRefreshIndicator(
            refreshing = homeScreenViewModel.isRefreshing.value,
            state = pullRefreshState,
            modifier = Modifier.align(Alignment.TopCenter)
        )
    }
}
