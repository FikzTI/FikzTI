package org.chevalierlabsas.Kashier.history.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.chevalierlabsas.Kashier.history.domain.TransactionHistory
import org.chevalierlabsas.Kashier.history.domain.repository.HistoryRepository

data class HistoryState(
    val items: List<TransactionHistory> = emptyList(),
    val isLoading: Boolean = false
)

class HistoryViewModel(
    private val repository: HistoryRepository
) : ViewModel() {

    private val _state = MutableStateFlow(HistoryState())
    val state = _state.asStateFlow()

    init {
        loadData()
    }

    fun loadData() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            val data = repository.getHistory()
            _state.update { it.copy(items = data, isLoading = false) }
        }
    }
}