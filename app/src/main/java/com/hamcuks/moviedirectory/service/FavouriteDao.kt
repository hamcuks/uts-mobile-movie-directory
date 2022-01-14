package com.hamcuks.moviedirectory.service

import androidx.lifecycle.LiveData
import androidx.room.*
import com.hamcuks.moviedirectory.model.ResultMovie

@Dao
interface FavouriteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFavourite(data: ResultMovie)

    @Delete
    suspend fun deleteFavourite(data: ResultMovie)

    @Query("SELECT * FROM movie")
    fun getAllFavs(): LiveData<List<ResultMovie>>

    @Query("SELECT * FROM movie WHERE id=:id")
    open fun isFavourite(id: Int): LiveData<List<ResultMovie>>
}