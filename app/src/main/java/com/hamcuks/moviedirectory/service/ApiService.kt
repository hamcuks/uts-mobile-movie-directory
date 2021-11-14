package com.hamcuks.moviedirectory.service

import com.hamcuks.moviedirectory.model.MovieModel
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiService {
    @GET("/b/618dd6084a56fb3dee0da690")
    suspend fun getMovies(): MovieModel
}

private val retrofit: Retrofit = Retrofit.Builder()
    .baseUrl("https://api.jsonbin.io/")
    .addConverterFactory(GsonConverterFactory.create())
    .build()

object MovieDirectoriApi {
    val retrofitService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}