package com.example.todoapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.todoapp.data.ThemeRepository
import com.example.todoapp.data.TodoRepository
import com.example.todoapp.ui.screens.AboutScreen
import com.example.todoapp.ui.screens.TodoScreen

sealed class Screen(val route: String){
    object Todo: Screen("todo")
    object About: Screen("about")
}

@Composable
fun AppNavigation(
    navController: NavHostController,
    repository: TodoRepository,
    themeRepository: ThemeRepository
){
    NavHost(
        navController = navController,
        startDestination = Screen.Todo.route
    ){
        composable(Screen.Todo.route){
            TodoScreen(repository, themeRepository)
        }
        composable(Screen.About.route){
            AboutScreen()
        }
    }
}