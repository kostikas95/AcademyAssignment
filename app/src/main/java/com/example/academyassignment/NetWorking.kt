package com.example.academyassignment

import com.example.academyassignment.model.CryptoData
import com.example.academyassignment.repositories.CryptosRepository
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONArray

class NetWorking {

    public fun queryForAllCryptos() {
        var baseUrl: String = "https://api.coingecko.com/api/v3/coins/markets?vs_currency=usd"

        val client = OkHttpClient()
        val request = Request.Builder()
            .url("https://api.coingecko.com/api/v3/coins/markets?vs_currency=usd")
            .get()
            .addHeader("accept", "application/json")
            .addHeader("x-cg-demo-api-key", "CG-rYPvzHBEzVoyiZ7eR7sVmShp")
            .build()

        val response = client.newCall(request).execute()
    }

    public fun queryForSeveralCryptos(page: Int, numberPerPage: Int) {
        var baseUrl: String = "https://api.coingecko.com/api/v3/coins/markets?vs_currency=usd"

        val client = OkHttpClient()
        val request = Request.Builder()
            .url("https://api.coingecko.com/api/v3/coins/markets?vs_currency=usd")
            .get()
            .addHeader("accept", "application/json")
            .addHeader("x-cg-demo-api-key", "CG-rYPvzHBEzVoyiZ7eR7sVmShp")
            .build()

        val response = client.newCall(request).execute()
    }



    public fun parseAndAppend(response: String) {
        val jsonArray = JSONArray(response)

        for (i in 0 until jsonArray.length()) {
            val jsonObject = jsonArray.getJSONObject(i)
            val id: String = jsonObject.getString("id")
            val symbol: String = jsonObject.getString("symbol")
            val name: String = jsonObject.getString("name")
            val image: String = jsonObject.getString("image")
            val currentPrice: Double = jsonObject.getDouble("current_price")
            val priceChangePercentage24h: Double = jsonObject.getDouble("price_change_percentage_24h")

            val crypto: CryptoData?  = CryptosRepository.getCrypto(id)
            if (crypto == null) {
                val newCrypto = CryptoData(id, symbol, name, image, currentPrice, priceChangePercentage24h)
                CryptosRepository.addElement(newCrypto)
            }
            else {
                crypto.currentPrice = currentPrice
                crypto.priceChangePercentage24h = priceChangePercentage24h
            }
        }
    }
}