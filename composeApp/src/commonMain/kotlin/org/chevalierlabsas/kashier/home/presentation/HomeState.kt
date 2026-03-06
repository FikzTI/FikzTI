package org.chevalierlabsas.kashier.home.presentation

import org.chevalierlabsas.kashier.home.domain.Item

data class HomeState(
    val totalPrice: Double = 0.0,
    val selectedItems: List<Item> = emptyList(),
    val showSelectedItem: Boolean = true,
    val showAllItem: Boolean = true,
    val items: List<Item> = emptyList(),
    val searchQuery: String = "",
    val namaBarang: String = "",
    val hargaBarang: String = "",
    val isEdit: Boolean = false
) {
    val filteredItems: List<Item>
        get() = if (searchQuery.isBlank()) {
            items
        } else {
            items.filter {
                it.name.contains(searchQuery, ignoreCase = true)
            }
        }
}