package com.example.academyassignment.model

import com.google.gson.annotations.SerializedName
data class CryptoData(
    @SerializedName("id") val id: String, // we use SerializedName when using Json, in order to specify the keys and the fields
    @SerializedName("symbol") val symbol: String,
    @SerializedName("name") val name: String,
    @SerializedName("image") val image: String,
    @SerializedName("current_price") var currentPrice: Double,
    @SerializedName("price_change24")var priceChangePercentage24h: Double
) {
    // val market_cap: Double
    // val price_change_24h: Double

}
