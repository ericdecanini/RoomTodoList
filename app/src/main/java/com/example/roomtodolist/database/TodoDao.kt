package com.example.roomtodolist.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.roomtodolist.entity.Todo

@Dao
interface TodoDao {

    @Query("SELECT * FROM todo_lists")
    fun getAll(): LiveData<List<Todo>>

    @Query("SELECT * FROM todo_lists WHERE id=:id")
    fun loadSingleById(id: Int): LiveData<Todo>

    @Insert
    suspend fun insert(todo: Todo)

    @Update
    suspend fun update(todo: Todo)

    @Delete
    suspend fun delete(todo: Todo)

}