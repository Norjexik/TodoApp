package com.example.todoapp.data

import com.example.todoapp.model.TodoItem
//
//class TodoRepository {
//    private val _todos = mutableListOf<TodoItem>()
//    private var autoId = 0
//
//    val todos: List<TodoItem>
//        get() = _todos
//
//    fun add(title: String) {
//        _todos.add(TodoItem(id = autoId++, title = title))
//    }
//
//    fun toggleDone(id: Int){
//        val item = _todos.find { it.id == id }
//        if (item != null) {
//            _todos.remove(item)
//            _todos.add(item.copy(isDone = !item.isDone))
//        }
//    }
//
//    fun remove(id: Int){
//        _todos.removeIf { it.id == id }
//    }
//}