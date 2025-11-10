package com.example.todoapp.data.repository

import com.example.todoapp.data.local.dao.TodoDao
import com.example.todoapp.model.TodoItem
import kotlinx.coroutines.flow.Flow

class TodoRepository(
    private val todoDao: TodoDao
) {
    fun getAllTodos(): Flow<List<TodoItem>> = todoDao.getAllTodos()
    suspend fun insert(todo: TodoItem): Long = todoDao.insert(todo)
    suspend fun update(todo: TodoItem) = todoDao.update(todo)
    suspend fun delete(todo: TodoItem) = todoDao.delete(todo)
}