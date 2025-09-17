package com.example.todoapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.todoapp.data.ThemeRepository
import com.example.todoapp.data.TodoRepository
import com.example.todoapp.ui.screens.AboutScreen
import com.example.todoapp.ui.screens.EditTodoScreen
import com.example.todoapp.ui.screens.TodoScreen
import com.example.todoapp.viewmodel.TodoViewModel

sealed class Screen(val route: String) {
    object Todo : Screen("todo")
    object About : Screen("about")
    data class Edit(val todoId: Int) : Screen("edit/$todoId")
}

@Composable
fun AppNavigation(
    navController: NavHostController,
    todoViewModel: TodoViewModel
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Todo.route
    ) {
        composable(Screen.Todo.route) {
            TodoScreen(
                todoViewModel = todoViewModel,
                navController = navController
            )
        }
        composable(Screen.About.route) {
            AboutScreen()
        }
        composable(
            route = "edit/{todoId}",
            arguments = listOf(
                navArgument("todoId") { type = NavType.IntType })
        ) { backStackEntry ->
            val todoId = backStackEntry.arguments?.getInt("todoId")!!
            val todo = todoViewModel.todos.value.find { it.id == todoId } ?: return@composable
            EditTodoScreen(
                todo = todo,
                onSave = { updatedTodo ->
                    val newList =
                        todoViewModel.todos.value.map { if (it.id == todoId) updatedTodo else it }
                    todoViewModel.updateTodos(newList)
                },
                navController = navController
            )
        }
    }
}