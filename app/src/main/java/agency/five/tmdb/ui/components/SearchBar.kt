package agency.five.tmdb.ui.components

import agency.five.tmdb.R
import agency.five.tmdb.ui.theme.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Clear
import androidx.compose.material.icons.rounded.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview


@Composable
fun SearchBar() {
    var text by remember { mutableStateOf(value = "") }
    var showClearIcon by remember { mutableStateOf(false) }

    if (text.isEmpty()) {
        showClearIcon = false
    } else if (text.isNotEmpty()) {
        showClearIcon = true
    }

    TextField(
        value = text,
        onValueChange = { text = it },
        singleLine = true,
        modifier = Modifier
            .padding(
                start = dimensionResource(id = R.dimen.screen_content_padding),
                end = dimensionResource(id = R.dimen.screen_content_padding),
                top = dimensionResource(id = R.dimen.search_bar_top_bottom_padding),
                bottom = dimensionResource(id = R.dimen.search_bar_top_bottom_padding)
            )
            .background(
                color = LightGray,
                shape = Shapes.small
            )
            .fillMaxWidth(),
        leadingIcon = {
            Icon(
                Icons.Rounded.Search,
                contentDescription = stringResource(id = R.string.search_icon),
                modifier = Modifier.size(size = dimensionResource(id = R.dimen.search_bar_icon_size)),
                tint = Navy
            )
        },
        textStyle = Typography.body1,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        trailingIcon = {
            if (showClearIcon) {
                IconButton(onClick = { text = "" }) {
                    Icon(
                        imageVector = Icons.Rounded.Clear,
                        tint = Navy,
                        contentDescription = stringResource(id = R.string.clear_icon)
                    )
                }
            }
        },
        colors = TextFieldDefaults.textFieldColors(
            focusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            backgroundColor = Color.Transparent,
            cursorColor = Navy,
            unfocusedLabelColor = Color.Transparent
        ),
        label = {
            Text(
                text = stringResource(id = R.string.search),
                style = Typography.body1,
                color = GrayNavy
            )
        }
    )
}


@Preview
@Composable
fun SearchBarPreview() {
    SearchBar()
}