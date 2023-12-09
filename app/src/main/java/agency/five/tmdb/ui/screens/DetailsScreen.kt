package agency.five.tmdb.ui.screens


import agency.five.tmdb.R
import agency.five.tmdb.ui.components.CrewMemberComponent
import agency.five.tmdb.ui.components.TmdbTopAppBar
import agency.five.tmdb.ui.components.cast.CastCardsList
import agency.five.tmdb.ui.components.movie.MovieDetailsComponent
import agency.five.tmdb.ui.theme.Navy
import agency.five.tmdb.ui.theme.Typography
import agency.five.tmdb.util.gridItems
import agency.five.tmdb.viewmodel.DetailsScreenViewModel
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowLeft
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavHostController
import org.koin.androidx.compose.getViewModel


@Composable
fun DetailsScreen(
    rootNavHostController: NavHostController,
    movieId: Int
) {

    val movieDetailsViewModel = getViewModel<DetailsScreenViewModel>()

    val movieDetailsState by movieDetailsViewModel.movieDetailsState

    val creditsState by movieDetailsViewModel.creditsState


    Scaffold(
        topBar = {
            TmdbTopAppBar {
                IconButton(
                    onClick = {
                        // Detect if the back button has already been pressed (only pop the backstack is the screen state is resumed)
                        if (rootNavHostController.currentBackStackEntry?.lifecycle?.currentState == Lifecycle.State.RESUMED) {
                            rootNavHostController.popBackStack()
                        }
                    }
                ) {
                    Icon(
                        Icons.Outlined.KeyboardArrowLeft,
                        contentDescription = null,
                        tint = Color.White
                    )
                }
            }
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier.padding(paddingValues)
        ) {

            item {
                movieDetailsState.movie?.let { details ->
                    MovieDetailsComponent(
                        movieDetails = details,
                        modifier = Modifier.fillMaxWidth(),
                        onFavoriteButtonClick = { updatedMovieDetails ->
                            movieDetailsViewModel.movieFavoriteButtonClick(updatedMovieDetails)
                        }
                    )
                }
            }

            item {

                Column(
                    modifier = Modifier
                        .padding(
                            start = dimensionResource(id = R.dimen.screen_content_padding),
                            end = dimensionResource(id = R.dimen.screen_content_padding),
                            top = dimensionResource(id = R.dimen.overview_top_padding),
                            bottom = dimensionResource(id = R.dimen.overview_bottom_padding)
                        )
                        .fillMaxWidth()
                ) {
                    Text(
                        text = stringResource(id = R.string.overview),
                        style = Typography.h6,
                        modifier = Modifier.padding(
                            bottom = dimensionResource(id = R.dimen.micro_spacing),
                            end = dimensionResource(id = R.dimen.overview_text_end_padding)
                        )
                    )

                    if (movieDetailsState.movie != null) {
                        Text(
                            text = movieDetailsState.movie!!.overview,
                            style = Typography.body2,
                            color = Color.Black
                        )
                    }

                    if (movieDetailsState.error != null) {
                        Text(
                            text = movieDetailsState.error!!,
                            style = Typography.body2,
                            color = Color.Black
                        )
                    }

                    if (movieDetailsState.isLoading) {
                        CircularProgressIndicator(
                            modifier = Modifier.align(Alignment.CenterHorizontally),
                            color = Navy
                        )
                    }
                }
            }

            if (creditsState.credits != null) {
                gridItems(
                    data = creditsState.credits!!.crew,
                    columnCount = 3,
                    modifier = Modifier.padding(start = 18.dp, end = 13.dp, bottom = 25.dp),
                    horizontalArrangement = Arrangement.Start
                ) { itemData ->
                    CrewMemberComponent(crewMember = itemData)
                }
            }

            item {
                Row(
                    modifier = Modifier
                        .padding(
                            start = dimensionResource(id = R.dimen.screen_content_padding),
                            end = dimensionResource(id = R.dimen.screen_content_padding),
                            bottom = dimensionResource(id = R.dimen.cast_title_row_bottom_padding),
                            top = dimensionResource(id = R.dimen.cast_title_row_top_padding)
                        )
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Bottom
                ) {
                    Text(
                        text = stringResource(id = R.string.top_billed_cast),
                        style = Typography.h6
                    )

                    Text(
                        text = stringResource(id = R.string.full_cast_crew),
                        style = Typography.subtitle2,
                        color = Navy,
                        modifier = Modifier
                            .height(dimensionResource(id = R.dimen.medium_spacing))
                            .clickable { /* TODO */ },
                        textAlign = TextAlign.Center
                    )
                }
            }

            item {

                if (creditsState.credits != null && creditsState.credits!!.cast.isNotEmpty()) {
                    CastCardsList(cast = creditsState.credits!!.cast)

                }

                if (creditsState.error != null) {
                    Text(
                        text = creditsState.error!!,
                        modifier = Modifier.padding(start = dimensionResource(id = R.dimen.screen_content_padding))
                    )
                }

                if (creditsState.isLoading) {
                    Column(
                        modifier = Modifier
                            .padding(
                                start = dimensionResource(id = R.dimen.screen_content_padding),
                                end = dimensionResource(id = R.dimen.screen_content_padding)
                            )
                            .fillMaxWidth()
                    ) {
                        CircularProgressIndicator(
                            modifier = Modifier.align(Alignment.CenterHorizontally),
                            color = Navy
                        )
                    }
                }
            }
        }
    }
}