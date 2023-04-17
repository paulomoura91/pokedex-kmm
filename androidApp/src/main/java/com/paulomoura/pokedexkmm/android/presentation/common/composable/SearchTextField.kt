package com.paulomoura.pokedexkmm.android.presentation.common.composable

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.paulomoura.pokedexkmm.android.presentation.ui.theme.DarkWhite
import com.paulomoura.pokedexkmm.android.presentation.ui.theme.LightBlack

@Composable
fun SearchTextField(searchQuery: String, onValueChange: (String) -> Unit, darkTheme: Boolean = isSystemInDarkTheme()) {
    TextField(
        value = searchQuery,
        onValueChange = { onValueChange(it) },
        modifier = Modifier.fillMaxWidth(),
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null,
                modifier = Modifier
                    .padding(16.dp)
                    .size(24.dp)
            )
        },
        trailingIcon = {
            if (searchQuery.isNotBlank()) {
                IconButton(
                    onClick = {
                        onValueChange("")
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = null,
                        modifier = Modifier
                            .padding(16.dp)
                            .size(24.dp)
                    )
                }
            }
        },
        singleLine = true,
        colors = if (darkTheme) TextFieldDefaults.textFieldColors(
            leadingIconColor = Color.White,
            trailingIconColor = Color.White,
            backgroundColor = LightBlack
        ) else TextFieldDefaults.textFieldColors(
            leadingIconColor = Color.Black,
            trailingIconColor = Color.Black,
            backgroundColor = DarkWhite
        )
    )
}