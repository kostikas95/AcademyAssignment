package com.example.academyassignment.model

data class CryptoDetailedData(
    val id: String,
    val symbol: String,
    val name: String,
    val image: String,
    var current_price: Double,
    var price_change_percentage_24h: Double,
    var market_cap: Double,
    var market_cap_rank: Int,
    var market_cap_change_percentage_24h: Double,
    var total_volume: Long,
    var ath_change_percentage: Double
) {}
