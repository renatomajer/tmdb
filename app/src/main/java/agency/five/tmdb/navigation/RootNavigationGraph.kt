package agency.five.tmdb.navigation

import agency.five.tmdb.ui.screens.DetailsScreen
import agency.five.tmdb.ui.screens.MainScreen
import agency.five.tmdb.util.detailsScreenPopExitTransition
import agency.five.tmdb.util.detailsScreenEnterTransition
import agency.five.tmdb.util.mainScreenExitTransition
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation


@Composable
fun RootNavigationGraph(
    rootNavController: NavHostController
) {

    NavHost(
        navController = rootNavController,
        route = Graph.ROOT,
        startDestination = Graph.BOTTOM_BAR
    ) {

        composable(
            route = Graph.BOTTOM_BAR,
            enterTransition = { EnterTransition.None },
            exitTransition = mainScreenExitTransition(),
            popEnterTransition = { EnterTransition.None },
            popExitTransition = { ExitTransition.None }
        ) {
            MainScreen(rootNavHostController = rootNavController)
        }

        navigation(route = Graph.DETAILS, startDestination = RootScreen.DetailsScreen.route) {

            composable(
                route = RootScreen.DetailsScreen.route + "/{${RootScreen.DetailsScreen.ARGUMENT_ID}}",
                arguments = listOf(navArgument(name = RootScreen.DetailsScreen.ARGUMENT_ID) {
                    type = NavType.IntType
                }),
                enterTransition = detailsScreenEnterTransition(),
                popExitTransition = detailsScreenPopExitTransition()
            ) { entry ->
                entry.arguments?.getInt(RootScreen.DetailsScreen.ARGUMENT_ID)
                    ?.let { movieId ->
                        DetailsScreen(
                            rootNavHostController = rootNavController,
                            movieId = movieId
                        )
                    }
            }
        }
    }
}