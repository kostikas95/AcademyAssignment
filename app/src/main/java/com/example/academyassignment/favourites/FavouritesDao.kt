package com.example.academyassignment.favourites

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface FavouritesDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(favourite: FavouritesEntity)

    @Query("SELECT * FROM favourites")
    fun getAllFavourites(): List<FavouritesEntity>

    @Query("DELETE FROM favourites WHERE id = :id")
    fun deleteById(id: String)


}