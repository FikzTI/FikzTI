package org.chevalierlabsas.kashier.home.domain

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ExpandableDivider(
    modifier: Modifier = Modifier,
    title: String,
    isExpanded: Boolean,
    onToggle: () -> Unit
) {
    Column(modifier = modifier.fillMaxWidth()) {

        Row(
            modifier = Modifier.fillMaxWidth().clickable { onToggle() }.padding(vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            HorizontalDivider(
                modifier = Modifier.fillMaxWidth(),
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
            )



            Text(
                text = title,
                modifier = Modifier.padding(horizontal = 8.dp),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface
            )

            Icon(
                imageVector = if (isExpanded)
                    Icons.Default.KeyboardArrowUp
                else
                    Icons.Default.KeyboardArrowDown,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}
