package com.hamcuks.moviedirectory

import android.app.Application
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavType
import androidx.navigation.activity
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.hamcuks.moviedirectory.pages.AboutPage
import com.hamcuks.moviedirectory.pages.DetailMoviePage
import com.hamcuks.moviedirectory.pages.FavouritePage
import com.hamcuks.moviedirectory.pages.HomePage
import com.hamcuks.moviedirectory.ui.theme.MovieDirectoryTheme
import com.hamcuks.moviedirectory.viewmodel.FavouriteVMFactory
import com.hamcuks.moviedirectory.viewmodel.FavouriteViewModel
import com.hamcuks.moviedirectory.viewmodel.MovieViewModel

class MainActivity : ComponentActivity() {
    private val movieViewModel by viewModels<MovieViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val componentActivity = this
        val favouriteViewModel : FavouriteViewModel by viewModels<FavouriteViewModel>{
            FavouriteVMFactory(application = componentActivity.application)
        }

        setContent {
            val navController = rememberNavController()
            MovieDirectoryTheme {
                Surface(
                    Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()) {
                    NavHost(navController = navController, startDestination = "homePage") {
                        composable("homePage") {

                            HomePage(activity = componentActivity, movieVM = movieViewModel, favVM = favouriteViewModel, navController = navController)
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
                        composable("favouritePage") {
                            FavouritePage(navController = navController, activity = componentActivity, favVM = favouriteViewModel, movieVM = movieViewModel)
                        }
                    }

                }
            }
        }
    }
}
