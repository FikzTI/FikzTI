package org.chevalierlabsas.Kashier.history.presentation
import org.chevalierlabsas.Kashier.history.domain.TransactionHistory

data class HistoryState(
    val items: List<TransactionHistory> = emptyList(),
    val isLoading: Boolean = false
)