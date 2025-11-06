package com.example.todoapp.ui.screens

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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import com.example.todoapp.model.TodoItem
import com.example.todoapp.ui.component.EditTodoModal
import com.example.todoapp.ui.component.SwipeToDelete
import com.example.todoapp.ui.component.TodoItemCard
import com.example.todoapp.viewmodel.ThemeViewModel
import com.example.todoapp.viewmodel.TodoViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoScreen(
    themeViewModel: ThemeViewModel,
    todoViewModel: TodoViewModel,
) {
    val focusManager = LocalFocusManager.current
    var title by remember { mutableStateOf("") }
    val todos by todoViewModel.todos.collectAsState()
    val isDarkTheme by themeViewModel.isDarkTheme.collectAsState()
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    var editingTodo by remember { mutableStateOf<TodoItem?>(null) }
    val scope = rememberCoroutineScope()

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
                        todoViewModel.addTodo(title)
                        title = ""
                        focusManager.clearFocus()
                    }
                },
                enabled = title.isNotBlank()
            ) {
                Text("Добавить")
            }
        }
        Spacer(modifier = Modifier.height(16.dp))

        Row {
            Text(
                text = if (isDarkTheme) "Тёмная тема" else "Светлая тема",
                modifier = Modifier.weight(1f)
            )
            Switch(
                checked = isDarkTheme,
                onCheckedChange = { themeViewModel.setDarkTheme(it) }
            )
        }

        LazyColumn(
            modifier = Modifier.padding(horizontal = 8.dp)
        ) {
            items(todos, key = { it.id }) { item ->
                SwipeToDelete(item = item, onRemove = { todoViewModel.remove(it) }
                ) {
                    TodoItemCard(
                        item = item,
                        onToggle = { todoViewModel.toggleDone(it) },
                        onEdit = {
                            editingTodo = it
                            scope.launch { sheetState.expand() }
                        }
                    )
                }
            }
        }
    }
    editingTodo?.let { todo ->
        EditTodoModal(
            task = todo.title,
            onDismiss = {
                scope.launch {
                    sheetState.hide()
                    editingTodo = null
                }
            },
            onTaskSave = { newText ->
                todoViewModel.updateTodoItem(todo.copy(title = newText))
                scope.launch {
                    sheetState.hide()
                    editingTodo = null
                }
            },
            sheetState = sheetState
        )
    }
}
