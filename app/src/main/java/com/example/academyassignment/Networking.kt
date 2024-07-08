package com.example.academyassignment

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Log
import com.example.academyassignment.model.CryptoBasicData
import com.example.academyassignment.model.CryptoDetailedData
import com.example.academyassignment.model.VsCurrency
import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

class Networking {

    companion object {

        // used to check connection to the api
        fun ping(): String{
            val client = OkHttpClient()
            val url: String = "https://api.coingecko.com/api/v3/ping"
            val request = Request.Builder()
                .url(url)
                .get()
                .addHeader("accept", "application/json")
                .build()
            return try {
                val response: Response = client.newCall(request).execute()
                if (!response.isSuccessful) {
                    throw IOException("Unexpected code $response")
                }
                response.body?.string() ?: "No response body"
            } catch (e: Exception) {
                e.printStackTrace()
                "Request failed: ${e.message}"
            }
        }

        // used to get all the cryptos in a list
        public suspend fun queryCoinListWithMarketData(vsCurrency: VsCurrency) : List<CryptoBasicData> {
            val url: String = "https://api.coingecko.com/api/v3/coins/markets?vs_currency=$vsCurrency"

            val client = OkHttpClient()
            val request = Request.Builder()
                .url(url)
                .get()
                .addHeader("accept", "application/json")
                .build()
            val response = client.newCall(request).execute()
            val responseString: String = response.body?.string() ?: throw IOException("Empty response body")
            return Gson().fromJson(responseString, Array<CryptoBasicData>::class.java).toList()
        }

        // used to get a batch of cryptos
        public suspend fun queryCoinListWithMarketData(vsCurrency: VsCurrency, page: Int, numberPerPage: Int) : List<CryptoBasicData> {
            var url: String = "https://api.coingecko.com/api/v3/coins/markets?vs_currency=$vsCurrency"
            url = url + "&per_page=$numberPerPage" + "&page=$page"

            val client = OkHttpClient()
            val request = Request.Builder()
                .url(url)
                .get()
                .addHeader("accept", "application/json")
                .build()
            val response = client.newCall(request).execute()
            val responseString: String = response.body?.string() ?: throw IOException("Empty response body")
            return Gson().fromJson(responseString, Array<CryptoBasicData>::class.java).toList()
        }

        // used to get a specific crypto, via its id
        public suspend fun queryCoinListWithMarketData(vsCurrency: VsCurrency, id: String): CryptoDetailedData {
            var url: String = "https://api.coingecko.com/api/v3/coins/markets?vs_currency=$vsCurrency"
            url = url + "&ids=$id"

            val client = OkHttpClient()
            val request = Request.Builder()
                .url(url)
                .get()
                .addHeader("accept", "application/json")
                .build()
            val response = client.newCall(request).execute()
            val responseString: String = response.body?.string() ?: throw IOException("Empty response body")
            val crypto: CryptoDetailedData = Gson().fromJson(responseString, Array<CryptoDetailedData>::class.java)[0]
            return crypto
        }

        // used to get a list of specific cryptos, via their ids
        public suspend fun queryCoinListWithMarketData(vsCurrency: VsCurrency, ids: List<String>): List<CryptoBasicData> {
            var url: String = "https://api.coingecko.com/api/v3/coins/markets?vs_currency=$vsCurrency"
            url = url + "&ids="
            for (id: String in ids) {
                url = url + "$id%2C"
            }
            url = url.substring(0, url.length - 3)

            val client = OkHttpClient()
            val request = Request.Builder()
                .url(url)
                .get()
                .addHeader("accept", "application/json")
                .build()
            val response = client.newCall(request).execute()
            val responseString: String = response.body?.string() ?: throw IOException("Empty response body")
            val cryptos: List<CryptoBasicData> = Gson().fromJson(responseString, Array<CryptoBasicData>::class.java).toList()

            // val names: String = cryptos.map { it.name }.joinToString(",")
            // Log.d("NETWORKING2", "${cryptos.size} " + names)

            return cryptos
        }

        // used to get a list of cryptos with ids similar to the text inserted by the user
        public suspend fun searchForSimilar(vsCurrency: VsCurrency, strInput: String) : List<CryptoBasicData> {
            val url: String = "https://api.coingecko.com/api/v3/search?query=$strInput"
            val client = OkHttpClient()

            val request = Request.Builder()
                .url(url)
                .get()
                .addHeader("accept", "application/json")
                .build()

            val response = client.newCall(request).execute()
            val responseString: String = response.body?.string() ?: throw IOException("Empty response body")

            val gson = Gson()
            val jsonObject = JsonParser.parseString(responseString).asJsonObject
            val coinsArray: JsonArray = jsonObject.getAsJsonArray("coins")

            val ids: List<String> = coinsArray.map { coinElement ->
                val coinObject = coinElement.asJsonObject
                val id = coinObject.get("id").asString
                id
            }

            // val idString: String = ids.joinToString(",")
            // Log.d("NETWORKING1", "${ids.size} " + idString)

            return queryCoinListWithMarketData(vsCurrency, ids)
        }
    }

}