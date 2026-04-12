package org.chevalierlabsas.Kashier.history.domain.repository

import org.chevalierlabsas.Kashier.history.domain.TransactionHistory

interface HomeRepository {
    suspend fun getItems(): List<TransactionHistory>
    // Fungsi lainnya (postItem, dll) bisa ditambah nanti sesuai gambar tantangan
}