package org.chevalierlabsas.kashier.home.presentation.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable

@Composable
fun BarangTextField(
    value: String,
    placeholder: String,
    onValueChange: (String) -> Unit
) {

    TextField(
        value = value,
        onValueChange = onValueChange,

        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Edit,
                contentDescription = null
            )
        },

        placeholder = {
            Text(placeholder)
        },

        colors = TextFieldDefaults.colors(

            focusedContainerColor = MaterialTheme.colorScheme.onTertiaryContainer,

            unfocusedContainerColor = MaterialTheme.colorScheme.tertiaryContainer
        )
    )
}