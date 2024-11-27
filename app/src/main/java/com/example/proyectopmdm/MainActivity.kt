package com.example.proyectopmdm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.proyectopmdm.screens.AboutMe
import com.example.proyectopmdm.screens.CreateUser
import com.example.proyectopmdm.screens.Dice
import com.example.proyectopmdm.screens.Home
import com.example.proyectopmdm.screens.LogIn
import com.example.proyectopmdm.screens.Prizes
import com.example.proyectopmdm.screens.Roulette
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
                    startDestination = Routes.LOGIN
                ) {
                    composable(Routes.HOME) { Home(navController) }
                    composable(Routes.LOGIN) { LogIn(navController) }
                    composable(Routes.ROULETTE) { Roulette(navController) }
                    composable(Routes.PRIZES) { Prizes(navController) }
                    composable(Routes.DICE) { Dice(navController) }
                    composable(Routes.ME) { AboutMe(navController) }
                    composable(Routes.CREATEUSER) { CreateUser(navController) }
                }
            }
        }
    }
}