package com.example.proyectopmdm.screens

import android.content.Context
import android.widget.Toast
import android.os.Handler
import android.os.Looper
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
import androidx.compose.material3.AlertDialogDefaults.containerColor
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.proyectopmdm.GoogleAuthClient
import com.example.proyectopmdm.R
import com.example.proyectopmdm.ui.theme.blackSebas
import com.example.proyectopmdm.ui.theme.buttonColorDefalt
import com.example.proyectopmdm.ui.theme.whiteSebas
import kotlinx.coroutines.launch

@Composable
fun AddMoneyBox(googleAuthClient: GoogleAuthClient) {
    var isExpanded by remember { mutableStateOf(false) } // The box is open or not
    val coroutineScope = rememberCoroutineScope()

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
                    Button(
                        onClick = {
                            coroutineScope.launch {
                                val userId = googleAuthClient.userId
                                if (userId != null) {
                                    googleAuthClient.setUserMoney(userId, 10) { newMoney ->
                                        if (newMoney != null) {
//                                        money = newMoney
                                        }
                                    }
                                }
                            }
                            money += 20
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = buttonColorDefalt)
                    ) {
                        Text("20€")
                    }

                    Button(
                        onClick = {
                            googleAuthClient.userId?.let {
                                googleAuthClient.setUserMoney(it, 60) { newMoney ->
                                    if (newMoney != null) {
//                                    money = newMoney
                                    }
                                }
                            }
                            money += 60
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = buttonColorDefalt)
                    ) {
                        Text("60€")
                    }

                    Button(
                        onClick = {
                            println("User id: ${googleAuthClient.userId}")
                            googleAuthClient.userId?.let {
                                googleAuthClient.setUserMoney(it, 100) { newMoney ->
                                    if (newMoney != null) {
//                                    money = newMoney
                                    }
                                }
                            }
                            money += 100
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = buttonColorDefalt)
                    ) {
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
    var zamazon by remember { mutableStateOf(true) } // The box is open or not
    val context = LocalContext.current

    Box(
        modifier = Modifier
            .height(if (zamazon) 160.dp else 100.dp)
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
            Text(
                text = if (zamazon) "Zamazon Gift Card €10" else "s",
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
                    Button(
                        onClick = {
                            checkMoney(context)
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = buttonColorDefalt)
                    ) {
                        Text(
                            "claim this Zamazon prize",
                            color = Color.Black
                        )
                    }
                }
            }
        }
    }
}

fun checkMoney(context: Context) {
    if (money < 10) {
        showToast(context, "You don't have that money!")
    }else{
        money -= 10
        showToast(context, "Actual money any $money €")
    }
}

@Composable
fun PiePaxBox() {
    var piePax by remember { mutableStateOf(true) } // The box is open or not
    val context = LocalContext.current

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(if (piePax) 160.dp else 100.dp)
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
            Text(
                text = if (piePax) "PiePax Gift Card €10" else "s",
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
                    Button(
                        onClick = {
                           checkMoney(context)
                        }, colors = ButtonDefaults.buttonColors(containerColor = buttonColorDefalt)
                    ) {
                        Text(
                            "claim this piePax prize",
                            color = Color.Black
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun SthymBox() {
    var sthym by remember { mutableStateOf(true) } // The box is open or not
    val context = LocalContext.current

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(if (sthym) 160.dp else 100.dp)
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
            Text(
                text = if (sthym) "Sthym Gift Card €10" else "s",
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

                    Button(
                        onClick = {
                            checkMoney(context)
                        }, colors = ButtonDefaults.buttonColors(containerColor = buttonColorDefalt)

                    ) {
                        Text(
                            "claim this Sthym prize",
                            color = Color.Black
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ColorTheme() {
    var openOrNot by remember { mutableStateOf(false) } // The box is open or not
    var checkedButton by remember { mutableStateOf(false) } // The box is open or not


    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(if (openOrNot) 160.dp else 70.dp)
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
            .clickable { openOrNot = !openOrNot }
            .padding(10.dp), contentAlignment = Alignment.Center
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(if (checkedButton) "White mode" else "Dark mode")
            Spacer(Modifier.width(10.dp))
            Switch(
                checked = checkedButton,
                onCheckedChange = {
                    checkedButton = it
                    backgroundColor = if (checkedButton) blackSebas else whiteSebas
                },
                colors = SwitchDefaults.colors(checkedThumbColor = buttonColorDefalt)
            )
        }
    }
}

private var toast: Toast? = null
private val handler = Handler(Looper.getMainLooper())

fun showToast(context: Context, message: String) {
    toast?.cancel() // Cancel the previous toast if it exists
    toast = Toast.makeText(context, message, Toast.LENGTH_SHORT)
    toast?.show()

    // Cancel the toast after a short delay
    handler.postDelayed({
        toast?.cancel()
    }, 1000) // 1000 milliseconds = 1 second
}