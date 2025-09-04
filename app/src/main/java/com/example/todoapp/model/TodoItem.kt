package com.example.todoapp.model

data class TodoItem (
    val  id: Int = 0,
    val title: String,
    val isDone: Boolean = false
)
/*id - чтобы отличать задачи
  title - текст задачи
  isDone - выполнено или нет
*/

