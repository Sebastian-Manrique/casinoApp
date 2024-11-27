package com.example.proyectopmdm.screens

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.proyectopmdm.Routes

@Composable
fun barButtons(navController: NavController){
    Button(onClick = {
        navController.navigate(Routes.HOME)
    }) {
        Text(text = "🏠")
    }
    Button(onClick = {
        navController.navigate(Routes.ROULETTE)
    }) {
        Text(text = "🎰")
    }
    Button(onClick = {
        navController.navigate(Routes.DICE)
    }) {
        Text(text = "🎲")
    }
    Button(onClick = {
        navController.navigate(Routes.PRIZES)
    }) {
        Text(text = "🏅")
    }
    Button(onClick = {
        navController.navigate(Routes.ME)
    }) {
        Text(text = "👤")
    }
}