package agency.five.tmdb.ui.screens

import agency.five.tmdb.R
import agency.five.tmdb.ui.theme.TmdbTheme
import agency.five.tmdb.ui.theme.Typography
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Home
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun MainScreen(
    navController: NavController
) {
    var home by remember { mutableStateOf(true) }

    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = Color(0xFF0B253F)
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {

                    Image(
                        painter = painterResource(id = R.drawable.ic_logo),
                        contentDescription = null,
                        modifier = Modifier.size(width = 134.dp, height = 35.dp)
                    )
                }
            }
        },
        bottomBar = {
            BottomNavigation(
                backgroundColor = Color.White
            ) {
                BottomNavigationItem(   // home icon
                    selected = true,
                    onClick = {

                        if (!home) {
                            home = home.not()
                        }
                    },
                    icon = {

                        if (home) {
                            Icon(
                                Icons.Filled.Home,
                                contentDescription = null,
                                tint = Color(0xFF0B253F)
                            )

                        } else {
                            Icon(
                                Icons.Outlined.Home,
                                contentDescription = null,
                                tint = Color(0xFFBDBDBD)
                            )
                        }
                    },
                    label = {
                        Text(
                            text = stringResource(id = R.string.home),
                            style = Typography.overline,
                            color = if (home) Color(0xFF0B253F) else Color(0xFFBDBDBD)
                        )
                    }
                )

                BottomNavigationItem(   // favorites icon
                    selected = false,
                    onClick = {
                        if (home) {
                            home = home.not()
                        }
                    },
                    icon = {
                        if (!home) {
                            Icon(
                                Icons.Filled.Favorite,
                                contentDescription = null,
                                tint = Color(0xFF0B253F)
                            )
                        } else {
                            Icon(
                                Icons.Outlined.Favorite,
                                contentDescription = null,
                                tint = Color(0xFFBDBDBD)
                            )
                        }
                    },
                    label = {
                        Text(
                            text = stringResource(id = R.string.favorites),
                            style = Typography.overline,
                            color = if (!home) Color(0xFF0B253F) else Color(0xFFBDBDBD)
                        )
                    }
                )
            }
        }
    ) {
        if (home) {
            HomeScreen(
                modifier = Modifier.padding(bottom = it.calculateBottomPadding()),
                navController = navController
            )
        } else {
            FavoritesScreen(
                modifier = Modifier.padding(bottom = it.calculateBottomPadding()),
                navController = navController
            ) // movie list is set by default
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TmdbTheme {
        //MainScreen()
    }
}