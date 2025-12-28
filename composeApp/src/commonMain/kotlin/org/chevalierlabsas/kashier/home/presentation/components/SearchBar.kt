@file:OptIn(ExperimentalMaterial3Api::class)

package org.chevalierlabsas.kashier.home.presentation.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kashier.composeapp.generated.resources.Res
import kashier.composeapp.generated.resources.searchbar_hint
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview


@Composable
fun Searchbar(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    OutlinedTextField(
        modifier = modifier,
        value = value,
        onValueChange = onValueChange,
        placeholder = {
            Text(stringResource(Res.string.searchbar_hint))
        },
        trailingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = stringResource(Res.string.searchbar_hint)
            )
        },
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = MaterialTheme.colorScheme.tertiary,
            unfocusedBorderColor = MaterialTheme.colorScheme.tertiaryContainer,
            focusedContainerColor = MaterialTheme.colorScheme.tertiaryContainer,
            unfocusedContainerColor = MaterialTheme.colorScheme.tertiaryContainer,
            cursorColor = MaterialTheme.colorScheme.onTertiaryContainer,
            focusedTrailingIconColor = MaterialTheme.colorScheme.onTertiaryContainer,
            unfocusedTrailingIconColor = MaterialTheme.colorScheme.onTertiaryContainer,
            focusedTextColor = MaterialTheme.colorScheme.onTertiaryContainer
        )
    )
}

@Preview
@Composable
fun SearchBarPreview() {
    var text by remember { mutableStateOf("") }
    Surface {
        Searchbar(
            value = text,
            onValueChange = { text = it },
            modifier = Modifier.padding(16.dp)
        )
    }
}
