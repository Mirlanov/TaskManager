package com.example.tmanager.ui.home.adapter

import com.example.tmanager.model.Task
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.tmanager.databinding.ItemTaskBinding

class TaskAdapter : Adapter<TaskAdapter.TaskViewHolder>() {
    private val list: ArrayList<Task> = arrayListOf()
    fun addTask(tasks: List<Task>) {
        list.clear()
        list.addAll(tasks)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        return TaskViewHolder(
            ItemTaskBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bind(list.get(position))
    }

    inner class TaskViewHolder(private val binding: ItemTaskBinding) : ViewHolder(binding.root) {
        fun bind(task: Task) {
            binding.title.text = task.title
            binding.desc.text = task.description
        }
    }
}