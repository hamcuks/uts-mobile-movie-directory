package com.hamcuks.moviedirectory.utils

import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hamcuks.moviedirectory.R


@Composable
fun ProgressIndicator() {
    Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center, modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight()) {
        CircularProgressIndicator()
        Spacer(Modifier.height(16.dp))
        Text("${stringResource(R.string.loading_data)}...", fontSize = 18.sp, fontWeight = FontWeight.Medium)
    }
}