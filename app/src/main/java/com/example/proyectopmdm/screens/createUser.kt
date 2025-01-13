package com.example.proyectopmdm.screens

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.proyectopmdm.R
import com.example.proyectopmdm.Routes
import com.example.proyectopmdm.ui.theme.buttonColorDefalt
import com.example.proyectopmdm.ui.theme.whiteSebas

@Composable
fun CreateUser(navController: NavController) {
    val usernameFocusRequester = remember { FocusRequester() }
    val emailFocusRequester = remember { FocusRequester() }
    val passwordFocusRequester = remember { FocusRequester() }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var username by remember { mutableStateOf("") }
    val context = LocalContext.current

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
            color = if (backgroundColor == whiteSebas) Color.Black else Color.White,
            textAlign = TextAlign.Center,
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
                keyboardActions = KeyboardActions(onNext = { emailFocusRequester.requestFocus() }),
                modifier = Modifier.focusRequester(usernameFocusRequester)
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            TextField(
                value = email,
                onValueChange = { newText -> email = newText },
                label = { Text("Enter your email") },
                singleLine = true,
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
                keyboardActions = KeyboardActions(onNext = { passwordFocusRequester.requestFocus() }),
                modifier = Modifier.focusRequester(emailFocusRequester)
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
                    keyboardType = KeyboardType.Password, imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(onDone = {
                    if (validData(username, email, password, context)) {
                        navController.navigate(Routes.HOME)
                    } else {
                        //DO nothing
                    }
                }),
                singleLine = true,
                modifier = Modifier.focusRequester(passwordFocusRequester)
            )
        }

        Spacer(modifier = Modifier.height(30.dp))

        Button(
            onClick = {
                if (validData(username, email, password, context)) {
                    navController.navigate(Routes.HOME)
                } else {
                    //DO nothing
                }
            }, colors = ButtonDefaults.buttonColors(containerColor = buttonColorDefalt)
        ) {
            Text(
                text = "Log in",
                color = if (backgroundColor == whiteSebas) Color.Black else Color.White,
            )
        }
    }
}

fun validData(username: String, email: String, password: String, context: Context): Boolean {
    if (username.isEmpty()) {
        showToast(
            context, "You cannot leave the user empty."
        )
        return false
    } else if (!email.contains("@") && email.length < 7) {
        showToast(
            context, "enter a valid email address"
        )
        return false
    } else if (password.length < 8) {
        showToast(
            context, "enter a valid password"
        )
        return false
    } else {
        return true
    }
}
