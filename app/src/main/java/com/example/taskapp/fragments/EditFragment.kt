package com.example.taskapp.fragments

import android.app.AlertDialog
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
import androidx.navigation.fragment.navArgs
import com.example.taskapp.MainActivity
import com.example.taskapp.R
import com.example.taskapp.databinding.FragmentEditBinding
import com.example.taskapp.model.Task
import com.example.taskapp.viewmodel.TaskViewModel

class EditFragment : Fragment(R.layout.fragment_edit) ,MenuProvider{
    private var editBinding:FragmentEditBinding? = null
    private val binding get() = editBinding!!

    private lateinit var taskViewModel: TaskViewModel
    private lateinit var currentTask: Task

    private val args:EditFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        editBinding = FragmentEditBinding.inflate(inflater,container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(this,viewLifecycleOwner, Lifecycle.State.RESUMED)

        taskViewModel = (activity as MainActivity).taskViewModel
        currentTask = args.task!!

        binding.editNoteTitle.setText(currentTask.taskTitle)
        binding.editNoteDesc.setText(currentTask.taskDesc)

        binding.editNoteFab.setOnClickListener{
            val taskTitle = binding.editNoteTitle.text.toString().trim()
            val taskDesc = binding.editNoteDesc.text.toString().trim()

            if (taskTitle.isNotEmpty()){
                val task = Task(currentTask.id,taskTitle,taskDesc)
                taskViewModel.updateTask(task)
                view.findNavController().popBackStack(R.id.homeFragment,false)
            }else{
                Toast.makeText(context,"Please Enter The Task Title",Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun deleteTask(){
        AlertDialog.Builder(activity).apply {
            setTitle("Delete Task Details..!")
            setMessage("You Want to delete Task Details..?")
            setPositiveButton("Delete"){_,_ ->
                taskViewModel.deleteTask(currentTask)
                Toast.makeText(context,"Task Deleted",Toast.LENGTH_SHORT).show()
                view?.findNavController()?.popBackStack(R.id.homeFragment,false)
            }
            setNegativeButton("Cancel",null)
        }.create().show()
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menu.clear()
        menuInflater.inflate(R.menu.menu_edit,menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        return when(menuItem.itemId){
            R.id.deleteMenu->{
                deleteTask()
                true
            }else-> false
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        editBinding = null
    }
}