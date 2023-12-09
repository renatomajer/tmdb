package agency.five.tmdb.ui.screens

import agency.five.tmdb.navigation.BottomBarNavigationGraph
import agency.five.tmdb.ui.components.TmdbBottomBar
import agency.five.tmdb.ui.components.TmdbTopAppBar
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun MainScreen(
    rootNavHostController: NavHostController,
    bottomBarNavController: NavHostController = rememberNavController()
) {

    Scaffold(
        topBar = {
            TmdbTopAppBar()
        },
        bottomBar = {
            TmdbBottomBar(navHostController = bottomBarNavController)
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            BottomBarNavigationGraph(
                rootNavHostController = rootNavHostController,
                bottomBarNavHostController = bottomBarNavController
            )
        }
    }
}