package agency.five.tmdb.ui.components.movie

import agency.five.tmdb.R
import agency.five.tmdb.domain.common.Constants.IMAGE_BASE_URL
import agency.five.tmdb.domain.common.Constants.IMAGE_BIG
import agency.five.tmdb.domain.common.MovieDetails
import agency.five.tmdb.ui.components.CircularProgressBarComponent
import agency.five.tmdb.ui.components.FavoriteButton
import agency.five.tmdb.ui.theme.DarkGray
import agency.five.tmdb.ui.theme.Typography
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import coil.compose.rememberAsyncImagePainter


@Composable
fun MovieDetailsComponent(
    modifier: Modifier = Modifier,
    movieDetails: MovieDetails,
    onFavoriteButtonClick: (updatedMovieDetails: MovieDetails) -> Unit = {}
) {
    Box(
        modifier = modifier
            .height(dimensionResource(id = R.dimen.movie_details_image_height))
            .width(dimensionResource(id = R.dimen.movie_details_image_width))
    ) {
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = rememberAsyncImagePainter(IMAGE_BASE_URL + IMAGE_BIG + movieDetails.posterPath),
            contentDescription = null,
            contentScale = ContentScale.FillWidth,
            alignment = Alignment.TopStart
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color.Transparent,
                            Color.Black
                        ),
                        startY = 50f
                    )
                )
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(dimensionResource(id = R.dimen.cards_list_content_padding)),
            contentAlignment = Alignment.BottomStart
        ) {
            Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Bottom) {

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {

                    CircularProgressBarComponent(
                        value = (movieDetails.voteAverage * 10).toInt(),
                        modifier = Modifier.padding(end = dimensionResource(id = R.dimen.small_spacing))
                    )

                    Text(
                        text = stringResource(id = R.string.user_score),
                        style = Typography.subtitle2,
                        color = Color.White
                    )
                }

                Text(
                    text = movieDetails.title,
                    style = Typography.h5
                )

                Text(
                    text = movieDetails.releaseDate + " (" + movieDetails.originalLanguage + ")",
                    style = Typography.body2,
                    color = Color.White
                )

                Row {
                    for (i in movieDetails.genres.indices) {

                        if (i == 0) {
                            Text(
                                text = movieDetails.genres[i].name,
                                style = Typography.body2,
                                color = Color.White
                            )
                        } else {
                            Text(
                                text = ", " + movieDetails.genres[i].name,
                                style = Typography.body2,
                                color = Color.White
                            )
                        }
                    }

                    Text(
                        text = " " + movieDetails.runtime + "m",
                        style = Typography.body2,
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                }

                FavoriteButton(
                    modifier = Modifier.padding(top = dimensionResource(id = R.dimen.small_spacing)),
                    isFavorite = movieDetails.isFavorite,
                    onFavoriteButtonClick = {
                        onFavoriteButtonClick(movieDetails.copy(isFavorite = movieDetails.isFavorite.not()))
                    },
                    backgroundColor = DarkGray
                )
            }
        }
    }
}