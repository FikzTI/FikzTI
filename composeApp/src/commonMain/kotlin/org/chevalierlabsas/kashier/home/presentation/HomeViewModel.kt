package org.chevalierlabsas.kashier.home.presentation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import org.chevalierlabsas.kashier.home.data.DummyDataSource
import org.chevalierlabsas.kashier.home.domain.Item

class HomeViewModel : ViewModel() {

    private val allItems = DummyDataSource().getData()

    private val _state = MutableStateFlow(HomeState(items = allItems))
    val state = _state.asStateFlow()

    fun onEvent(event: HomeEvent) {
        when (event) {

            is HomeEvent.OnAddItem -> addItem(event.item)

            is HomeEvent.OnRemoveItem -> removeItem(event.item)

            is HomeEvent.OnAllItemVisibilityChange ->
                setAllItemVisibility(event.isVisible)

            is HomeEvent.OnSelectedItemVisibilityChange ->
                setSelectedItemVisibility(event.isVisible)

            is HomeEvent.OnSearchQueryChange ->
                updateQuery(event.query)

            HomeEvent.OnSearchQuerySubmit ->
                search()

            HomeEvent.OnSaveTransaction ->
                saveTransaction()

            is HomeEvent.OnNamaBarangChange ->
                updateNamaBarang(event.value)

            is HomeEvent.OnHargaBarangChange ->
                updateHargaBarang(event.value)

            HomeEvent.OnSaveClick ->
                saveBarang()
        }
    }

    private fun search() {
        val query = state.value.searchQuery

        if (query.isNotBlank()) {
            _state.update {
                it.copy(
                    items = allItems.filter { item ->
                        item.name.contains(query, ignoreCase = true)
                    }
                )
            }
        } else {
            _state.update { it.copy(items = allItems) }
        }
    }

    private fun removeItem(item: Item) {
        _state.update {
            it.copy(
                selectedItems = it.selectedItems - item,
                totalPrice = it.totalPrice - item.price
            )
        }
    }

    private fun addItem(item: Item) {
        _state.update {
            it.copy(
                selectedItems = it.selectedItems + item,
                totalPrice = it.totalPrice + item.price
            )
        }
    }

    private fun setAllItemVisibility(visible: Boolean) {
        _state.update { it.copy(showAllItem = visible) }
    }

    private fun setSelectedItemVisibility(visible: Boolean) {
        _state.update { it.copy(showSelectedItem = visible) }
    }

    private fun updateQuery(query: String) {
        _state.update { it.copy(searchQuery = query) }
    }

    private fun updateNamaBarang(nama: String) {
        _state.update { it.copy(namaBarang = nama) }
    }

    private fun updateHargaBarang(harga: String) {
        _state.update { it.copy(hargaBarang = harga) }
    }

    private fun saveBarang() {
        println("Nama Barang: ${state.value.namaBarang}")
        println("Harga Barang: ${state.value.hargaBarang}")
    }

    private fun saveTransaction() {
        println("Transaction Saved")
    }
}