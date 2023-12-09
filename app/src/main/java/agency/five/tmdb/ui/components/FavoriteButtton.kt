package agency.five.tmdb.ui.components

import agency.five.tmdb.R
import agency.five.tmdb.ui.theme.GrayNavy
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource


@Composable
fun FavoriteButton(
    modifier: Modifier = Modifier,
    isFavorite: Boolean,
    onFavoriteButtonClick: () -> Unit = {},
    backgroundColor: Color = GrayNavy
) {

    Icon(
        imageVector = if (isFavorite) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
        contentDescription = stringResource(id = R.string.favorite_button),
        modifier = modifier
            .clickable { onFavoriteButtonClick() }
            .size(dimensionResource(id = R.dimen.large_spacing))
            .background(backgroundColor, CircleShape)
            .padding(dimensionResource(id = R.dimen.micro_spacing)),
        tint = Color.White
    )
}