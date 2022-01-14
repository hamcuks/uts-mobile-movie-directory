package com.hamcuks.moviedirectory.pages

import android.util.Log
import androidx.activity.ComponentActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Info
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.hamcuks.moviedirectory.R
import com.hamcuks.moviedirectory.model.ResultMovie
import com.hamcuks.moviedirectory.ui.theme.KWhite
import com.hamcuks.moviedirectory.ui.theme.kGrey
import com.hamcuks.moviedirectory.utils.ProgressIndicator
import com.hamcuks.moviedirectory.viewmodel.FavouriteViewModel
import com.hamcuks.moviedirectory.viewmodel.MovieViewModel

@Composable
fun HomePage(activity: ComponentActivity, movieVM: MovieViewModel, favVM: FavouriteViewModel, navController: NavController) {

    var textValue by remember { mutableStateOf("")}
    var data :List<ResultMovie>

    if(textValue.isNotEmpty()) {
        data = movieVM.movieList.filter { e -> e.title.lowercase().contains(textValue.lowercase()) || e.overview.lowercase().contains(textValue.lowercase()) }
    } else {
        data = movieVM.movieList
    }


    Scaffold(
        floatingActionButton = {
            Row {
                FloatingActionButton(
                    backgroundColor = Color.Black,
                    contentColor = KWhite,
                    onClick = { navController.navigate("aboutPage") }
                ) {
                    Icon(Icons.Outlined.Info, contentDescription = "")
                }
                Spacer(Modifier.width(16.dp))
                FloatingActionButton(
                    backgroundColor = Color.Black,
                    contentColor = KWhite,
                    onClick = { navController.navigate("favouritePage") }
                ) {
                    Icon(Icons.Outlined.Favorite, contentDescription = "Favourite Page")
                }
            }
        },
        content = {
            Column (modifier = Modifier
                .padding(start = 24.dp, end = 24.dp, top = 24.dp)
            ) {
                Text(stringResource(R.string.app_name), fontSize = 18.sp, fontWeight = FontWeight.Medium)
                Spacer(Modifier.height(24.dp))
                TextField(
                    value = textValue,
                    onValueChange = {
                        textValue = it
                        Log.d("DEBUG", textValue)
                    },
                    label = {Text("Cari Disini...")},
                    textStyle = TextStyle(color = Color.Black),
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = kGrey,
                        focusedLabelColor = Color.Black,
                    ),
                    maxLines = 1,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(Modifier.height(24.dp))
                if (data.isNotEmpty()) {
                    LazyColumn {
                        itemsIndexed(items = data) { _, item -> MovieCard(activity, data = item, favVM = favVM, navController = navController, movieVM = movieVM)}
                    }
                } else {
                    if(textValue.isNotEmpty()) {
                        Column(
                            Modifier.fillMaxWidth()
                                .fillMaxHeight(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text("Tidak Ada Data!")
                        }
                    } else {
                        ProgressIndicator()
                    }
                }
            }
        }
    )
}

@Composable
fun MovieCard(activity: ComponentActivity, data: ResultMovie, favVM: FavouriteViewModel, navController: NavController, movieVM: MovieViewModel) {
    var isFavourite: Boolean by remember {mutableStateOf(false)}

    favVM.getFavs().observe(activity) {
        isFavourite = it.any { e -> e.id == data.id }
    }

    Log.d("DEBUG", isFavourite.toString())

    Box (
        modifier = Modifier
            .height(250.dp)
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
                Row(Modifier.fillMaxWidth(),verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween) {
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
                    IconButton(onClick = {
                            if(isFavourite) {
                                favVM.deleteFavourite(data)
                            } else {
                                favVM.addFavourite(data)
                            }
                        }
                    ) {
                        if(isFavourite) {
                            Icon( Icons.Filled.Favorite, tint = Color.Red, contentDescription = "Favourite Page")
                        } else {
                            Icon( Icons.Outlined.FavoriteBorder, contentDescription = "Favourite Page")
                        }
                    }

                }
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = "${data.overview}", fontSize = 12.sp, fontWeight = FontWeight.Normal, overflow = TextOverflow.Ellipsis, maxLines = 2, lineHeight = 18.sp)
            }
        }
    }
}
