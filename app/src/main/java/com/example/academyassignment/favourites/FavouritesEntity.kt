package com.example.academyassignment.favourites

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favourites")
data class FavouritesEntity(
    @ColumnInfo("id") @PrimaryKey var id: String, // we use ColumnInfo when using Room, in order to map fields to columns in a SQLite database
    @ColumnInfo("symbol") val symbol: String,
    @ColumnInfo("name") val name: String,
    @ColumnInfo("image") val image: String,
    @ColumnInfo("current_price") var currentPrice: Double,
    @ColumnInfo("price_change24")var priceChangePercentage24h: Double
)