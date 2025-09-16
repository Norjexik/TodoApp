package com.example.todoapp

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.todoapp.data.ThemeRepository
import com.example.todoapp.data.TodoRepository
import com.example.todoapp.ui.navigation.AppNavigation
import com.example.todoapp.ui.navigation.BottomNavigationBar
import com.example.todoapp.ui.theme.TodoAppTheme

@Composable
fun TodoApp(
    repository: TodoRepository,
    themeRepository: ThemeRepository,
) {
    val isDarkTheme by themeRepository.isDarkTheme.collectAsState(false)
    TodoAppTheme(darkTheme = isDarkTheme) {
        val navController = rememberNavController()

        Scaffold(
            bottomBar = {
                BottomNavigationBar(navController)
            }
        ) { innerPadding ->
            Box(modifier = Modifier.padding(innerPadding)) {
                AppNavigation(
                    navController = navController,
                    repository = repository,
                    themeRepository = themeRepository
                )
            }
        }
    }
}