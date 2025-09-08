package com.example.todoapp.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.core.stringSetPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.todoapp.model.TodoItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlin.collections.forEach


val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "todo_setting")

class TodoRepository(context: Context) {
    private val dataStore = context.dataStore

    private val KEY_TASKS = stringSetPreferencesKey("tasks")
    private val KEY_TASK_PREFIX_DONE = "task_done_"
    private val KEY_TASK_PREFIX_TITLE = "task_title_"

    val todos: Flow<List<TodoItem>> = dataStore.data.map { preferences ->
        val taskIds = (preferences[KEY_TASKS] ?: emptySet()).toList()
        taskIds.mapNotNull { idStr ->
            val id = idStr.toIntOrNull() ?: return@mapNotNull null
            val title = preferences[stringPreferencesKey("$KEY_TASK_PREFIX_TITLE$id")]
                ?: return@mapNotNull null
            val isDone = preferences[booleanPreferencesKey("$KEY_TASK_PREFIX_DONE$id")] ?: false
            TodoItem(id = id, title = title, isDone = isDone)
        }
    }

    suspend fun saveTodos(todos: List<TodoItem>) {
        dataStore.edit { preferences ->
            // 1. Сохраняем список ID задач
            val taskIds = todos.map { it.id.toString() }.toSet()
            preferences[KEY_TASKS] = taskIds

            // 2. Сохраняем каждую задачу по полям
            todos.forEach { task ->
                // Сохраняем title
                preferences[stringPreferencesKey("$KEY_TASK_PREFIX_TITLE${task.id}")] = task.title
                // Сохраняем isDone
                preferences[booleanPreferencesKey("$KEY_TASK_PREFIX_DONE${task.id}")] = task.isDone
                // ID не нужно сохранять отдельно — он в task.id и в KEY_TASKS
            }

            // 3. Удаляем старые данные (очистка)
            val allKeys = preferences.asMap().keys
            allKeys.forEach { key ->
                val keyName = key.name  // получаем имя ключа как строку
                if (keyName.startsWith(KEY_TASK_PREFIX_TITLE) ||
                    keyName.startsWith(KEY_TASK_PREFIX_DONE)
                ) {
                    // Извлекаем id из имени ключа
                    val idPart = keyName
                        .removePrefix(KEY_TASK_PREFIX_TITLE)
                        .removePrefix(KEY_TASK_PREFIX_DONE)
                    val id = idPart.toIntOrNull()
                    if (id != null && id !in todos.map { it.id }) {
                        // Удаляем устаревший ключ
                        preferences -= key
                    }
                }
            }
        }
    }
}
