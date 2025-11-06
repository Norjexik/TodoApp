package com.example.todoapp.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.todoapp.model.TodoItem
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoDao {
    @Query("SELECT * FROM todos ORDER BY id ASC")
    fun getAllTodos(): Flow<List<TodoItem>>

    @Insert
    suspend fun insert(todo: TodoItem): Long

    @Update
    suspend fun update(todo: TodoItem)

    @Delete
    suspend fun delete(todo: TodoItem)
}