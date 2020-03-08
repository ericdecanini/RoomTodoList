package com.example.roomtodolist.mvvm

import androidx.lifecycle.LiveData
import com.example.roomtodolist.database.TodoDao
import com.example.roomtodolist.entity.Todo

class TodoRepository(private val todoDao: TodoDao) {

    val allTodos: LiveData<List<Todo>> = todoDao.getAll()

    suspend fun insert(todo: Todo) {
        todoDao.insert(todo)
    }

    suspend fun update(todo: Todo, newText: String) {
        todoDao.update(todo.withNewText(newText))
    }

    suspend fun delete(todo: Todo) {
        todoDao.delete(todo)
    }

}