package agency.five.tmdb.ui.components

import agency.five.tmdb.R
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview


@Composable
fun FavoriteButton(
    modifier: Modifier = Modifier,
    movie: MovieItemViewState,
    onFavoriteButtonClick: (MovieItemViewState) -> Unit = {}
) {

    IconButton(
        onClick = {
            onFavoriteButtonClick(movie.copy(favorite = movie.favorite.not()))
        }
    ) {

        if (movie.favorite) {

            Icon(
                Icons.Filled.Favorite,
                contentDescription = null,
                modifier = Modifier
                    .size(dimensionResource(id = R.dimen.large_spacing))
                    .background(Color(0x3C0B253F), CircleShape)
                    .padding(dimensionResource(id = R.dimen.micro_spacing)),
                tint = Color.White
            )

        } else {

            Icon(
                Icons.Filled.FavoriteBorder,
                contentDescription = null,
                modifier = Modifier
                    .size(dimensionResource(id = R.dimen.large_spacing))
                    .background(Color(0x3C0B253F), CircleShape)
                    .padding(dimensionResource(id = R.dimen.micro_spacing)),
                tint = Color.White
            )
        }
    }
}


@Preview
@Composable
fun FavoriteButtonPreview() {
    //FavoriteButton()
}