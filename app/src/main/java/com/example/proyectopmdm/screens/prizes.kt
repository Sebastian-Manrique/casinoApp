package com.example.proyectopmdm.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.proyectopmdm.R
import com.example.proyectopmdm.Routes

@Composable
fun Prizes(navController: NavHostController) {

    var zamazon by remember { mutableStateOf(false) } // The box is open or not
    var piePax by remember { mutableStateOf(false) } // The box is open or not
    var sthym by remember { mutableStateOf(false) } // The box is open or not

    Column(
        modifier = Modifier
            .fillMaxSize()
            .fillMaxWidth()
    ) {
        Box(
            modifier = Modifier.clickable { zamazon = !zamazon }
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = if (zamazon) "Show less" else "Zamazon Gift Card €10",
                    style = MaterialTheme.typography.labelMedium
                )
                if (zamazon) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.zamazon),
                            contentDescription = "Zamazon",
                            modifier = Modifier
                                .size(100.dp)
                                .padding(8.dp)
                        )
                        Button(onClick = { money -= 10 }) {
                            Text("claim this Zamazon prize")
                        }
                    }
                }
            }
        }

        Box(
            modifier = Modifier.clickable { piePax = !piePax }
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = if (piePax) "Show less" else "PiePax Gift Card €10",
                    style = MaterialTheme.typography.labelMedium
                )
                if (piePax) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.piepax),
                            contentDescription = "PiePax",
                            modifier = Modifier
                                .size(100.dp)
                                .padding(8.dp)
                        )
                        Button(onClick = { money -= 10 }) {
                            Text("claim this piePax prize")
                        }
                    }
                }
            }
        }

        Box(
            modifier = Modifier.clickable { sthym = !sthym }
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = if (sthym) "Show less" else "Sthym Gift Card €10",
                    style = MaterialTheme.typography.labelMedium
                )
                if (sthym) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.sthymbg),
                            contentDescription = "PiePax",
                            modifier = Modifier
                                .size(100.dp)
                                .padding(8.dp)
                        )
                        Button(onClick = { money -= 10 }) {
                            Text("claim this Sthym prize")
                        }
                    }
                }
            }
        }
    }
}