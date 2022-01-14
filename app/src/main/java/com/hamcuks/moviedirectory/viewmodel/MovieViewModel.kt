package com.hamcuks.moviedirectory.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.toLowerCase
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hamcuks.moviedirectory.model.ResultMovie
import com.hamcuks.moviedirectory.service.ApiService
import com.hamcuks.moviedirectory.service.MovieDirectoriApi
import kotlinx.coroutines.launch
import java.lang.Exception

class MovieViewModel : ViewModel() {
    private val api: ApiService = MovieDirectoriApi.retrofitService
    var movieList: List<ResultMovie> by mutableStateOf(listOf())
    var searchMovie: List<ResultMovie> by mutableStateOf(listOf())
    var detailMovie: ResultMovie? = null

    init {
        fetchMovieList()
    }

    private fun fetchMovieList() {
        viewModelScope.launch {
            try {
                movieList = api.getMovies().results
            } catch (e: Exception) {
                Log.d("DBG", e.message.toString())
            }
        }
    }

    fun fetchMovieById(id: Int) {
        viewModelScope.launch {
            try {
                detailMovie = movieList.first { it.id == id }
                Log.d("DBG VM", detailMovie.toString())
            } catch (e: Exception) {
                Log.d("DBG", e.message.toString())
            }
        }
    }
}