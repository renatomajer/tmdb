package agency.five.tmdb.ui.components

import agency.five.tmdb.R
import agency.five.tmdb.navigation.BottomBarScreen
import agency.five.tmdb.ui.theme.Navy
import agency.five.tmdb.ui.theme.NavyLight
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun TmdbBottomBar(
    navHostController: NavHostController
) {

    // List of screens we can navigate to using the bottom bar navigation
    val screens = listOf(
        BottomBarScreen.HomeScreen,
        BottomBarScreen.FavoritesScreen
    )

    val backStackEntry by navHostController.currentBackStackEntryAsState()
    val currentDestination = backStackEntry?.destination

    val bottomBarDestination = screens.any { it.route == currentDestination?.route }

    if (bottomBarDestination) {
        BottomNavigation(
            backgroundColor = Color.White
        ) {

            screens.forEach { screen ->

                AddBottomNavigationItem(
                    screen = screen,
                    currentDestination = currentDestination,
                    navHostController = navHostController
                )
            }
        }
    }
}


@Composable
fun RowScope.AddBottomNavigationItem(
    screen: BottomBarScreen,
    currentDestination: NavDestination?,
    navHostController: NavHostController
) {

    BottomNavigationItem(
        selected = currentDestination?.hierarchy?.any {
            it.route == screen.route
        } == true,
        onClick = {
            if (screen.route != currentDestination?.route) { // Do not navigate to the same route
                navHostController.navigate(screen.route) {
                    popUpTo(navHostController.graph.findStartDestination().id)
                    launchSingleTop = true
                }
            }
        },
        icon = {
            Icon(
                imageVector = screen.icon,
                contentDescription = stringResource(id = R.string.navigation_item)
            )
        },
        label = {
            Text(text = screen.title)
        },
        selectedContentColor = Navy,
        unselectedContentColor = NavyLight
    )
}