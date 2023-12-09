package agency.five.tmdb.ui.screens

import agency.five.tmdb.R
import agency.five.tmdb.navigation.RootScreen
import agency.five.tmdb.ui.components.movie.MovieCard
import agency.five.tmdb.ui.theme.Typography
import agency.five.tmdb.viewmodel.FavoritesScreenViewModel
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import org.koin.androidx.compose.getViewModel


@Composable
fun FavoritesScreen(
    modifier: Modifier = Modifier,
    rootNavController: NavHostController
) {
    val favoritesViewModel = getViewModel<FavoritesScreenViewModel>()

    val favoriteMoviesState by favoritesViewModel.favoriteMoviesState

    BoxWithConstraints(
        modifier = modifier
            .padding(horizontal = dimensionResource(id = R.dimen.screen_content_padding))
            .fillMaxWidth()
    ) {

        LazyVerticalGrid(
            columns = GridCells.Adaptive(dimensionResource(id = R.dimen.movie_card_width)),
            modifier = Modifier
                .fillMaxSize(),
            horizontalArrangement = Arrangement.Center
        ) {


            item(
                span = {
                    GridItemSpan(maxCurrentLineSpan)
                }
            ) {
                Text(
                    text = stringResource(id = R.string.favorites_section),
                    modifier = Modifier
                        .padding(
                            top = dimensionResource(id = R.dimen.medium_spacing),
                            bottom = dimensionResource(id = R.dimen.medium_spacing)
                        )
                        .fillMaxWidth(),
                    style = Typography.h6
                )
            }

            if (favoriteMoviesState.movies != null) {
                for (movie in favoriteMoviesState.movies!!) {
                    item {

                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            MovieCard(
                                movie = movie,
                                modifier = Modifier
                                    .padding(
                                        horizontal = dimensionResource(id = R.dimen.favorite_movie_cards_horizontal_padding),
                                        vertical = dimensionResource(id = R.dimen.micro_spacing)
                                    ),
                                onMovieClick = { movieId ->
                                    rootNavController.navigate(RootScreen.DetailsScreen.route + "/${movieId}") {
                                        // Do not navigate to the same route on double click
                                        launchSingleTop = true
                                    }
                                },
                                onFavoriteButtonClick = { updatedMovie ->
                                    favoritesViewModel.movieFavoriteButtonClick(updatedMovie)
                                }
                            )
                        }
                    }
                }
            }
        }

        if (favoriteMoviesState.error != null) {
            Text(
                text = favoriteMoviesState.error!!,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        top = dimensionResource(id = R.dimen.no_connection_text_top_padding),
                        bottom = dimensionResource(id = R.dimen.no_connection_text_bottom_padding)
                    )
                    .align(Alignment.Center),
            )
        }

        if (favoriteMoviesState.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }
}