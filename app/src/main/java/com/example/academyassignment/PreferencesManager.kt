package com.example.academyassignment

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class PreferencesManager() {

    companion object {
        private const val PREFS_NAME = "crypto_prefs"
        private const val FAVORITES_KEY = "favorite_cryptos"

        private lateinit var sharedPreferences: SharedPreferences
        private val gson = Gson()

        fun initialize(context: Context) {
            sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        }

        fun addFavoriteCrypto(id: String) {
            val favorites = getFavoriteCryptos().toMutableSet()
            favorites.add(id)
            setFavoriteCryptos(favorites)
        }

        fun removeFavoriteCrypto(id: String) {
            val favorites = getFavoriteCryptos().toMutableSet()
            favorites.remove(id)
            setFavoriteCryptos(favorites)
        }

        fun isFavoriteCrypto(id: String): Boolean {
            return getFavoriteCryptos().contains(id)
        }

        private fun setFavoriteCryptos(favorites: Set<String>) {
            val editor = sharedPreferences.edit()
            val jsonString = gson.toJson(favorites.toList())
            editor.putString(FAVORITES_KEY, jsonString)
            editor.apply()
        }

        fun getFavoriteCryptos(): List<String> {
            val jsonString = sharedPreferences.getString(FAVORITES_KEY, null)
            if (jsonString != null) {
                val type = object : TypeToken<List<String>>() {}.type
                return gson.fromJson(jsonString, type)
            }
            return emptyList()
        }

    }
}
