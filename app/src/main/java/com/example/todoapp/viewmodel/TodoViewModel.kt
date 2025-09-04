package com.example.todoapp.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.todoapp.model.TodoItem


class TodoViewModel : ViewModel() {
    var todos = mutableStateOf<List<TodoItem>>(emptyList())
        private set
    fun addTodo(title: String) {
        val newId = if (todos.value.isEmpty()) 0 else todos.value.maxOf { it.id } + 1
        val newTodo = TodoItem(id = newId, title = title, isDone = false)

        todos.value = todos.value + newTodo
    }

    fun toggleDone(id: Int) {
        todos.value = todos.value.map { item ->
            if (item.id == id) item.copy(isDone = !item.isDone) else item
        }
    }

    fun remove(id: Int) {
        todos.value = todos.value.filter { it.id != id }
    }
}