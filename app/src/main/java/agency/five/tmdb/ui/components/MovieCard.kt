package agency.five.tmdb.ui.components

import agency.five.tmdb.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview


data class MovieItemViewState(
    val id: Int = 0,
    val title: String = "",
    val overview: String = "Nothing to show",
    val imageResId: Int = -1, // id from drawable folder (R.drawable...)
    var favorite: Boolean = false,
    var date: String = "No date available",   // needs to be changed
    var userScore: Int = 0,
    var country: String = "Country unknown",
    var duration: String = "No duration available",   // needs to be changed
    var genres: List<String> = mutableListOf()
)


@Composable
fun MovieCard(
    modifier: Modifier = Modifier,
    onMovieItemClick: () -> Unit = {},
    onFavoriteButtonClick: (MovieItemViewState) -> Unit = {},
    item: MovieItemViewState
) {
    Box(
        modifier = modifier
    ) {
        Image(
            painter = painterResource(id = item.imageResId),
            contentDescription = null,
            modifier = Modifier
                .size(
                    width = dimensionResource(id = R.dimen.movie_card_width),
                    height = dimensionResource(id = R.dimen.movie_card_height)
                )
                .clip(RoundedCornerShape(dimensionResource(id = R.dimen.small_spacing)))
                .clickable { onMovieItemClick() },
            contentScale = ContentScale.Crop
        )

        FavoriteButton(
            modifier = Modifier.padding(
                start = dimensionResource(id = R.dimen.small_spacing),
                top = dimensionResource(id = R.dimen.small_spacing)
            ),
            //favorite = item.favorite,
            movie = item,
            onFavoriteButtonClick = onFavoriteButtonClick
        )
    }
}


@Composable
fun MovieList(
    modifier: Modifier = Modifier,
    onMovieItemClick: (MovieItemViewState) -> Unit = {},
    onFavoriteButtonClick: (MovieItemViewState) -> Unit = {},
    movieItems: List<MovieItemViewState>
) {
    LazyRow(
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = dimensionResource(id = R.dimen.home_movie_list_bottom_padding)),
        contentPadding = PaddingValues(
            vertical = dimensionResource(id = R.dimen.home_movies_list_content_padding)
        )
    ) {
        items(movieItems) {
            MovieCard(
                item = it,
                modifier = Modifier.padding(end = dimensionResource(id = R.dimen.movie_list_item_padding)),
                onMovieItemClick = { onMovieItemClick(it) },
                onFavoriteButtonClick = onFavoriteButtonClick
            )
        }
    }
}


@Preview
@Composable
fun MovieListPreview() {
    MovieList(
        movieItems = listOf(
            MovieItemViewState(
                id = 0,
                overview = "",
                title = "Iron Man",
                imageResId = R.drawable.iron_man_1
            ),

            MovieItemViewState(
                id = 1,
                overview = "",
                title = "Gattaca",
                imageResId = R.drawable.gattaca
            ),

            MovieItemViewState(
                id = 2,
                overview = "",
                title = "Lion King",
                imageResId = R.drawable.lion_king
            ),

            MovieItemViewState(
                id = 3,
                overview = "",
                title = "Puppy Love",
                imageResId = R.drawable.puppy_love
            )
        )
    )
}
