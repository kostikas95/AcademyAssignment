package com.example.academyassignment.repositories

import com.example.academyassignment.model.CryptoData
import okhttp3.*
import org.json.JSONArray
import java.io.IOException

class CryptosRepository {

    private val cryptos: MutableList<CryptoData> = mutableListOf()

    companion object {
        // singleton instance
        @Volatile
        private var instance: CryptosRepository? = null

        // get + set
        fun getInstance(): CryptosRepository {
            if (instance == null)   instance = CryptosRepository()
            return instance!!
        }

        // static functions
        fun addElement(crypto: CryptoData) {
            getInstance().cryptos.add(crypto)
        }

        // fun addElements(cryptos: List<CryptoData>) {
        //     getInstance().cryptos.addAll(cryptos)
        // }

        fun getCryptos(): MutableList<CryptoData> {
            return getInstance().cryptos
        }

        fun getCrypto(id: String): CryptoData? {
            for (crypto: CryptoData in getInstance().cryptos) {
                if (crypto.id == id) {
                    return crypto
                }
            }
            return null
        }

    }



}