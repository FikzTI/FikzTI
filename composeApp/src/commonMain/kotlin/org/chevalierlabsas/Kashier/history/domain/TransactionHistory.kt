package org.chevalierlabsas.Kashier.history.domain

data class TransactionHistory(
    val tanggal: String,
    val totalHarga: Double,
    val totalBarang: Int
)