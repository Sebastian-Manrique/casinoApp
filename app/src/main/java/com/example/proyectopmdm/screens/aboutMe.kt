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
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.example.proyectopmdm.GoogleAuthClient
import com.example.proyectopmdm.R
import com.example.proyectopmdm.Routes
import com.example.proyectopmdm.ui.theme.buttonColorDefalt
import com.example.proyectopmdm.ui.theme.whiteSebas
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch

@Composable
fun AboutMe(navController: NavHostController) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val googleAuthClient = remember { GoogleAuthClient(context) }
    var isSingIn by rememberSaveable { mutableStateOf(googleAuthClient.isSignedIn()) }
    val iniciado = FirebaseAuth.getInstance()
    val userName = iniciado.currentUser?.displayName
    val userId = iniciado.currentUser?.uid
    val userPhotoUrl = iniciado.currentUser?.photoUrl

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
                "User:  $userName\n" +
                        "ID: ${userId!!.takeLast(4)}\n",
                color = if (backgroundColor == whiteSebas) Color.Black else Color.White,
                fontSize = 20.sp,
            )
        }
        Image(
            painter = rememberAsyncImagePainter("$userPhotoUrl"),
            contentDescription = "User Photo",
            modifier = Modifier.size(100.dp)
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

        Spacer(Modifier.height(60.dp))
        val fontBarras = FontFamily(Font(R.font.fuentaparaescribirbarras, FontWeight.Black))
        Text(
            "¿A qué tienes miedo de perder, si en realidad nada de lo que hay en el mundo te pertenece?\n" +
                    "\n~Marco Aurelio",
            fontFamily = fontBarras,
            color = if (backgroundColor == whiteSebas) Color.Black else Color.White,
            fontSize = 20.sp,
            textAlign = TextAlign.Center,
        )
    }
}

@Preview
@Composable
fun AboutMePreview() {
    AboutMe(rememberNavController())
}