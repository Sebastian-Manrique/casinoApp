package com.example.proyectopmdm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.getValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.proyectopmdm.ui.theme.ProyectoPMDMTheme
import com.example.proyectopmdm.screens.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ProyectoPMDMTheme {
                val navController = rememberNavController()
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route

                val scaffoldRoutes = listOf(
                    Routes.HOME,
                    Routes.ME,
                    Routes.DICE,
                    Routes.PRIZES,
                    Routes.ROULETTE
                )

                if (currentRoute in scaffoldRoutes) {
                    MainScaffold(navController)
                } else {
                    NavHost(
                        navController = navController,
                        startDestination = Routes.LOGIN
                    ) {
                        composable(Routes.LOGIN) { LogIn(navController) }
                        composable(Routes.HOME) { Home(navController) }
                        composable(Routes.ROULETTE) { Roulette(navController) }
                        composable(Routes.PRIZES) { Prizes(navController) }
                        composable(Routes.DICE) { Dice(navController) }
                        composable(Routes.ME) { AboutMe(navController) }
                        composable(Routes.CREATEJS) { CreateUser(navController) }
                    }
                }
            }
        }
    }
}