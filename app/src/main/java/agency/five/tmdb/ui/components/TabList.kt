package agency.five.tmdb.ui.components


import agency.five.tmdb.R
import agency.five.tmdb.domain.common.Movie
import agency.five.tmdb.ui.components.movie.MovieCardsList
import agency.five.tmdb.ui.theme.Gray
import agency.five.tmdb.ui.theme.Navy
import agency.five.tmdb.ui.theme.Typography
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Tab
import androidx.compose.material.TabPosition
import androidx.compose.material.TabRow
import androidx.compose.material.TabRowDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.debugInspectorInfo
import androidx.compose.ui.res.dimensionResource


@Composable
fun TabList(
    tabData: List<String>,
    moviesLists: List<List<Movie>>,
    onMovieClick: (Int) -> Unit = {},
    onFavoriteButtonClick: (updatedMovie: Movie) -> Unit = {}
) {
    var tabIndex by remember { mutableIntStateOf(0) }

    Column(
        horizontalAlignment = Alignment.Start
    ) {
        TabRow(
            modifier = Modifier.padding(
                start = dimensionResource(id = R.dimen.screen_content_padding),
                end = dimensionResource(id = R.dimen.screen_content_padding)
            ),
            selectedTabIndex = tabIndex,
            backgroundColor = Color.Transparent,
            divider = {
                TabRowDefaults.Divider(
                    thickness = dimensionResource(id = R.dimen.tab_row_divider_thickness),
                    color = Color.Transparent
                )
            },
            indicator = { tabPositions ->
                TabRowDefaults.Indicator(
                    modifier = Modifier.customTabIndicatorOffset(tabPositions[tabIndex]),
                    height = dimensionResource(id = R.dimen.tab_indicator_height),
                    color = Navy
                )
            }
        ) {
            tabData.forEachIndexed { index, name ->
                Tab(
                    selected = tabIndex == index,
                    onClick = {
                        tabIndex = index
                    },
                    unselectedContentColor = Gray,
                    selectedContentColor = Color.Black,

                    ) {
                    Text(
                        text = name,
                        style = Typography.subtitle1,
                        modifier = Modifier.padding(vertical = dimensionResource(id = R.dimen.tab_data_vertical_padding))
                    )
                }
            }
        }

        MovieCardsList(
            movies = moviesLists[tabIndex],
            onMovieClick = onMovieClick,
            onFavoriteButtonClick = onFavoriteButtonClick
        )
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
    val indicatorWidth = dimensionResource(id = R.dimen.tab_indicator_width)
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