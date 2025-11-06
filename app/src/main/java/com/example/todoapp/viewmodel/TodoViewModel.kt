package com.example.todoapp.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todoapp.data.repository.TodoRepository
import com.example.todoapp.model.TodoItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodoViewModel @Inject constructor(
    private val repository: TodoRepository
) : ViewModel() {
    val todos: StateFlow<List<TodoItem>> = repository.getAllTodos().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = emptyList()
    )
    fun addTodo(title: String) {
        viewModelScope.launch {
            repository.insert(TodoItem(title = title, isDone = false))
        }
    }

    fun toggleDone(id: Long) {
        val item = todos.value.find { it.id == id } ?: return
        viewModelScope.launch {
            repository.update(item.copy(isDone = !item.isDone))
        }
    }

    fun remove(id: Long) {
        val item = todos.value.find { it.id == id } ?: return
        viewModelScope.launch {
            repository.delete(item)
        }
    }

    fun updateTodoItem(item: TodoItem) {
        viewModelScope.launch {
            repository.update(item)
        }
    }
}
