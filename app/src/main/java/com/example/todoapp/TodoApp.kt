package com.example.todoapp

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import com.example.todoapp.data.TodoRepository
import com.example.todoapp.ui.TodoScreen
import com.example.todoapp.ui.theme.TodoAppTheme

@Composable
fun TodoApp(repository: TodoRepository) {
    TodoAppTheme {
        Surface(color = MaterialTheme.colorScheme.background){
            TodoScreen(repository = repository)
        }
    }
}