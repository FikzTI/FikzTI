package org.chevalierlabsas.kashier.home.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.chevalierlabsas.kashier.home.domain.Item

@Composable
fun ItemCard(
    item: Item,
    modifier: Modifier = Modifier,
    onAddClick: () -> Unit,
    onEditClick: () -> Unit
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondary
        )
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = item.name,
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = "Rp. ${item.price.toInt()}",
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            IconButton(onClick = onEditClick) {
                Icon(Icons.Default.Edit, contentDescription = "Edit")
            }

            IconButton(onClick = onAddClick) {
                Icon(Icons.Default.Add, contentDescription = "Add")
            }
        }
    }
}