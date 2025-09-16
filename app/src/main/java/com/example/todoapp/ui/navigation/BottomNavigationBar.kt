package com.example.todoapp.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavController

@Composable
fun BottomNavigationBar(navController: NavController) {
    val items = listOf(Screen.Todo, Screen.About)
    var selectedItemIndex by remember { mutableIntStateOf(0) }

    BottomAppBar {
        items.forEachIndexed { index, screen ->
            NavigationBarItem(
                icon = {
                    when (screen) {
                        Screen.Todo ->
                            Icon(Icons.AutoMirrored.Filled.List, contentDescription = "Задачи")
                        Screen.About ->
                            Icon(Icons.Default.Info,contentDescription = "О приложении")
                    }
                },
                label = { Text(screen.route.replaceFirstChar { it.uppercase() })},
                selected = selectedItemIndex == index,
                onClick = {
                    selectedItemIndex = index
                    navController.navigate(screen.route) {
                        popUpTo(navController.graph.startDestinationId)
                        launchSingleTop = true
                    }
                }
            )
        }
    }
}