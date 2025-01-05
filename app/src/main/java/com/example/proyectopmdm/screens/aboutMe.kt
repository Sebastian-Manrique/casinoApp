package com.example.proyectopmdm.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.example.proyectopmdm.GoogleAuthClient
import com.example.proyectopmdm.R
import com.example.proyectopmdm.Routes
import kotlinx.coroutines.launch

@Composable
fun AboutMe(navController: NavHostController) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val googleAuthClient = remember { GoogleAuthClient(context) }
    var isSingIn by rememberSaveable { mutableStateOf(googleAuthClient.isSignedIn()) }

    var userName by rememberSaveable { mutableStateOf<String?>(null) }
    var userId by rememberSaveable { mutableStateOf<String?>(null) }
    var userPhotoUrl by rememberSaveable { mutableStateOf<String?>(null) }

    LaunchedEffect(Unit) {
        if (isSingIn) {
            coroutineScope.launch {
                googleAuthClient.signIn()
                userName = googleAuthClient.userName
                userId = googleAuthClient.userId
                userPhotoUrl = googleAuthClient.userPhotoUrl
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .fillMaxWidth()
            .padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Box(
            modifier = Modifier
                .background(Color.Gray)
                .fillMaxWidth()
        ) {
            Text(
                "User: $userName\n" +
                        "ID: $userId\n"
            )
        }

        userPhotoUrl?.let {
            Image(
                painter = rememberAsyncImagePainter(it),
                contentDescription = "User Photo",
                modifier = Modifier.size(100.dp)
            )
        }

        Box(
            modifier = Modifier
                .background(Color.Gray)
                .fillMaxWidth()
        ) {
            Text(
                "Change information"
            )
            Image(
                painter = painterResource(id = R.drawable.settings),
                contentDescription = "Settings",
                modifier = Modifier
                    .size(50.dp)
                    .align(Alignment.CenterEnd)
            )
        }

        Button(
            onClick = {
                coroutineScope.launch {
                    if (isSingIn) {
                        googleAuthClient.signOut()
                        isSingIn = false
                    }
                    navController.navigate(Routes.LOGIN)
                }
            }, modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
        ) {
            Text("Log out")
        }
    }
}

@Preview
@Composable
fun AboutMePreview() {
    AboutMe(rememberNavController())
}