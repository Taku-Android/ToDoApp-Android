package com.example.android.todoapp_android.ui.home.lists

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.android.todoapp_android.database.Task
import com.example.android.todoapp_android.databinding.ItemTaskBinding

class TasksAdapter(var taskList: List<Task>?) : RecyclerView.Adapter<TasksAdapter.TaskViewHolder>() {

    class TaskViewHolder(val binding: ItemTaskBinding) : RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TasksAdapter.TaskViewHolder {

        val bind = ItemTaskBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return TasksAdapter.TaskViewHolder(bind)

    }

    override fun getItemCount(): Int {
        return taskList!!.size

    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val pos = taskList!!.get(position)

        with(holder){
            with(binding){
                title.text = pos.title
                desc.text = pos.description

            }
        }
    }



    fun changeData(newListOfTasks:List<Task>?){
        taskList = newListOfTasks
        notifyDataSetChanged()
    }

}