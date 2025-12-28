package org.chevalierlabsas.kashier.home.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kashier.composeapp.generated.resources.Res
import kashier.composeapp.generated.resources.add_item_fab_label
import kashier.composeapp.generated.resources.all_item_label
import kashier.composeapp.generated.resources.app_name
import kashier.composeapp.generated.resources.selected_item_label
import org.chevalierlabsas.kashier.home.data.DummyDataSource
import org.chevalierlabsas.kashier.home.domain.Item
import org.chevalierlabsas.kashier.home.presentation.components.ItemCard
import org.chevalierlabsas.kashier.home.presentation.components.SaveButton
import org.chevalierlabsas.kashier.home.presentation.components.Searchbar
import org.chevalierlabsas.kashier.home.presentation.components.SectionDivider
import org.chevalierlabsas.kashier.home.presentation.components.SelectedItemChip
import org.chevalierlabsas.kashier.home.presentation.components.TotalPriceHeader
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {

    var totalPrice by remember { mutableStateOf(0.00) }
    val selectedItems = remember { mutableStateListOf<Item>() }
    var showSelectedItem by remember { mutableStateOf(true) }
    var showAllItem by remember { mutableStateOf(true) }
    var searchQuery by remember { mutableStateOf("") }
    val allItems = remember { DummyDataSource().getData() }
    val filteredItems = allItems.filter {
        it.name.contains(searchQuery, ignoreCase = true)
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(Res.string.app_name))
                }
            )
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                onClick = { },
                containerColor = MaterialTheme.colorScheme.tertiary,
                text = { Text(text = stringResource(Res.string.add_item_fab_label)) },
                icon = {
                    Icon(
                        Icons.Filled.Add,
                        contentDescription = stringResource(Res.string.add_item_fab_label)
                    )
                }
            )
        }
    ) { paddingValues ->

        LazyColumn(
            contentPadding = paddingValues
        ) {
            item {
                TotalPriceHeader(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    totalPrice = totalPrice
                )
            }
            item {
                SaveButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    onSave = { /* Save data */ },
                    enabled = selectedItems.isNotEmpty() && totalPrice > 0.00
                )
            }
            item {
                SectionDivider(
                    modifier = Modifier.padding(start = 16.dp, end = 4.dp),
                    title = stringResource(Res.string.selected_item_label),
                    visible = showSelectedItem,
                    onAction = { visible ->
                        showSelectedItem = visible
                    }
                )
            }
            item {
                AnimatedVisibility(
                    visible = showSelectedItem,
                    enter = expandVertically(),
                    exit = shrinkVertically()
                ) {
                    FlowRow(
                        modifier = Modifier.padding(horizontal = 16.dp),
                        verticalArrangement = Arrangement.spacedBy(4.dp),
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        selectedItems.forEach { item ->
                            SelectedItemChip(
                                onRemove = {
                                    selectedItems.remove(item)
                                    totalPrice -= item.price
                                },
                                item = item,
                                modifier = Modifier.padding(horizontal = 4.dp)
                            )
                        }
                    }
                }
            }
            item {
                SectionDivider(
                    modifier = Modifier.padding(start = 16.dp, end = 4.dp),
                    title = stringResource(Res.string.all_item_label),
                    visible = showAllItem,
                    onAction = { visible ->
                        showAllItem = visible
                    }
                )
            }
            item {
                Searchbar(
                    value = searchQuery,
                    onValueChange = { searchQuery = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                )
            }
            items(filteredItems) { item ->
                AnimatedVisibility(
                    visible = showAllItem,
                    enter = fadeIn(),
                    exit = fadeOut()
                ) {
                    ItemCard(
                        modifier = Modifier.padding(
                            horizontal = 16.dp,
                            vertical = 4.dp
                        ),
                        item = item,
                        onEditClick = { },
                        onAddClick = {
                            selectedItems.add(item)
                            totalPrice += item.price
                        }
                    )
                }
            }
        }
    }
}
@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}