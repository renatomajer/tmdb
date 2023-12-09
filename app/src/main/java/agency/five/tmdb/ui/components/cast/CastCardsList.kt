package agency.five.tmdb.ui.components.cast

import agency.five.tmdb.R
import agency.five.tmdb.domain.common.CastMember
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource

@Composable
fun CastCardsList(
    modifier: Modifier = Modifier,
    onCastMemberClick: (CastMember) -> Unit = {},
    cast: List<CastMember>
) {
    LazyRow(
        modifier = modifier.fillMaxWidth(),
        contentPadding = PaddingValues(
            horizontal = dimensionResource(id = R.dimen.cards_list_content_padding),
            vertical = dimensionResource(id = R.dimen.cards_list_content_padding)
        )
    ) {
        items(cast) {
            CastMemberCard(
                castMember = it,
                modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.micro_spacing)),
                onCastMemberClick = { onCastMemberClick(it) }
            )
        }
    }
}