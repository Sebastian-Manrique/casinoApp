package com.example.proyectopmdm.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.example.proyectopmdm.GoogleAuthClient
import com.example.proyectopmdm.Routes
import com.example.proyectopmdm.ui.theme.buttonColorDefalt
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
        coroutineScope.launch {
            userName = googleAuthClient.userName
            userId = googleAuthClient.userId
            userPhotoUrl = googleAuthClient.userPhotoUrl
            println(
                "GoogleSignInClient:AboutMe " + "User: $userName, ID: $userId" +
                        ", Photo: $userPhotoUrl"
            )
        }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(backgroundColor)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        Spacer(modifier = Modifier.height(25.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(
                    RoundedCornerShape(
                        topStart = 16.dp,
                        topEnd = 8.dp,
                        bottomStart = 8.dp,
                        bottomEnd = 16.dp
                    )
                )
                .background(Color.Gray)
                .border(
                    2.dp, Color.Black, RoundedCornerShape(
                        topStart = 16.dp,
                        topEnd = 8.dp,
                        bottomStart = 8.dp,
                        bottomEnd = 16.dp
                    )
                )
                .padding(10.dp), contentAlignment = Alignment.Center
        ) {
            Text(
                "User:  Ragz Fly\n" +
                        "ID: smanriquemontiel@gmail.com\n"
            )
        }
        Image(
            painter = rememberAsyncImagePainter("https://lh3.googleusercontent.com/a/ACg8ocJnTPM3hJwOesbfYfLickflwQDmKlt93ukYPDhFTJxwkYyiMNeT=s96-c"),
            contentDescription = "User Photo",
            modifier = Modifier.size(50.dp)
        )

        ColorTheme() //white or black theme color

        Button( //log out button
            onClick = {
                coroutineScope.launch {
                    googleAuthClient.signOut()
                    isSingIn = false
                    navController.navigate(Routes.LOGIN)
                }
            },
            colors = ButtonDefaults.buttonColors(containerColor = buttonColorDefalt),
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
        ) {
            Text(
                "Log out",
                color = Color.Black
            )
        }
    }
}

@Preview
@Composable
fun AboutMePreview() {
    AboutMe(rememberNavController())
}