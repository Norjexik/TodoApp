package com.example.todoapp

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.todoapp.data.ThemeRepository
import com.example.todoapp.data.TodoRepository
import com.example.todoapp.ui.navigation.AppNavigation
import com.example.todoapp.ui.navigation.BottomNavigationBar
import com.example.todoapp.ui.theme.TodoAppTheme
import com.example.todoapp.viewmodel.TodoViewModel

@Composable
fun TodoApp(
    repository: TodoRepository,
    themeRepository: ThemeRepository,
) {
    val isDarkTheme by themeRepository.isDarkTheme.collectAsState(false)
    TodoAppTheme(darkTheme = isDarkTheme) {
        val navController = rememberNavController()
        val todoViewModel: TodoViewModel = viewModel(factory = object : ViewModelProvider.Factory{
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return TodoViewModel(
                    repository = repository,
                    themeRepository = themeRepository
                ) as T
            }
        })
        Scaffold(
            bottomBar = {
                BottomNavigationBar(navController)
            }
        ) { innerPadding ->
            Box(modifier = Modifier.padding(innerPadding)) {
                AppNavigation(
                    navController = navController,
                    todoViewModel = todoViewModel
                )
            }
        }
    }
}