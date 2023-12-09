package agency.five.tmdb.navigation

import agency.five.tmdb.ui.screens.FavoritesScreen
import agency.five.tmdb.ui.screens.HomeScreen
import agency.five.tmdb.util.favoritesScreenEnterTransition
import agency.five.tmdb.util.favoritesScreenPopExitTransition
import agency.five.tmdb.util.homeScreenExitTransition
import agency.five.tmdb.util.homeScreenPopEnterTransition
import androidx.compose.animation.EnterTransition
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable


@Composable
fun BottomBarNavigationGraph(
    bottomBarNavHostController: NavHostController,
    rootNavHostController: NavHostController
) {

    NavHost(
        navController = bottomBarNavHostController,
        route = Graph.BOTTOM_BAR,
        startDestination = BottomBarScreen.HomeScreen.route
    ) {

        composable(
            route = BottomBarScreen.HomeScreen.route,
            enterTransition = { EnterTransition.None },
            exitTransition = homeScreenExitTransition(),
            popEnterTransition = homeScreenPopEnterTransition()
        ) {

            HomeScreen(rootNavHostController = rootNavHostController)
        }

        composable(
            route = BottomBarScreen.FavoritesScreen.route,
            enterTransition = favoritesScreenEnterTransition(),
            popExitTransition = favoritesScreenPopExitTransition()
        ) {

            FavoritesScreen(rootNavController = rootNavHostController)
        }
    }
}