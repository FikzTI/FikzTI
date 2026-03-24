package org.chevalierlabsas.Kashier.history.data

import org.chevalierlabsas.Kashier.history.domain.TransactionHistory

class HistoryDummyDataSource {

    fun getHistoryData(): List<TransactionHistory> = listOf(
        TransactionHistory(250000.0, 10, "2024-03-20"),
        TransactionHistory(200000.0, 8, "2024-03-18"),
        TransactionHistory(150000.0, 5, "2024-03-15"),
        TransactionHistory(300000.0, 12, "2024-03-10"),
        TransactionHistory(100000.0, 3, "2024-02-20")
    )
}