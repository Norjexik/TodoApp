package com.example.todoapp

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.example.todoapp.ui.navigation.AppNavigation
import com.example.todoapp.ui.navigation.BottomNavigationBar
import com.example.todoapp.ui.theme.TodoAppTheme
import com.example.todoapp.viewmodel.ThemeViewModel
import com.example.todoapp.viewmodel.TodoViewModel

@Composable
fun TodoApp() {
    val todoViewModel: TodoViewModel = hiltViewModel()
    val themeViewModel: ThemeViewModel = hiltViewModel()

    val navController = rememberNavController()
    val isDarkTheme by themeViewModel.isDarkTheme.collectAsState(true)

    TodoAppTheme(darkTheme = isDarkTheme) {
        Scaffold(
            bottomBar = {
                BottomNavigationBar(navController)
            }
        ) { innerPadding ->
            Box(modifier = Modifier.padding(innerPadding)) {
                AppNavigation(
                    navController = navController,
                    themeViewModel = themeViewModel,
                    todoViewModel = todoViewModel,
                )
            }
        }
    }
}
