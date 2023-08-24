package agency.five.tmdb.ui.components


import agency.five.tmdb.navigation.Screens
import agency.five.tmdb.ui.theme.Typography
import agency.five.tmdb.ui.viewmodel.HomeScreenViewModel
import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.TabRowDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.debugInspectorInfo
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController


@Composable
fun TabList(
    tabData: List<String>,
    moviesLists: List<List<MovieItemViewState>>,
    navController: NavController,
    model: HomeScreenViewModel
) {
    var tabIndex by remember { mutableStateOf(0) }

    Column(
        horizontalAlignment = Alignment.Start
    ) {
        TabRow(
            selectedTabIndex = tabIndex,
            backgroundColor = Color.Transparent,
            divider = { TabRowDefaults.Divider(thickness = 5.dp, color = Color.Transparent) },
            indicator = { tabPositions ->
                TabRowDefaults.Indicator(
                    modifier = Modifier.customTabIndicatorOffset(tabPositions[tabIndex]),
                    height = 3.dp,
                    color = Color(0xFF0B253F)
                )
            }
        ) {
            tabData.forEachIndexed { index, name ->
                Tab(
                    selected = tabIndex == index,
                    onClick = {
                        tabIndex = index
                    },
                    unselectedContentColor = Color(0xFF828282),
                    selectedContentColor = Color.Black,

                    ) {
                    Text(
                        text = name,
                        style = Typography.subtitle1,
                        modifier = Modifier.padding(vertical = 5.dp)
                    )
                }
            }
        }

        Surface() {
            val moviesData = moviesLists[tabIndex]
//            val referenceValue = Integer.toHexString(System.identityHashCode(moviesLists))
//            Log.d("debug_log", "$referenceValue: moviesData: ${tabData[tabIndex]}")
            MovieList(
                movieItems = moviesData,
                onMovieItemClick = { navController.navigate(Screens.DetailsScreen.route + "/${it.id}") },
                onFavoriteButtonClick = { updatedMovie ->
                    model.movieFavoriteButtonClick(
                        moviesData.first { updatedMovie.id == it.id },
                        updatedMovie.favorite
                    )
                }
            )
        }
    }
}


fun Modifier.customTabIndicatorOffset(
    currentTabPosition: TabPosition
): Modifier = composed(
    inspectorInfo = debugInspectorInfo {
        name = "tabIndicatorOffset"
        value = currentTabPosition
    }
) {
    val indicatorWidth = 75.dp
    val currentTabWidth = currentTabPosition.width
    val indicatorOffset by animateDpAsState(
        targetValue = currentTabPosition.left + currentTabWidth / 2 - indicatorWidth / 2,
        animationSpec = tween(durationMillis = 200, easing = FastOutSlowInEasing)
    )
    fillMaxWidth()
        .wrapContentSize(Alignment.BottomStart)
        .offset(x = indicatorOffset)
        .width(indicatorWidth)
}


