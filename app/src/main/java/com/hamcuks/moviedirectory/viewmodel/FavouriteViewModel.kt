package com.hamcuks.moviedirectory.viewmodel

import android.app.Application
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.room.RoomDatabase
import com.hamcuks.moviedirectory.database.MoviedDB
import com.hamcuks.moviedirectory.model.ResultMovie
import com.hamcuks.moviedirectory.service.FavouriteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class FavouriteVMFactory(private val application: Application) : ViewModelProvider.NewInstanceFactory(){
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = FavouriteViewModel(application = application) as T
}

class FavouriteViewModel(application: Application) : ViewModel() {
    private val repository: FavouriteRepository
    var movieList: List<ResultMovie> by mutableStateOf(listOf())
    private var _fetchAll: LiveData<List<ResultMovie>>

    init {
        val favouriteDb = MoviedDB.getDb(application).favouriteDao()
        repository = FavouriteRepository(favouriteDb)
        _fetchAll = repository.getAllFavs()
    }

    fun getFavs() : LiveData<List<ResultMovie>> {
        return _fetchAll
    }

    fun addFavourite(data: ResultMovie) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addFavourite(data)
        }
    }

    fun deleteFavourite(data: ResultMovie) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteFavourite(data)
        }
    }

    fun isFavourite(id: Int, activity: ComponentActivity) : Boolean {

        var res: Boolean = false
        _fetchAll.observe(activity) {
            Log.d("DEBUG FAV", res.toString())
        }

        return res
    }


}