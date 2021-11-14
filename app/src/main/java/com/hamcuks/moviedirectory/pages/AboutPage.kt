package com.hamcuks.moviedirectory.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.hamcuks.moviedirectory.R
import com.hamcuks.moviedirectory.utils.BottomBar

@Composable
fun AboutPage (navController: NavController) {
    Scaffold(
        bottomBar = { BottomBar(navController = navController)},
        content = {
            Column (Modifier.padding(24.dp)) {
                Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                    Image(
                        painter = rememberImagePainter(R.drawable.ic_app),
                        contentDescription = stringResource(R.string.app_name),
                        Modifier.size(120.dp)
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(stringResource(R.string.app_name), fontSize = 18.sp, fontWeight = FontWeight.Medium)
                }
                Spacer(modifier = Modifier.height(32.dp))
                Column {
                    Text(
                        stringResource(R.string.app_desc),
                        fontSize = 16.sp,
                        lineHeight = 24.sp
                    )
                    Spacer(modifier = Modifier.height(24.dp))
                    Text("${stringResource(R.string.name)}  \t: Ivan Nur Ilham Syah", fontSize = 16.sp)
                    Text("${stringResource(R.string.nim)}   \t\t\t: 19.11.2742", fontSize = 16.sp)
                    Text("${stringResource(R.string.kelas)} \t\t\t: 19 IF 03", fontSize = 16.sp)
                }
            }
        }
    )
}