package com.example.roomtodolist.mvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.LinearLayout
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.roomtodolist.R
import com.example.roomtodolist.entity.Todo
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.todo_item.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private val todoList = ArrayList<Todo>()
    lateinit var adapter: TodoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = MainViewModel(application)

        setClickListeners()
        initRecycler()
        getTodos()
    }

    private fun getTodos() {
        viewModel.allTodos.observe(this, Observer { todoList ->
            updateTodos(todoList)
        })
    }

    private fun initRecycler() {
        adapter = TodoAdapter(todoList, showEditDialog, viewModel.handleDelete)
        recycler_main.adapter = adapter
        recycler_main.layoutManager = LinearLayoutManager(this)
    }

    private val showEditDialog: (todo: Todo) -> Unit = {
        todo -> run {
        val dialog = AlertDialog.Builder(this)
            .setTitle("Edit Item")

        val input = EditText(this)
        val lp = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.MATCH_PARENT)
        input.layoutParams = lp

        dialog.setView(input)
        dialog.setPositiveButton("Done") { _, _ ->
            viewModel.handleEdit(todo, input.text.toString())
        }

        dialog.show()
    }

    }

    private fun updateTodos(todoList: List<Todo>) {
        this.todoList.clear()
        this.todoList.addAll(todoList)
        adapter.notifyDataSetChanged()
    }

    private fun setClickListeners() {
        button_insert.setOnClickListener { handleInsertButton() }
    }

    private fun handleInsertButton() {
        val item = edittext_insert.text.toString()
        edittext_insert.text = null
        insertItem(item)
    }

    private fun insertItem(item: String) {
        viewModel.insert(Todo(text = item))
    }

}
