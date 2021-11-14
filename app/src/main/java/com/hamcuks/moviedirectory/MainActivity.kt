package com.hamcuks.moviedirectory

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.hamcuks.moviedirectory.pages.AboutPage
import com.hamcuks.moviedirectory.pages.DetailMoviePage
import com.hamcuks.moviedirectory.pages.HomePage
import com.hamcuks.moviedirectory.ui.theme.MovieDirectoryTheme
import com.hamcuks.moviedirectory.viewmodel.MovieViewModel

class MainActivity : ComponentActivity() {
    private val movieViewModel by viewModels<MovieViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navController = rememberNavController()
            MovieDirectoryTheme {
                Surface(
                    Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()) {
                    NavHost(navController = navController, startDestination = "homePage") {
                        composable("homePage") {
                            HomePage(movieVM = movieViewModel, navController = navController)
                        }
                        composable("detailMovie/{id}", arguments = listOf(navArgument("id") {type = NavType.IntType})) {
                                backStackEntry -> DetailMoviePage(
                                    navController = navController,
                                    movieWM = movieViewModel,
                                    movieId = backStackEntry.arguments?.getInt("id")!!
                                )
                        }
                        composable("aboutPage") {
                            AboutPage(navController = navController)
                        }
                    }

                }
            }
        }
    }
}
