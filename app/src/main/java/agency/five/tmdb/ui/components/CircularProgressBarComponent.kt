package agency.five.tmdb.ui.components

import agency.five.tmdb.R
import agency.five.tmdb.ui.theme.DarkGreen
import agency.five.tmdb.ui.theme.Green
import agency.five.tmdb.ui.theme.Typography
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.sp

@Composable
fun CircularProgressBarComponent(
    modifier: Modifier = Modifier,
    value: Int = 0
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.size(dimensionResource(id = R.dimen.circular_progress_bar_size))
    ) {
        CircularProgressIndicator(
            progress = 1f,
            color = DarkGreen,
            strokeWidth = dimensionResource(id = R.dimen.circular_progress_bar_stroke_width)
        )

        CircularProgressIndicator(
            progress = value / 100f,
            color = Green,
            strokeWidth = dimensionResource(id = R.dimen.circular_progress_bar_stroke_width)
        )

        Text(
            text = "$value%",
            style = Typography.subtitle2,
            color = Color.White,
            fontSize = 9.sp
        )
    }
}