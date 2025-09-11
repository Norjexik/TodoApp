package com.example.todoapp

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.todoapp.data.ThemeRepository
import com.example.todoapp.data.TodoRepository
import com.example.todoapp.ui.TodoScreen
import com.example.todoapp.ui.theme.TodoAppTheme

@Composable
fun TodoApp(
    repository: TodoRepository,
    themeRepository: ThemeRepository,
) {
    val isDarkTheme by themeRepository.isDarkTheme.collectAsState(false)
    TodoAppTheme(darkTheme = isDarkTheme) {
        Surface(color = MaterialTheme.colorScheme.background){
            TodoScreen(
                repository = repository,
                themeRepository = themeRepository
            )
        }
    }
}