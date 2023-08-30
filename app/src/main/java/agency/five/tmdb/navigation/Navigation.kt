package agency.five.tmdb.navigation

import agency.five.tmdb.ui.screens.DetailsScreen
import agency.five.tmdb.ui.screens.MainScreen
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screens.MainScreen.route) {
        composable(route = Screens.MainScreen.route) {
            MainScreen(navController = navController)
        }

        composable(
            route = Screens.DetailsScreen.route + "/{id}",
            arguments = listOf(navArgument(name = "id") {
                type = NavType.IntType
            })
        ) { entry ->
            entry.arguments?.getInt("id")?.let { DetailsScreen(navController = navController, it) }
            //DetailsScreen(navController = navController, entry.arguments?.getInt("id")) //TODO: see if the changed line will work
        }
    }
}
