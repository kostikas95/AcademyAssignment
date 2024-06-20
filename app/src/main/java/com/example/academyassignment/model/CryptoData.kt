package com.example.academyassignment.model

data class CryptoData(
    val id: String,
    val symbol: String,
    val name: String,
    val image: String,
    var currentPrice: Double,
    var priceChangePercentage24h: Double
) {
    // val market_cap: Double
    // val price_change_24h: Double
}
