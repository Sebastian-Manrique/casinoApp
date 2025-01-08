package com.example.proyectopmdm.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.ScrollableDefaults
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.proyectopmdm.GoogleAuthClient
import com.example.proyectopmdm.ui.theme.whiteSebas

var money by mutableIntStateOf(100)

@Composable
fun Home(navController: NavHostController) {
    val context = LocalContext.current
    val googleAuthClient = remember { GoogleAuthClient(context) }

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(backgroundColor),
        state = rememberLazyListState(),
        contentPadding = PaddingValues(16.dp),
        reverseLayout = false,
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        flingBehavior = ScrollableDefaults.flingBehavior(),
        userScrollEnabled = true
    ) {
        item {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    "Main statistics",
                    fontSize = 30.sp,
                    color = if (backgroundColor == whiteSebas) Color.Black else Color.White
                )
                Text(
                    text = "$money €",
                    fontSize = 90.sp,
                    color = if (backgroundColor == whiteSebas) Color.Black else Color.White
                )
            }
        }
        item {
            AddMoneyBox(googleAuthClient)
        }
        item {
            GambleImage()
        }
        item {
            OurClients()
        }
    }
}

@Preview
@Composable
fun PreviewHome() {
    Home(navController = rememberNavController())
}