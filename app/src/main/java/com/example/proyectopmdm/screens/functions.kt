package com.example.proyectopmdm.screens

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.proyectopmdm.Routes
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.*
import com.example.proyectopmdm.screens.*
import com.example.bottombar.AnimatedBottomBar
import com.example.bottombar.components.BottomBarItem
import com.example.bottombar.model.IndicatorStyle


// Modelo de NavigationItem para la barra inferior
data class NavigationItem(
    val label: String,
    val route: String,
    val icon: ImageVector
)

@Composable
fun BarIcons(
    navController: NavController,
    navigationItems: List<NavigationItem>,
    currentRoute: String?
) {
    AnimatedBottomBar(
        selectedItem = navigationItems.indexOfFirst { it.route == currentRoute },
        itemSize = navigationItems.size,
        containerColor = Color.Black,
        indicatorStyle = IndicatorStyle.LINE,
    ) {
        navigationItems.forEach { navigationItem ->
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
}