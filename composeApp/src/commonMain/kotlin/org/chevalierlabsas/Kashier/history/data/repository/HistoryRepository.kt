package org.chevalierlabsas.Kashier.history.domain.repository

import org.chevalierlabsas.Kashier.history.domain.TransactionHistory

interface HistoryRepository {
    suspend fun getHistory(): List<TransactionHistory>
}