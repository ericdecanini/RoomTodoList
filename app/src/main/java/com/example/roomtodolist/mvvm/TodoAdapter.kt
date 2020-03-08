package com.example.roomtodolist.mvvm

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.roomtodolist.R
import com.example.roomtodolist.entity.Todo
import kotlinx.android.synthetic.main.todo_item.view.*

class TodoAdapter(val todoList: List<Todo>,
                  val editOnClick: (Todo) -> Unit,
                  val deleteOnClick: (Todo) -> Unit
): RecyclerView.Adapter<TodoAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(inflater, parent)
    }

    override fun getItemCount(): Int {
        return todoList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val todo = todoList[position]
        holder.itemView.todo.text = todo.text
        holder.setEditOnClick(editOnClick, todoList[position])
        holder.setDeleteOnClick(deleteOnClick, todoList[position])
    }

    class ViewHolder(inflater: LayoutInflater, parent: ViewGroup): RecyclerView.ViewHolder(
        inflater.inflate(R.layout.todo_item, parent, false)
    ) {

        private var todo: TextView = itemView.todo
        private var edit: ImageView = itemView.edit
        private var delete: ImageView = itemView.delete

        fun setEditOnClick(handleEdit: (todo: Todo) -> Unit, todoItem: Todo) {
            edit.setOnClickListener { handleEdit(todoItem) }
        }

        fun setDeleteOnClick(handleDelete: (todo: Todo) -> Unit, todoItem: Todo) {
            delete.setOnClickListener { handleDelete(todoItem) }
        }

    }

}