package agency.five.tmdb

import agency.five.tmdb.ui.components.MovieItemViewState
import agency.five.tmdb.ui.components.TabList
import agency.five.tmdb.ui.theme.Typography
import agency.five.tmdb.ui.viewmodel.HomeScreenViewModel
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.navigation.NavController
import org.koin.androidx.compose.viewModel


@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navController: NavController
) {

    val homeScreenViewModel by viewModel<HomeScreenViewModel>()

    val lifecycleOwner = LocalLifecycleOwner.current

    val popularFlowLifecycleAware =
        remember(homeScreenViewModel.getPopularMoviesLists(), lifecycleOwner) {
            homeScreenViewModel.getPopularMoviesLists().flowWithLifecycle(
                lifecycleOwner.lifecycle,
                Lifecycle.State.STARTED
            )
        }

    val trendingFlowLifecycleAware =
        remember(homeScreenViewModel.getTrendingMoviesLists(), lifecycleOwner) {
            homeScreenViewModel.getTrendingMoviesLists().flowWithLifecycle(
                lifecycleOwner.lifecycle,
                Lifecycle.State.STARTED
            )
        }

    val freeFlowLifecycleAware =
        remember(homeScreenViewModel.getFreeMoviesLists(), lifecycleOwner) {
            homeScreenViewModel.getFreeMoviesLists().flowWithLifecycle(
                lifecycleOwner.lifecycle,
                Lifecycle.State.STARTED
            )
        }

    val popular: List<List<MovieItemViewState>> by popularFlowLifecycleAware
        .collectAsState(
            initial = listOf(emptyList(), emptyList(), emptyList(), emptyList())
        )

    val trending: List<List<MovieItemViewState>> by trendingFlowLifecycleAware
        .collectAsState(
            initial = listOf(emptyList(), emptyList())
        )

    val free: List<List<MovieItemViewState>> by freeFlowLifecycleAware
        .collectAsState(
            initial = listOf(emptyList(), emptyList())
        )


    // list of tabs for the "What's popular" section
    val t1 = listOf(
        stringResource(id = R.string.streaming_tab),
        stringResource(id = R.string.on_tv_tab),
        stringResource(id = R.string.for_rent_tab),
        stringResource(id = R.string.in_theaters_tab)
    )

    // list of tabs for the "Free to watch" section
    val t2 = listOf(
        stringResource(id = R.string.movies_tab),
        stringResource(id = R.string.tv_tab)
    )

    // list of tabs for the "Trending" section
    val t3 = listOf(
        stringResource(id = R.string.today_tab),
        stringResource(id = R.string.this_week_tab)
    )

    LazyColumn(
        contentPadding = PaddingValues(horizontal = 18.dp),
        modifier = modifier
    ) {

        item {
            SearchBar()
        }

        items(3) { index ->
            if (index == 0) {
                Text(
                    text = stringResource(id = R.string.whats_popular_section),
                    modifier = Modifier
                        .fillMaxWidth(),
                    style = Typography.h6
                )

                TabList(
                    tabData = t1,
                    moviesLists = popular,
                    navController = navController,
                    model = homeScreenViewModel
                )

            } else if (index == 1) {
                Text(
                    text = stringResource(id = R.string.free_to_watch_section),
                    modifier = Modifier
                        .fillMaxWidth(),
                    style = Typography.h6
                )

                TabList(
                    tabData = t2,
                    moviesLists = free,
                    navController = navController,
                    model = homeScreenViewModel
                )

            } else {
                Text(
                    text = stringResource(id = R.string.trending_section),
                    modifier = Modifier
                        .fillMaxWidth(),
                    style = Typography.h6
                )

                TabList(
                    tabData = t3,
                    moviesLists = trending,
                    navController = navController,
                    model = homeScreenViewModel
                )
            }
        }
    }
}
