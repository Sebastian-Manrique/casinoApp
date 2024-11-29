package com.example.proyectopmdm.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.ScrollableDefaults
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
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
fun AboutMe(navController: NavHostController) {
    val user = "Sebas"
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .fillMaxWidth()
            .padding(8.dp),
        state = rememberLazyListState(),
        contentPadding = PaddingValues(16.dp),
        reverseLayout = false,
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        flingBehavior = ScrollableDefaults.flingBehavior(),
        userScrollEnabled = true
    ) {
        item {
            Box(
                modifier = Modifier
                    .background(Color.Gray)
                    .fillMaxWidth()
            ) {
                Text(
                    "User: $user\n" +
                            "Second name: \n" +
                            "ID: \n" +
                            "Birth date: \n" +
                            "Payment method: \n"
                )
            }
        }
        item {
            Box(
                modifier = Modifier
                    .background(Color.Gray)
                    .fillMaxWidth()
            ) {
                Text(
                    "Change information"
                )
            }
        }
    }
}