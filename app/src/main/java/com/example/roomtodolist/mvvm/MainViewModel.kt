package com.example.roomtodolist.mvvm

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.roomtodolist.database.TodoDatabase
import com.example.roomtodolist.entity.Todo
import kotlinx.coroutines.launch

class MainViewModel(application: Application): AndroidViewModel(application) {

    private val repository: TodoRepository

    val allTodos: LiveData<List<Todo>>

    init {
        val todoDao = TodoDatabase.getDatabase(application).todoDao()
        repository = TodoRepository(todoDao)
        allTodos = repository.allTodos
    }

    fun insert(todo: Todo) = viewModelScope.launch {
        repository.insert(todo)
    }

    val handleEdit: (todo: Todo, newItem: String) -> Unit = {
        todo, newText -> run {
            viewModelScope.launch {
                repository.update(todo, newText)
            }
        }
    }

    val handleDelete: (todo: Todo) -> Unit = {
        todo -> run {
            viewModelScope.launch {
                repository.delete(todo)
            }
        }
    }

}