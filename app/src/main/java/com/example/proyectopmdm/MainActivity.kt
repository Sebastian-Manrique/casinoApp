package com.example.proyectopmdm

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.*
import com.example.proyectopmdm.screens.*
import com.example.proyectopmdm.ui.theme.ProyectoPMDMTheme
import com.example.bottombar.AnimatedBottomBar
import com.example.bottombar.components.BottomBarItem
import com.example.bottombar.model.IndicatorStyle
import java.lang.reflect.Modifier

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ProyectoPMDMTheme {
                val navController = rememberNavController()
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route

                // Define tus rutas principales como objetos NavigationItem
                val navigationItems = listOf(
                    NavigationItem("Home", Routes.HOME, Icons.Default.Home),
                    NavigationItem("Roulette", Routes.ROULETTE, Icons.Default.Warning),
                    NavigationItem("Prizes", Routes.PRIZES, Icons.Default.Star),
                    NavigationItem("Dice", Routes.DICE, Icons.Default.Star),
                    NavigationItem("About Me", Routes.ME, Icons.Default.Person)
                )

                Scaffold(
                    bottomBar = {
                        AnimatedBottomBar(
                            selectedItem = navigationItems.indexOfFirst { it.route == currentRoute },
                            itemSize = navigationItems.size,
                            containerColor = Color.Black,
                            indicatorStyle = IndicatorStyle.LINE,
                        ) {
                            navigationItems.forEachIndexed { index, navigationItem ->
                                BottomBarItem(
                                    selected = currentRoute == navigationItem.route,
                                    onClick = {
                                        if (currentRoute != navigationItem.route) {
                                            navController.navigate(navigationItem.route) {
                                                navController.graph.startDestinationRoute?.let { route ->
                                                    popUpTo(route) { saveState = true }
                                                }
                                                launchSingleTop = true
                                                restoreState = true
                                            }
                                        }
                                    },
                                    imageVector = navigationItem.icon,
                                    label = navigationItem.label,
                                    containerColor = Color.Transparent,
                                )
                            }
                        }
                    },
                ) { innerPadding ->
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
}

// Modelo de NavigationItem para la barra inferior
data class NavigationItem(
    val label: String,
    val route: String,
    val icon: ImageVector
)
