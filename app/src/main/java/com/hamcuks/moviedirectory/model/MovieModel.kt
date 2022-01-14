package com.hamcuks.moviedirectory.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class MovieModel(
	@field:SerializedName("page")
	val page: Int,

	@field:SerializedName("total_pages")
	val totalPages: Int,

	@field:SerializedName("results")
	val results: List<ResultMovie>,

	@field:SerializedName("total_results")
	val totalResults: Int
)

@Entity(tableName = "movie")
data class ResultMovie(

	@ColumnInfo(name = "overview")
	@field:SerializedName("overview")
	val overview: String,

	@ColumnInfo(name = "original_language")
	@field:SerializedName("original_language")
	val originalLanguage: String,

	@ColumnInfo(name = "original_title")
	@field:SerializedName("original_title")
	val originalTitle: String,

	@ColumnInfo(name = "video")
	@field:SerializedName("video")
	val video: Boolean,

	@ColumnInfo(name = "title")
	@field:SerializedName("title")
	val title: String,

	@ColumnInfo(name = "poster_path")
	@field:SerializedName("poster_path")
	val posterPath: String,

	@ColumnInfo(name = "backdrop_path")
	@field:SerializedName("backdrop_path")
	val backdropPath: String,

	@ColumnInfo(name = "release_date")
	@field:SerializedName("release_date")
	val releaseDate: String,

	@ColumnInfo(name = "popularity")
	@field:SerializedName("popularity")
	val popularity: Double,

	@ColumnInfo(name = "vote_average")
	@field:SerializedName("vote_average")
	val voteAverage: Double,

	@PrimaryKey(autoGenerate = true)
	@field:SerializedName("id")
	val id: Int,

	@ColumnInfo(name = "adult")
	@field:SerializedName("adult")
	val adult: Boolean,

	@ColumnInfo(name = "vote_count")
	@field:SerializedName("vote_count")
	val voteCount: Int
)
