package agency.five.tmdb.ui.components

import agency.five.tmdb.R
import agency.five.tmdb.ui.theme.homeScreenContentPadding
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import coil.compose.rememberImagePainter
import kotlinx.serialization.Serializable


@Entity
@Serializable
data class MovieItemViewState(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "movie_id")
    var id: Int = -1,
    var title: String = "",
    var overview: String = "Nothing to show",
    var favorite: Boolean = false,
    var release_date: String = "No date available",
    var vote_average: Double = 0.0,
    var original_language: String = "Country unknown",
    var runtime: Int? = 0,   // details
    @Ignore
    var genres: List<Genre> = mutableListOf(), // details
    var poster_path: String? = ""
)


@Serializable
data class Genre(
    var id: Int,
    var name: String
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
            painter = rememberImagePainter("https://image.tmdb.org/t/p/w200" + item.poster_path),
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
        itemsIndexed(movieItems) { index, item ->

            val padding = if (index == 0) {
                // Add start padding to the first movie item
                PaddingValues(
                    start = homeScreenContentPadding,
                    end = dimensionResource(id = R.dimen.movie_list_item_padding)
                )
            } else {
                PaddingValues(end = dimensionResource(id = R.dimen.movie_list_item_padding))
            }

            MovieCard(
                item = item,
                modifier = Modifier.padding(padding),
                onMovieItemClick = { onMovieItemClick(item) },
                onFavoriteButtonClick = onFavoriteButtonClick
            )
        }
    }
}