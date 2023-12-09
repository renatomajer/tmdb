package agency.five.tmdb.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector

abstract class Screen(val route: String)

sealed class RootScreen(route: String) : Screen(route) {

    data object DetailsScreen : RootScreen("details_screen") {
        const val ARGUMENT_ID = "id"
    }
}

sealed class BottomBarScreen(
    route: String,
    val title: String,
    val icon: ImageVector
) : Screen(route) {

    data object HomeScreen : BottomBarScreen(
        route = "home_screen",
        title = "Home",
        icon = Icons.Filled.Home
    )

    data object FavoritesScreen : BottomBarScreen(
        route = "favorites_screen",
        title = "Favorites",
        icon = Icons.Filled.Favorite
    )
}
