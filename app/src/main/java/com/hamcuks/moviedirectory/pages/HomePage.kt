package com.hamcuks.moviedirectory.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.hamcuks.moviedirectory.model.ResultMovie
import com.hamcuks.moviedirectory.utils.BottomBar
import com.hamcuks.moviedirectory.utils.ProgressIndicator
import com.hamcuks.moviedirectory.viewmodel.MovieViewModel

@Composable
fun HomePage(movieVM: MovieViewModel, navController: NavController) {
    Scaffold(
        bottomBar = { BottomBar(navController = navController) },
        content = {
            Column (modifier = Modifier
                .padding(24.dp)
            ) {
                Text("Movie Directory", fontSize = 18.sp, fontWeight = FontWeight.Medium)
                Spacer(Modifier.height(24.dp))
                MovieList(movieList = movieVM.movieList, navController = navController, movieVM = movieVM)
            }
        }
    )
}

@Composable
fun MovieList(movieList: List<ResultMovie>, navController: NavController, movieVM: MovieViewModel) {

    if (movieList.size > 0) {
        LazyColumn {
            itemsIndexed(items = movieList) { _, item -> MovieCard(data = item, navController = navController, movieVM = movieVM)}
        }
    } else {
        ProgressIndicator()
    }

}

@Composable
fun MovieCard(data: ResultMovie, navController: NavController, movieVM: MovieViewModel) {
    Box (
        modifier = Modifier
            .height(200.dp)
            .padding(bottom = 24.dp)
    ) {
        Card (
            backgroundColor = Color.LightGray,
            modifier = Modifier
                .fillMaxWidth(1f)
                .height(148.dp)
                .clip(RoundedCornerShape(16.dp))
        ) {
            Image(
                painter = rememberImagePainter("https://themoviedb.org/t/p/w500/${data.backdropPath}"),
                contentDescription = "${data.title}",
                contentScale = ContentScale.FillWidth
            )
        }
        Card (
            backgroundColor = Color.White,
            elevation = 20.dp,
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .clip(RoundedCornerShape(8.dp))
                .align(Alignment.BottomCenter)
                .clickable {
                    movieVM.fetchMovieById(data.id)
                    navController.navigate("detailMovie/${data.id}")
                }
        ){
            Column (
                modifier = Modifier
                    .padding(16.dp)
            ){
                Text(text = "${data.title}", fontSize = 18.sp, fontWeight = FontWeight.Medium)
                Spacer(modifier = Modifier.height(16.dp))
                Row {
                    Row (verticalAlignment = Alignment.CenterVertically) {
                        Image(painter = painterResource(id = com.hamcuks.moviedirectory.R.drawable.ic_star), contentDescription = "Rating Icon")
                        Spacer(Modifier.width(8.dp))
                        Text("${data.voteAverage}", fontWeight = FontWeight.Medium)
                    }
                    Spacer(Modifier.width(16.dp))
                    Row (verticalAlignment = Alignment.CenterVertically){
                        Image(painter = painterResource(id = com.hamcuks.moviedirectory.R.drawable.ic_date), contentDescription = "Rating Icon", Modifier.size(16.dp))
                        Spacer(Modifier.width(8.dp))
                        Text("${data.releaseDate.split("-")[0]}", fontWeight = FontWeight.Medium)
                    }
                }
            }
        }
    }
}
