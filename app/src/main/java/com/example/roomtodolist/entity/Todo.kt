package com.example.roomtodolist.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todo_lists")
data class Todo(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "text") val text: String
) {

    fun withNewText(newText: String): Todo = Todo(id, newText)

}