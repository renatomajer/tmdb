package agency.five.tmdb.ui.components.cast

import agency.five.tmdb.R
import agency.five.tmdb.domain.common.CastMember
import agency.five.tmdb.domain.common.Constants.IMAGE_BASE_URL
import agency.five.tmdb.domain.common.Constants.IMAGE_SMALL
import agency.five.tmdb.ui.theme.Gray
import agency.five.tmdb.ui.theme.Shapes
import agency.five.tmdb.ui.theme.Typography
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import coil.compose.rememberAsyncImagePainter

@Composable
fun CastMemberCard(
    castMember: CastMember,
    modifier: Modifier = Modifier,
    onCastMemberClick: () -> Unit = {}
) {
    Surface(
        elevation = dimensionResource(id = R.dimen.card_elevation),
        color = Color.Transparent
    ) {
        Box(
            modifier = modifier
                .clickable { onCastMemberClick() }
                .width(dimensionResource(id = R.dimen.cast_member_card_width))
                .height(dimensionResource(id = R.dimen.cast_member_card_height))
                .clip(Shapes.medium)
                .background(Color.White)
        ) {
            Column() {
                Image(
                    painter = getCastMemberImage(profilePath = castMember.profilePath),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .height(dimensionResource(id = R.dimen.cast_member_image_height))
                        .width(dimensionResource(id = R.dimen.cast_member_image_width))
                )

                Text(
                    text = castMember.name,
                    style = Typography.subtitle2,
                    fontWeight = FontWeight.ExtraBold,
                    color = Color.Black,
                    modifier = Modifier.padding(
                        start = dimensionResource(id = R.dimen.cast_member_card_name_padding_start),
                        end = dimensionResource(id = R.dimen.cast_member_card_name_padding_end)
                    )
                )

                Text(
                    text = castMember.character,
                    style = Typography.caption,
                    color = Gray,
                    modifier = Modifier
                        .padding(
                            start = dimensionResource(id = R.dimen.cast_member_card_name_padding_start)
                        )
                )
            }
        }
    }
}

@Composable
fun getCastMemberImage(profilePath: String?): Painter {
    return if (profilePath != null) {
        rememberAsyncImagePainter("${IMAGE_BASE_URL}${IMAGE_SMALL}$profilePath")
    } else {
        painterResource(id = R.drawable.default_profile_avatar)
    }
}