package com.example.todoapp.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.todoapp.ui.component.TodoItemCard
import com.example.todoapp.viewmodel.TodoViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun TodoScreen(viewModel: TodoViewModel = viewModel())
{
    var title by remember { mutableStateOf("") }
    val todos by viewModel.todos

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Row {
            OutlinedTextField(
                value = title,
                onValueChange = { title = it },
                label = { Text("Новая задача") },
                modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.width(8.dp))

            Button(
                onClick = {
                    if (title.isNotBlank()) {
                        viewModel.addTodo(title)
                        title = ""
                    }
                },
                enabled = title.isNotBlank()
            ) {
                Text("Добавить")
            }
        }
        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            items(
                items = todos,
                key = { todo -> todo.id }
            ) { item ->
                TodoItemCard(
                    item = item,
                    onToggle = { viewModel.toggleDone(it) },
                    onRemove = { viewModel.remove(it) }
                )
            }
        }
    }
}