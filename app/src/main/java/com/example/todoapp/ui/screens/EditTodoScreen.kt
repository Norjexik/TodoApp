package com.example.todoapp.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.todoapp.model.TodoItem

@Composable
fun EditTodoScreen(
    todo: TodoItem,
    onSave: (TodoItem) -> Unit,
    navController: NavController
) {
    var title by remember { mutableStateOf(todo.title) }
    val isError = title.isBlank()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Редактировать задачу",
            style = MaterialTheme.typography.headlineMedium
        )
        OutlinedTextField(
            value = title,
            onValueChange = { title = it },
            label = { Text("Название") },
            isError = isError,
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )
        if (isError) {
            Text(
                text = "Название не может быть пустым",
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall
            )
        }
        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            TextButton(
                onClick = { navController.popBackStack() }
            ) {
                Text("Отмена")
            }
        }
        Spacer(modifier = Modifier.width(8.dp))

        Button(
            onClick = {
                if (title.isNotBlank()) {
                    onSave(todo.copy(title = title))
                    navController.popBackStack()
                }
            },
            enabled = title.isNotBlank()
        ) {
            Text("Сохранить")
        }
    }
}

