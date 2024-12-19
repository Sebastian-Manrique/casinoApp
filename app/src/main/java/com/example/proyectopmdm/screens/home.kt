package com.example.proyectopmdm.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.proyectopmdm.R


var money by mutableDoubleStateOf(100.00)


@Composable
fun Home(navController: NavHostController) {

    var isExpanded by remember { mutableStateOf(false) } // The box is open or not

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
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    "Main statistics",
                    fontSize = 30.sp
                )
                Text("%.2f€".format(money), fontSize = 90.sp)
            }
        }
        item {
            Box(modifier = Modifier
                .fillMaxWidth()
                .border(
                    width = 2.dp, color = Color.Black, shape = CircleShape
                )
                .clickable { isExpanded = !isExpanded } // Alterna la expansión al hacer clic
                .padding(16.dp), contentAlignment = Alignment.Center) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = if (isExpanded) "Show less" else "+ Add money",
                        style = MaterialTheme.typography.labelMedium
                    )
                    if (isExpanded) {
                        LazyColumn(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 16.dp)
                        ) {
                            items(10) { index ->
                                Text("Item $index")
                            }
                        }

                    }
                }
            }
        }
        item {
            Spacer(modifier = Modifier.height(16.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Gray)
                    .border(4.dp, Color.Gray), contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        "Last transaction\n" +
                                "Usuario: John Doe\n" +
                                "Lugar: Ruleta\n" +
                                "Introducido: 20€\n" +
                                "Ganancia: +20€"
                    )
                }
            }
        }
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Gray)
                    .border(4.dp, Color.Gray), contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text("Millions of people retire before they get it.")
                    Image(
                        painter = painterResource(id = R.drawable.keepgambling),
                        contentDescription = "Never surrender, you're too close to win.",
                        modifier = Modifier
                            .size(300.dp)
                            .align(alignment = Alignment.CenterHorizontally)
                    )
                }
            }
        }
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Gray)
                    .border(4.dp, Color.Gray), contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text("Meet our clients.")
                    Image(
                        painter = painterResource(id = R.drawable.ourclients),
                        contentDescription = "Never surrender, you're too close to win.",
                        modifier = Modifier
                            .size(300.dp)
                            .align(alignment = Alignment.CenterHorizontally)
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun previewHome() {
    Home(navController = rememberNavController())
}