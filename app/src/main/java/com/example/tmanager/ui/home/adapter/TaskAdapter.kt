package com.example.tmanager.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.tmanager.databinding.ItemTaskBinding
import com.example.tmanager.model.Task

class TaskAdapter(
    val onLongClickItem: (task: Task) -> Unit,
    val onClickItem: (task: Task) -> Unit
) : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    private val list = arrayListOf<Task>()
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

    fun addTasks(data: List<Task>) {

    }


    inner class TaskViewHolder(private val binding: ItemTaskBinding) : ViewHolder(binding.root) {
        fun bind(task: Task) {
            binding.title.text = task.title
            binding.desc.text = task.description
            itemView.setOnLongClickListener {
                onLongClickItem(task)
                true
            }
            itemView.setOnClickListener {
                onClickItem
            }
        }
    }
}