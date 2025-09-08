package com.example.todoapp.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todoapp.data.TodoRepository
import com.example.todoapp.model.TodoItem
import kotlinx.coroutines.launch


class TodoViewModel(
    private val repository: TodoRepository
) : ViewModel() {
    var todos = mutableStateOf<List<TodoItem>>(emptyList())
        private set

    init {
        viewModelScope.launch {
            repository.todos.collect { loadedTodos ->
                todos.value = loadedTodos
            }
        }
    }
    fun addTodo(title: String) {
        val newId = if (todos.value.isEmpty()) 0 else todos.value.maxOf { it.id } + 1
        val newTodo = TodoItem(id = newId, title = title, isDone = false)
        val updateList = todos.value + newTodo
        todos.value = updateList
        viewModelScope.launch {
            repository.saveTodos(updateList)
        }
    }

    fun toggleDone(id: Int) {
        val updateList = todos.value.map { item ->
            if (item.id == id) item.copy(isDone = !item.isDone) else item
        }
        todos.value = updateList
        viewModelScope.launch {
            repository.saveTodos(updateList)
        }
    }

    fun remove(id: Int) {
        val updateList = todos.value.filter { it.id != id }
        todos.value = updateList
        viewModelScope.launch {
            repository.saveTodos(updateList)
        }
    }
}