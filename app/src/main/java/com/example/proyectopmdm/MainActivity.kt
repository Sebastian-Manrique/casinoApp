package com.example.proyectopmdm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.proyectopmdm.ui.theme.ProyectoPMDMTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ProyectoPMDMTheme {

                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = "logIn"
                ) {
                    composable("home") { Home(navController) }
                    composable("logIn") { LogIn(navController) }
                }

                LogIn(navController)
            }
        }
    }
}