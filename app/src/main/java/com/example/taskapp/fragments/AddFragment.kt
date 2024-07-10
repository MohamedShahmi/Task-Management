package com.example.taskapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.lifecycle.Lifecycle
import androidx.navigation.findNavController
import com.example.taskapp.MainActivity
import com.example.taskapp.R
import com.example.taskapp.databinding.FragmentAddBinding
import com.example.taskapp.model.Task
import com.example.taskapp.viewmodel.TaskViewModel

class AddFragment : Fragment(R.layout.fragment_add),MenuProvider {


    private var addTaskBinding: FragmentAddBinding?=null
    private val binding get() = addTaskBinding!!

    private lateinit var taskViewModel: TaskViewModel
    private lateinit var addTask: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        addTaskBinding = FragmentAddBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(this,viewLifecycleOwner, Lifecycle.State.RESUMED)

        taskViewModel = (activity as MainActivity).taskViewModel
        addTask = view
    }

    private fun saveTask(view: View){
        val taskTitle = binding.addNoteTitle.text.toString().trim()
        val taskDesc = binding.addNoteDesc.text.toString().trim()

        if (taskTitle.isNotEmpty()){
            val task = Task(0,taskTitle,taskDesc)
            taskViewModel.addTask(task)

            Toast.makeText(addTask.context,"Task Saved",Toast.LENGTH_SHORT).show()
            view.findNavController().popBackStack(R.id.homeFragment,false)
        }else{
            Toast.makeText(addTask.context,"Please Enter The Task Title",Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menu.clear()
        menuInflater.inflate(R.menu.menu_add,menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
       return when(menuItem.itemId){
           R.id.saveMenu ->{
               saveTask(addTask)
               true
           }
           else -> false
       }
    }

    override fun onDestroy() {
        super.onDestroy()
        addTaskBinding = null
    }
}