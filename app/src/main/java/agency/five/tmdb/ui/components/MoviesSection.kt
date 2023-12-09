package agency.five.tmdb.ui.components

import agency.five.tmdb.R
import agency.five.tmdb.domain.common.Movie
import agency.five.tmdb.ui.state.MoviesUiState
import agency.five.tmdb.ui.theme.Navy
import agency.five.tmdb.ui.theme.Typography
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource

@Composable
fun MoviesSection(
    modifier: Modifier = Modifier,
    title: String,
    moviesUiState: MoviesUiState,
    tabData: List<String>,
    onMovieClick: (Int) -> Unit = {},
    onFavoriteButtonClick: (updatedMovie: Movie) -> Unit = {}
) {

    Column(modifier = modifier.fillMaxWidth()) {

        Text(
            text = title,
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = dimensionResource(id = R.dimen.screen_content_padding),
                    end = dimensionResource(id = R.dimen.screen_content_padding)
                ),
            style = Typography.h6
        )

        if (!moviesUiState.movies.isNullOrEmpty()) {
            TabList(
                tabData = tabData,
                moviesLists = moviesUiState.movies,
                onMovieClick = onMovieClick,
                onFavoriteButtonClick = onFavoriteButtonClick
            )
        }

        if (moviesUiState.error != null) {
            Text(
                text = moviesUiState.error,
                modifier = Modifier.padding(
                    start = dimensionResource(id = R.dimen.screen_content_padding),
                    top = dimensionResource(id = R.dimen.no_connection_text_top_padding),
                    bottom = dimensionResource(id = R.dimen.no_connection_text_bottom_padding)
                )
            )
        }

        if (moviesUiState.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier
                    .padding(
                        top = dimensionResource(id = R.dimen.progress_bar_top_padding),
                        bottom = dimensionResource(id = R.dimen.progress_bar_bottom_padding)
                    )
                    .align(Alignment.CenterHorizontally),
                color = Navy
            )
        }
    }
}