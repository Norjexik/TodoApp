package com.example.todoapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.todoapp.data.ThemeRepository
import com.example.todoapp.data.TodoRepository
import com.example.todoapp.ui.TodoScreen
import com.example.todoapp.ui.theme.TodoAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val context = this
            val todoRepository = TodoRepository(context)
            val themeRepository = ThemeRepository(context)
                TodoApp(
                    repository = todoRepository,
                    themeRepository = themeRepository
                )
        }
    }
}
