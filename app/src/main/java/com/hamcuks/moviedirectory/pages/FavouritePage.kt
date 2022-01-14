package com.hamcuks.moviedirectory.pages

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.KeyboardArrowLeft
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.hamcuks.moviedirectory.R
import com.hamcuks.moviedirectory.model.ResultMovie
import com.hamcuks.moviedirectory.pages.ui.theme.MovieDirectoryTheme
import com.hamcuks.moviedirectory.ui.theme.KWhite
import com.hamcuks.moviedirectory.utils.ProgressIndicator
import com.hamcuks.moviedirectory.viewmodel.FavouriteViewModel
import com.hamcuks.moviedirectory.viewmodel.MovieViewModel

@Composable
fun FavouritePage(activity: ComponentActivity, favVM: FavouriteViewModel, movieVM: MovieViewModel, navController: NavController) {
    var data: List<ResultMovie> by remember {mutableStateOf(listOf())}

    favVM.getFavs().observe(activity) {
        data = it
    }
    Scaffold(
        content = {
            Column (modifier = Modifier
                .padding(start = 24.dp, end = 24.dp, top = 24.dp)
            ) {
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
                    Text(stringResource(R.string.fav_page), fontSize = 18.sp, fontWeight = FontWeight.Medium)
                }
                Spacer(Modifier.height(24.dp))

                if (data.isNotEmpty()) {
                    LazyColumn {
                        itemsIndexed(items = data) { _, item -> MovieCard(activity, data = item, favVM = favVM, navController = navController, movieVM = movieVM) }
                    }
                } else {
                    Column(
                        Modifier.fillMaxWidth()
                        .fillMaxHeight(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text("Tidak Ada Data!")
                    }
                }
            }
        }
    )
}