package com.example.todoapp.ui.navigation

import androidx.compose.animation.AnimatedContentTransitionScope.SlideDirection
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.todoapp.ui.screens.AboutScreen
import com.example.todoapp.ui.screens.TodoScreen
import com.example.todoapp.viewmodel.ThemeViewModel
import com.example.todoapp.viewmodel.TodoViewModel

@Composable
fun AppNavigation(
    navController: NavHostController,
    themeViewModel: ThemeViewModel,
    todoViewModel: TodoViewModel,
) {
    NavHost(
        navController = navController,
        startDestination = BottomBarScreen.Todo.route,
        modifier = Modifier.background(MaterialTheme.colorScheme.background)
    ) {
        composable(
            route = BottomBarScreen.Todo.route,
            enterTransition = {
                slideIntoContainer(SlideDirection.Down, animationSpec = tween(800))
            },
            exitTransition = {
                slideOutOfContainer(SlideDirection.Up, animationSpec = tween(900))
            },

            ) {
            TodoScreen(
                themeViewModel = themeViewModel,
                todoViewModel = todoViewModel,
            )
        }
        composable(
            route = BottomBarScreen.About.route,
            enterTransition = {
                slideIntoContainer(SlideDirection.Up, animationSpec = tween(800))
            },
            exitTransition = {
                slideOutOfContainer(SlideDirection.Down, animationSpec = tween(900))
            },
        ) {
            AboutScreen()
        }
    }
}