package com.example.proyectopmdm.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import com.example.proyectopmdm.GoogleAuthClient
import com.example.proyectopmdm.R
import com.example.proyectopmdm.ui.theme.whiteSebas

@Composable
fun Prizes(navController: NavHostController) {
    val contextForGoogle = LocalContext.current
    val googleAuthClient = remember { GoogleAuthClient(contextForGoogle) }
    var userId by remember { mutableStateOf<String?>(null) }

    val isSingIn by rememberSaveable { mutableStateOf(googleAuthClient.isSignedIn()) }

    LaunchedEffect(isSingIn) {
        if (isSingIn) {
            googleAuthClient.updateUserInfo()
            userId = googleAuthClient.userId
        }
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(backgroundColor),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(Modifier.height(10.dp))

        val fontRedeem = FontFamily(Font(R.font.comicsans, FontWeight.Black))
        Text(
            "Redeem your codes in the most recognized businesses:",
            color = if (backgroundColor == whiteSebas) Color.Black else Color.White,
            fontSize = 25.sp,
            fontFamily = fontRedeem,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp) // Opcional para agregar márgenes laterales
        )

        Spacer(Modifier.height(20.dp))

        ZamazonBox(googleAuthClient,userId)

        PiePaxBox(googleAuthClient,userId)

        SthymBox(googleAuthClient,userId)
    }
}

@Preview
@Composable
fun PreviewPrizes() {
    val navController = rememberNavController()
    Prizes(navController)
}