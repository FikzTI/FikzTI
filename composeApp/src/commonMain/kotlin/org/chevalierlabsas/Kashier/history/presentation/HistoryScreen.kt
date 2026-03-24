package org.chevalierlabsas.Kashier.history.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import org.chevalierlabsas.Kashier.history.data.HistoryDummyDataSource
import org.chevalierlabsas.Kashier.history.domain.TransactionHistory
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class)
fun getTimeRangeLabel(dateString: String): String {
    return try {
        val transactionDate = LocalDate.parse(dateString)
        val today = Clock.System.todayIn(TimeZone.currentSystemDefault())
        val diff = today.toEpochDays() - transactionDate.toEpochDays()

        when {
            diff <= 0 -> "Hari Ini"
            diff in 1..7 -> "Minggu Ini"
            transactionDate.month == today.month && transactionDate.year == today.year -> "Bulan Ini"
            else -> "Riwayat Lama"
        }
    } catch (e: Exception) { "Lainnya" }
}

fun formatIndonesianDate(dateString: String): String {
    return try {
        val date = LocalDate.parse(dateString)
        val bulan = listOf("Januari","Februari","Maret","April","Mei","Juni","Juli","Agustus","September","Oktober","November","Desember")
        "${date.dayOfMonth} ${bulan[date.monthNumber - 1]} ${date.year}"
    } catch (e: Exception) { dateString }
}

fun formatRupiah(value: Double): String {
    val text = value.toLong().toString().reversed().chunked(3).joinToString(".").reversed()
    return "Rp $text"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HistoryScreen(onNavigateBack: () -> Unit) {
    val rawData = remember { HistoryDummyDataSource().getHistoryData() }

    val groupedData = remember(rawData) {
        rawData
            .groupBy { getTimeRangeLabel(it.tanggal) }
            .toList()
            .sortedBy { pair ->
                when (pair.first) {
                    "Hari Ini" -> 0
                    "Minggu Ini" -> 1
                    "Bulan Ini" -> 2
                    else -> 3
                }
            }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Riwayat Transaksi", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null)
                    }
                }
            )
        }
    ) { padding ->
        LazyColumn(modifier = Modifier.padding(padding).padding(horizontal = 16.dp)) {
            groupedData.forEach { (header, items) ->
                item {
                    Text(header, fontWeight = FontWeight.Bold, modifier = Modifier.padding(vertical = 12.dp))
                }
                items(items) { data ->
                    HistoryCard(data)
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
        Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text("Total Harga")
                Text(formatRupiah(data.totalHarga), fontWeight = FontWeight.Bold)
            }
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text("Total Barang")
                Text("${data.totalBarang} Item")
            }
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text("Tanggal")
                Text(formatIndonesianDate(data.tanggal), color = Color.Gray)
            }
        }
    }
}