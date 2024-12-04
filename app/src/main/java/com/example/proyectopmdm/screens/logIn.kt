package com.example.proyectopmdm.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.proyectopmdm.R
import com.example.proyectopmdm.Routes

@Composable
fun LogIn(navController: NavHostController) {
    val focusRequester = remember { FocusRequester() } //Enter selects the next TextField

    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val onButtonClick = {
        Log.d("LoginForm", "Username: $username, Password: $password")
        navController.navigate(Routes.HOME)
    }
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

            TextField(
                value = username, // The current text value
                onValueChange = { newText -> username = newText }, // Update text when changed
                label = { Text("Enter your username") }, // Placeholder label
                singleLine = true,
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next), // Indica "siguiente"
                keyboardActions = KeyboardActions(
                    onNext = { focusRequester.requestFocus() } // Pasa el enfoque al siguiente campo
                )
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {

            TextField(
                value = password, // The current text value
                onValueChange = { newText -> password = newText }, // Update text when changed
                label = { Text("Enter your password") }, // Placeholder label
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done // Indica "terminar"
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        onButtonClick()
                    }
                ),
                singleLine = true, // Una sola línea
                modifier = Modifier.focusRequester(focusRequester) // Aplica el FocusRequester
            )
        }

        Spacer(modifier = Modifier.height(30.dp))

        Button(
            onClick = onButtonClick
        ) {
            Text(text = "Log in")
        }


        Spacer(modifier = Modifier.height(10.dp))

        Text(text = "Don't have an account?")

        Spacer(modifier = Modifier.height(10.dp))

        val context = LocalContext.current  // Obtiene el contexto actual
        Button(onClick = {
            navController.navigate(Routes.CREATEJS)
        }) {
            Text(text = "Create one")
        }

    }
}

@Preview
@Composable
fun PreviewHome() {
    val navController = rememberNavController()
    Home(navController)
}