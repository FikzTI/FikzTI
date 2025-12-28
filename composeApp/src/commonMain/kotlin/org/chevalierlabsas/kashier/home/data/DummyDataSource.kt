package org.chevalierlabsas.kashier.home.data

import org.chevalierlabsas.kashier.home.domain.Item

class DummyDataSource {

    fun getData(): List<Item> = listOf(
        Item(
            id = 1,
            userId = 1,
            name = "Telur 0.5 KG",
            price = 12000.0
        ),
        Item(
            id = 2,
            userId = 1,
            name = "Jeruk Mandarin 1 KG",
            price = 30000.0
        ),
        Item(
            id = 3,
            userId = 1,
            name = "Alpukat 250 GR",
            price = 50000.0
        ),
        Item(
            id = 4,
            userId = 1,
            name = "Teh Celup 25 PCS",
            price = 15500.0
        ),
        Item(
            id = 5,
            userId = 1,
            name = "Kepiting Laut 2 KG",
            price = 120000.0
        )
    )
}