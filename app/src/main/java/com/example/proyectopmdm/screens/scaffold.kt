package com.example.proyectopmdm

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.bottombar.AnimatedBottomBar
import com.example.bottombar.components.BottomBarItem
import com.example.bottombar.model.IndicatorStyle
import com.example.proyectopmdm.screens.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.unit.dp
import com.example.proyectopmdm.ui.theme.buttonColorDefalt

@Composable
fun MainScaffold(navController: NavHostController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    // Mapa que asocia las rutas con los iconos y etiquetas
    val navigationMap = mapOf(
        Routes.HOME to Pair(Icons.Default.Home, "Home"),
        Routes.PRIZES to Pair(Icons.Default.Star, "Prizes"),
        Routes.DICE to Pair(Icons.Default.Email, "Dice"),
        Routes.ME to Pair(Icons.Default.Person, "About Me")
    )

    Scaffold(
        bottomBar = {
            AnimatedBottomBar(
                selectedItem = navigationMap.keys.indexOf(currentRoute),
                itemSize = navigationMap.size,
                containerColor = backgroundColor,
                indicatorStyle = IndicatorStyle.LINE,
                indicatorColor = buttonColorDefalt,
                contentColor = buttonColorDefalt,
            ) {
                navigationMap.forEach { (route, iconAndLabel) ->
                    BottomBarItem(
                        selected = currentRoute == route,
                        onClick = {
                            if (currentRoute != route) {
                                navController.navigate(route) {
                                    navController.graph.startDestinationRoute?.let { startRoute ->
                                        popUpTo(startRoute) { saveState = true }
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                        },
                        imageVector = iconAndLabel.first,
                        label = iconAndLabel.second,
                        containerColor = Color.Transparent
                    )
                }
            }
        },
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Routes.HOME,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Routes.HOME) { Home(navController) }
            composable(Routes.PRIZES) { Prizes(navController) }
            composable(Routes.DICE) { Dice(navController) }
            composable(Routes.ME) { AboutMe(navController) }
            composable(Routes.CREATEJS) { CreateUser(navController) }
            composable(Routes.LOGIN) { LogIn(navController) }
        }
    }
}