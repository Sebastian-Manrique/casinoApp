package com.example.proyectopmdm.screens

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.proyectopmdm.R

@Composable
fun AddMoneyBox() {
    var isExpanded by remember { mutableStateOf(false) } // The box is open or not

    Box(modifier = Modifier
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
        .padding(16.dp)
        .clickable { isExpanded = !isExpanded } // Alterna la expansión al hacer clic
        .padding(16.dp), contentAlignment = Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = if (isExpanded) "Show less" else "+ Add money",
                style = MaterialTheme.typography.labelMedium
            )
            if (isExpanded) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Button(onClick = { money += 20 }) {
                        Text("20€")
                    }

                    Button(onClick = { money += 60 }) {
                        Text("60€")
                    }

                    Button(onClick = { money += 100 }) {
                        Text("100€")
                    }
                }
            }
        }
    }
}

@Composable
fun GambleImage() {
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

@Composable
fun OurClients() {
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

//Prizes boxes

@Composable
fun ZamazonBox() {
    var zamazon by remember { mutableStateOf(false) } // The box is open or not
    val context = LocalContext.current

    Box(
        modifier = Modifier
            .height(if (zamazon) 160.dp else 70.dp)
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
            .clickable { zamazon = !zamazon }
            .padding(10.dp), contentAlignment = Alignment.Center
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
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.zamazon),
                        contentDescription = "Zamazon",
                        modifier = Modifier.size(100.dp)
                    )
                    Button(onClick = {
                        money -= 10
                        Toast.makeText(
                            context,
                            "Actual money now %.2f€".format(money),
                            Toast.LENGTH_SHORT
                        ).show()
                    }) {
                        Text("claim this Zamazon prize")
                    }
                }
            }
        }
    }
}

@Composable
fun PiePaxBox() {
    var piePax by remember { mutableStateOf(false) } // The box is open or not
    val context = LocalContext.current

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(if (piePax) 160.dp else 70.dp)
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
            .clickable { piePax = !piePax }
            .padding(10.dp), contentAlignment = Alignment.Center
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
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.piepax),
                        contentDescription = "PiePax",
                        modifier = Modifier.size(100.dp)
                    )
                    Button(onClick = {
                        money -= 10
                        Toast.makeText(
                            context,
                            "Actual money now %.2f€".format(money),
                            Toast.LENGTH_SHORT
                        ).show()
                    }) {
                        Text("claim this piePax prize")
                    }
                }
            }
        }
    }
}

@Composable
fun SthymBox() {
    var sthym by remember { mutableStateOf(false) } // The box is open or not
    val context = LocalContext.current

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(if (sthym) 160.dp else 70.dp)
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
            .clickable { sthym = !sthym }
            .padding(10.dp), contentAlignment = Alignment.Center
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
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.sthymbg),
                        contentDescription = "PiePax",
                        modifier = Modifier
                            .size(100.dp)
                    )

                    Button(onClick = {
                        money -= 10

                        Toast.makeText(
                            context,
                            "Actual money now %.2f€".format(money),
                            Toast.LENGTH_SHORT
                        ).show()
                    }) {
                        Text("claim this Sthym prize")
                    }
                }
            }
        }
    }
}