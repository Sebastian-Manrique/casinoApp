package com.example.proyectopmdm

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun LogIn(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Text(
            text = "Spin to Win\nP.M.D.M.\nPrizes, Money, Dice & More",
            textAlign = TextAlign.Center, fontSize = 30.sp
        )

        Spacer(modifier = Modifier.height(30.dp))

        Image(
            painter = painterResource(id = R.drawable.spinandwin),
            contentDescription = "A suitcase full of money with a roulette wheel in it.",
            modifier = Modifier.size(200.dp)
        )

        Spacer(modifier = Modifier.height(30.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            var text by remember { mutableStateOf("") } // Holds the input text
            TextField(
                value = text, // The current text value
                onValueChange = { newText -> text = newText }, // Update text when changed
                label = { Text("Enter your username") } // Placeholder label
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            var text2 by remember { mutableStateOf("") } // Holds the input text
            TextField(
                value = text2, // The current text value
                onValueChange = { newText -> text2 = newText }, // Update text when changed
                label = { Text("Enter your password") } // Placeholder label
            )
        }

        Spacer(modifier = Modifier.height(30.dp))

        Button(onClick = {
            navController.navigate("home") {
                popUpTo("logIn") { inclusive = true } // Borra la pantalla "logIn" de la pila
            }
        }) {
            Text(text = "Log in")
        }

        Spacer(modifier = Modifier.height(10.dp))

        Text(text = "Don't have an account?")

        Spacer(modifier = Modifier.height(10.dp))

        val context = LocalContext.current  // Obtiene el contexto actual
        Button(onClick = {
            Toast.makeText(context, "¡Hola desde el Toast!", Toast.LENGTH_SHORT).show()
        }) {
            Text(text = "Create one")
        }

    }
}

@Composable
fun Home(navController: NavHostController) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        item { Text("test  proof") }
        item { Text("test  proof") }
        item { Text("test  proof") }
        item { Text("test  proof") }
        item { Text("test  proof") }
        item { Text("test  proof") }
        item { Text("test  proof") }
        item { Text("test  proof") }
        item { Text("test  proof") }
        item { Text("test  proof") }
        item { Text("test  proof") }
    }
}

@Preview
@Composable
fun PreviewHome() {
    val navController = rememberNavController()
    Home(navController)
}

@Composable
fun PreviewLogIn() {
    val navController = rememberNavController()
    LogIn(navController)
}