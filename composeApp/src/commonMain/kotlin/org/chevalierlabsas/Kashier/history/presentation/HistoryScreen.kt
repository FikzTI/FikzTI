@file:OptIn(ExperimentalTime::class)

package org.chevalierlabsas.Kashier.history.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.todayIn
import org.chevalierlabsas.Kashier.history.domain.TransactionHistory
import kotlin.time.Clock
import kotlin.time.ExperimentalTime


fun getTimeRangeLabel(dateString: String): String {
    return try {
        val transactionDate = LocalDate.parse(dateString)
        val today = Clock.System.todayIn(TimeZone.currentSystemDefault())
        val diff = today.toEpochDays() - transactionDate.toEpochDays()

        when {
            diff <= 0 -> "Hari Ini"
            diff in 1..7 -> "Minggu Ini"
            // Pengecekan bulan dan tahun yang sama
            transactionDate.month == today.month && transactionDate.year == today.year -> "Bulan Ini"
            else -> "Riwayat Lama"
        }
    } catch (e: Exception) {
        "Lainnya"
    }
}

fun formatRupiah(value: Double): String = "Rp ${value.toLong()}"

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HistoryScreen(
    state: HistoryState,
    onNavigateBack: () -> Unit
) {
    val groupedData = remember(state.items) {
        state.items.groupBy { getTimeRangeLabel(it.tanggal) }.toList()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Riwayat Transaksi") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null)
                    }
                }
            )
        }
    ) { padding ->
        if (state.isLoading) {
            Box(Modifier.fillMaxSize(), contentAlignment = androidx.compose.ui.Alignment.Center) {
                CircularProgressIndicator()
            }
        } else {
            LazyColumn(modifier = Modifier.padding(padding).padding(horizontal = 16.dp)) {
                groupedData.forEach { (header, items) ->
                    item {
                        Text(header, fontWeight = FontWeight.Bold, modifier = Modifier.padding(vertical = 8.dp))
                    }
                    items(items) { data ->
                        HistoryCard(data)
                    }
                }
            }
        }
    }
}

@Composable
fun HistoryCard(data: TransactionHistory) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF3E5F5))
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text("Total")
                Text(formatRupiah(data.totalHarga), fontWeight = FontWeight.Bold)
            }
            Text("${data.totalBarang} Item", style = MaterialTheme.typography.bodySmall)
        }
    }
}