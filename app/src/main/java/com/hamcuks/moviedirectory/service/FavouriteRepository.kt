package com.hamcuks.moviedirectory.service

import androidx.lifecycle.LiveData
import com.hamcuks.moviedirectory.model.ResultMovie
import com.hamcuks.moviedirectory.service.FavouriteDao

class FavouriteRepository(private val favouriteDao: FavouriteDao) {
    suspend fun addFavourite(data: ResultMovie) = favouriteDao.addFavourite(data)
    suspend fun deleteFavourite(data: ResultMovie) = favouriteDao.deleteFavourite(data)
    fun getAllFavs(): LiveData<List<ResultMovie>> = favouriteDao.getAllFavs()
}