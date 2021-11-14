package com.hamcuks.moviedirectory.utils

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavController

@Composable
fun BottomBar(navController: NavController) {

    val selectedItem = remember { mutableStateOf("home") }

    BottomAppBar(
        content = {
            BottomNavigation() {
                BottomNavigationItem(
                    icon = {
                        Icon(Icons.Filled.Home , "")
                    },
                    label = { Text(text = "Home") },
                    selected = selectedItem.value == "home",
                    onClick = {
                        selectedItem.value = "home"
                        navController.navigate("homePage")
                    },
                    alwaysShowLabel = false
                )

                BottomNavigationItem(
                    icon = {
                        Icon(Icons.Filled.Info , "")
                    },
                    label = { Text(text = "About") },
                    selected = selectedItem.value == "about",
                    onClick = {
                        selectedItem.value = "about"
                        navController.navigate("aboutPage")
                    },
                    alwaysShowLabel = false
                )
            }
        }
    )
}