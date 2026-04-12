package org.chevalierlabsas.Kashier.history.data

import org.chevalierlabsas.Kashier.history.domain.TransactionHistory
import org.chevalierlabsas.Kashier.history.domain.repository.HistoryRepository

class HistoryRepositoryImpl(
    private val dataSource: HistoryDataSource
) : HistoryRepository {
    override suspend fun getHistory(): List<TransactionHistory> {
        return dataSource.getHistoryData()
    }
}