package com.example.proyectopmdm.screens

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
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
import com.example.proyectopmdm.GoogleAuthClient
import com.example.proyectopmdm.R
import com.example.proyectopmdm.Routes
import com.example.proyectopmdm.ui.theme.buttonColorDefalt
import com.example.proyectopmdm.ui.theme.whiteSebas
import kotlinx.coroutines.launch

var backgroundColor by mutableStateOf(whiteSebas)

@Composable
fun LogIn(navController: NavHostController) {
    val context = LocalContext.current
    val googleAuthClient = remember { GoogleAuthClient(context) }
    var isSingIn by rememberSaveable { mutableStateOf(googleAuthClient.isSignedIn()) }
    val focusRequester = remember { FocusRequester() }
    val coroutineScope = rememberCoroutineScope()

    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val onButtonClick = {
        if (username.isEmpty()) {
            showToast(
                context,
                "You cannot leave the user empty."
            )
        } else if (password.length < 8) {
            showToast(
                context,
                "You could not have entered a password of less than 8 characters."
            )
        } else {
            navController.navigate(Routes.HOME)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(backgroundColor),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Spin to Win\nP.M.D.M.\nPrizes, Money, Dice & More",
            textAlign = TextAlign.Center,
            color = if (backgroundColor == whiteSebas) Color.Black else Color.White,
            fontSize = 30.sp
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
                value = username,
                onValueChange = { newText -> username = newText },
                label = { Text("Enter your username") },
                singleLine = true,
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
                keyboardActions = KeyboardActions(
                    onNext = { focusRequester.requestFocus() }
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
                value = password,
                onValueChange = { newText -> password = newText },
                label = { Text("Enter your password") },
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = { onButtonClick() }
                ),
                singleLine = true,
                modifier = Modifier.focusRequester(focusRequester)
            )
        }

        Spacer(modifier = Modifier.height(30.dp))

        Button(
            onClick = onButtonClick,
            colors = ButtonDefaults.buttonColors(containerColor = buttonColorDefalt)
        ) {
            Text(
                text = "Log in",
                color = if (backgroundColor == whiteSebas) Color.Black else Color.White,
                )
        }

        Spacer(modifier = Modifier.height(10.dp))

        Button(
            onClick = {
                if (isSingIn) {
                    coroutineScope.launch {
                        navController.navigate(Routes.HOME)
                    }
                } else {
                    coroutineScope.launch {
                        isSingIn = googleAuthClient.signIn()
                        if (isSingIn) {
                            navController.navigate(Routes.HOME)
                        }
                    }
                }
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color.White),
            border = BorderStroke(1.dp, Color.Black),
            shape = RoundedCornerShape(50)
        ) {
            Text(text = if (isSingIn) "Continue with this account" else "Sign in with Google ")

            Image(
                painter = painterResource(id = R.drawable.googleicon),
                contentDescription = null,
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(8.dp)) // Adjust spacing
        }
        Spacer(modifier = Modifier.height(10.dp))

        Text(text = "Don't have an account?",
            color = if (backgroundColor == whiteSebas) Color.Black else Color.White,
            )

        Spacer(modifier = Modifier.height(10.dp))

        Button(onClick = {
            navController.navigate(Routes.CREATEJS)
        }, colors = ButtonDefaults.buttonColors(containerColor = buttonColorDefalt)) {
            Text(
                text = "Create one",
                color = if (backgroundColor == whiteSebas) Color.Black else Color.White,
                )
        }
    }
}

@Preview
@Composable
fun PreviewLogIn() {
    val navController = rememberNavController()
    LogIn(navController)
}