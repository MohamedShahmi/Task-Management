package com.example.taskapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.taskapp.database.TaskDatabase
import com.example.taskapp.repository.TaskRepository
import com.example.taskapp.viewmodel.TaskViewModel
import com.example.taskapp.viewmodel.ViewModelFactory

class MainActivity : AppCompatActivity() {
    lateinit var taskViewModel: TaskViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupViewModel()
    }

    private fun setupViewModel(){
        val taskRepository = TaskRepository(TaskDatabase(this))
        val viewModelProviderFactory = ViewModelFactory(application,taskRepository)
        taskViewModel = ViewModelProvider(this,viewModelProviderFactory)[TaskViewModel::class.java]
    }
}