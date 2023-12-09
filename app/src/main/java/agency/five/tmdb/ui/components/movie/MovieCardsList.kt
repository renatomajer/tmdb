package agency.five.tmdb.ui.components.movie

import agency.five.tmdb.R
import agency.five.tmdb.domain.common.Movie
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource


@Composable
fun MovieCardsList(
    modifier: Modifier = Modifier,
    onMovieClick: (Int) -> Unit = {},
    onFavoriteButtonClick: (updatedMovie: Movie) -> Unit = {},
    movies: List<Movie>
) {
    LazyRow(
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = dimensionResource(id = R.dimen.movie_cards_list_bottom_padding)),
        contentPadding = PaddingValues(
            vertical = dimensionResource(id = R.dimen.cards_list_content_padding)
        )
    ) {
        itemsIndexed(movies) { index, item ->

            val padding = if (index == 0) {
                // Add start padding to the first movie item
                PaddingValues(
                    start = dimensionResource(id = R.dimen.screen_content_padding),
                    end = dimensionResource(id = R.dimen.movie_cards_list_item_padding)
                )
            } else {
                PaddingValues(end = dimensionResource(id = R.dimen.movie_cards_list_item_padding))
            }

            MovieCard(
                movie = item,
                modifier = Modifier.padding(padding),
                onMovieClick = onMovieClick,
                onFavoriteButtonClick = onFavoriteButtonClick
            )
        }
    }
}