package agency.five.tmdb.ui.components

import agency.five.tmdb.R
import agency.five.tmdb.ui.theme.Navy
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TmdbTopAppBar(
    modifier: Modifier = Modifier,
    navigationIcon: @Composable () -> Unit = {}
) {
    CenterAlignedTopAppBar(
        modifier = modifier,
        title = {
            Image(
                painter = painterResource(id = R.drawable.ic_logo),
                contentDescription = null,
                modifier = Modifier.size(
                    width = dimensionResource(id = R.dimen.logo_width),
                    height = dimensionResource(id = R.dimen.logo_height)
                )
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = Navy),
        navigationIcon = navigationIcon
    )
}

@Preview
@Composable
fun TmdbTopAppBarPreview() {
    TmdbTopAppBar()
}