package com.example.taskapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.taskapp.databinding.LayoutBinding
import com.example.taskapp.fragments.HomeFragmentDirections
import com.example.taskapp.model.Task

class TaskAdapter :RecyclerView.Adapter<TaskAdapter.TaskViewHolder>(){
    class TaskViewHolder(val taskBinding: LayoutBinding):RecyclerView.ViewHolder(taskBinding.root)

    private val differCallback = object :DiffUtil.ItemCallback<Task>(){
        override fun areItemsTheSame(oldItem: Task, newItem: Task): Boolean {
            return oldItem.id ==newItem.id &&
                    oldItem.taskDesc == newItem.taskDesc &&
                    oldItem.taskTitle == newItem.taskTitle
        }

        override fun areContentsTheSame(oldItem: Task, newItem: Task): Boolean {
            return oldItem == newItem
        }

    }
    val differ = AsyncListDiffer(this,differCallback)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        return TaskViewHolder(
            LayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        )
    }

    override fun getItemCount(): Int {
       return differ.currentList.size
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
       val currentTask = differ.currentList[position]

        holder.taskBinding.noteTitle.text = currentTask.taskTitle
        holder.taskBinding.noteDesc.text = currentTask.taskDesc

        holder.itemView.setOnClickListener{
            val direction = HomeFragmentDirections.actionHomeFragmentToEditFragment(currentTask)
            it.findNavController().navigate(direction)
        }
    }

}