package agency.five.tmdb.ui.components.movie

import agency.five.tmdb.R
import agency.five.tmdb.domain.common.Constants.IMAGE_BASE_URL
import agency.five.tmdb.domain.common.Constants.IMAGE_SMALL
import agency.five.tmdb.domain.common.Movie
import agency.five.tmdb.ui.components.FavoriteButton
import agency.five.tmdb.ui.theme.Shapes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import coil.compose.rememberAsyncImagePainter


@Composable
fun MovieCard(
    modifier: Modifier = Modifier,
    onMovieClick: (Int) -> Unit = {},
    onFavoriteButtonClick: (updatedMovie: Movie) -> Unit = {},
    movie: Movie
) {
    Box(
        modifier = modifier
    ) {
        Image(
            painter = rememberAsyncImagePainter(IMAGE_BASE_URL + IMAGE_SMALL + movie.posterPath),
            contentDescription = null,
            modifier = Modifier
                .size(
                    width = dimensionResource(id = R.dimen.movie_card_width),
                    height = dimensionResource(id = R.dimen.movie_card_height)
                )
                .clip(Shapes.medium)
                .clickable { onMovieClick(movie.id) },
            contentScale = ContentScale.Crop
        )

        FavoriteButton(
            modifier = Modifier.padding(
                start = dimensionResource(id = R.dimen.small_spacing),
                top = dimensionResource(id = R.dimen.small_spacing)
            ),
            isFavorite = movie.isFavorite,
            onFavoriteButtonClick = { onFavoriteButtonClick(movie.copy(isFavorite = movie.isFavorite.not())) }
        )
    }
}