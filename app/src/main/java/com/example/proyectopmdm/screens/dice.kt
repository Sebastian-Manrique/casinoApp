package com.example.proyectopmdm.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun Dice(navController: NavHostController) {
    var resultNumber by remember { mutableIntStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = {
            resultNumber = randomGenerator()
        }) {
            Text("Roll the dices! 🎲")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "The result is $resultNumber")

        Text("Last values:")
    }
}

fun randomGenerator(): Int {
    val number = (0..6).random()
    return number
}
