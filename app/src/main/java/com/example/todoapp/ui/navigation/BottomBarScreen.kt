package com.example.todoapp.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Info
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomBarScreen(
    val route: String,
    val icon: ImageVector,
    val label: String
) {
    object Todo : BottomBarScreen("todo", Icons.AutoMirrored.Filled.List, "Задачи")
    object About : BottomBarScreen("about", Icons.Default.Info, "О приложении")
}