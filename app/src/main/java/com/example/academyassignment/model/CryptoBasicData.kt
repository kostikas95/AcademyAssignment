package com.example.academyassignment.model

data class CryptoBasicData(
    val id: String,
    val symbol: String,
    val name: String,
    val image: String,
    var current_price: Double,
    var price_change_percentage_24h: Double
) {}
