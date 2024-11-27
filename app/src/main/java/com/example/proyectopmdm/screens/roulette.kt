package com.example.proyectopmdm.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.proyectopmdm.Routes

@Composable
fun Roulette(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Text("Currently working on the roulette")

        Spacer(modifier = Modifier.height(750.dp)) // Empuja todo hacia arriba

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Gray) // Opcional: para destacar el Row
                .align(Alignment.CenterHorizontally) // Alinear dentro del Column
        ) {
            barButtons(navController)
        }
    }
}