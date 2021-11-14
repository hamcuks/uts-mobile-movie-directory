package com.hamcuks.moviedirectory.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowLeft
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.hamcuks.moviedirectory.R
import com.hamcuks.moviedirectory.model.ResultMovie
import com.hamcuks.moviedirectory.viewmodel.MovieViewModel

@Composable
fun DetailMoviePage (movieWM: MovieViewModel, movieId: Int, navController: NavController) {
    movieWM.fetchMovieById(id = movieId)
    var data: ResultMovie = movieWM.detailMovie!!

    Column (Modifier.padding(24.dp)) {
        Row (verticalAlignment = Alignment.CenterVertically) {
            Icon(
                Icons.Outlined.KeyboardArrowLeft,
                contentDescription = "Arrow Back",
                Modifier
                    .size(24.dp)
                    .clickable {
                        navController.popBackStack()
                    }
            )
            Spacer(Modifier.width(16.dp))
            Text(stringResource(R.string.detail_page), fontSize = 18.sp, fontWeight = FontWeight.Medium)
        }
        Spacer(Modifier.height(24.dp))
        Box (
            Modifier
                .heightIn(max = 250.dp)
                .height(250.dp)) {
            Card (
                backgroundColor = Color.LightGray,
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .clip(RoundedCornerShape(16.dp))
            ) {
                Image(
                    painter = rememberImagePainter("https://themoviedb.org/t/p/w500${data.backdropPath}"),
                    contentDescription = "${data.title}",
                    contentScale = ContentScale.FillWidth
                )
            }

            Card (

                elevation = 10.dp,
                modifier = Modifier
                    .height(180.dp)
                    .border(width = 5.dp, color = Color.White)
                    .align(Alignment.BottomCenter)
            ) {
                Image(
                    painter = rememberImagePainter("https://themoviedb.org/t/p/w500${data.posterPath}"),
                    contentDescription = "${data.title}",
                    contentScale = ContentScale.FillHeight
                )
            }
        }
        Spacer(Modifier.height(32.dp))
        Column (horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()) {
            Text("${data.title}", fontSize = 24.sp, fontWeight = FontWeight.Medium)
            Spacer(Modifier.height(16.dp))
            Row {
                Row (verticalAlignment = Alignment.CenterVertically) {
                    Image(painter = painterResource(id = R.drawable.ic_star), contentDescription = "Rating Icon")
                    Spacer(Modifier.width(8.dp))
                    Text("${data.voteAverage}", fontWeight = FontWeight.Medium)
                }
                Spacer(Modifier.width(16.dp))
                Row (verticalAlignment = Alignment.CenterVertically){
                    Image(painter = painterResource(id = R.drawable.ic_date), contentDescription = "Rating Icon", Modifier.size(16.dp))
                    Spacer(Modifier.width(8.dp))
                    Text("${data.releaseDate.split("-")[0]}", fontWeight = FontWeight.Medium)
                }
                Spacer(Modifier.width(16.dp))
                Row (verticalAlignment = Alignment.CenterVertically){
                    Image(painter = painterResource(id = R.drawable.ic_eye), contentDescription = "Rating Icon", Modifier.size(16.dp))
                    Spacer(Modifier.width(8.dp))
                    Text("${data.popularity}", fontWeight = FontWeight.Medium)
                }
            }
        }
        Spacer(modifier = Modifier.height(32.dp))
        Text(
            "${data.overview}",
            fontSize = 16.sp,
            lineHeight = 24.sp
        )
    }
}
