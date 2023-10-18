package agency.five.tmdb.presentation.navigation

sealed class Screens(val route: String) {
    object MainScreen : Screens("main_screen")  //add favorites screen and home screen
    object DetailsScreen : Screens("details_screen")
}
