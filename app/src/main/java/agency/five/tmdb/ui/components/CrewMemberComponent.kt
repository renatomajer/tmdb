package agency.five.tmdb.ui.components

import agency.five.tmdb.domain.common.CrewMember
import agency.five.tmdb.ui.theme.Typography
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight

@Composable
fun CrewMemberComponent(
    crewMember: CrewMember
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = crewMember.name,
            style = Typography.subtitle2,
            fontWeight = FontWeight.ExtraBold,
            color = Color.Black
        )

        Text(
            text = crewMember.department,
            style = Typography.body2,
            color = Color.Black
        )
    }
}