package com.example.tmanager.ui.home.adapter

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.tmanager.R
import com.example.tmanager.databinding.ItemTaskBinding
import com.example.tmanager.model.Task

class TaskAdapter(
    private val onLongClickItem: (task: Task) -> Unit,
    private val onClickItem: (task: Task) -> Unit,
    private val onSuccess: (task: Task) -> Unit
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
        fun bind(task: Task) = with(binding) {
            if (position % 2 == 0) {
                itemView.setBackgroundResource(R.color.white)
                title.setTextColor(ContextCompat.getColor(itemView.context, R.color.black))
                desc.setTextColor(ContextCompat.getColor(itemView.context, R.color.black))


            } else {

                itemView.setBackgroundResource(R.color.black)
                title.setTextColor(ContextCompat.getColor(itemView.context, R.color.white))
                desc.setTextColor(ContextCompat.getColor(itemView.context, R.color.white))
            }
            binding.checkbox.isChecked = task.isSuccess
            if (task.isSuccess) {
                title.paintFlags =
                    title.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
                desc.paintFlags =
                    desc.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
            } else {
                title.paintFlags = 0
                desc.paintFlags = 0
            }
            binding.title.text = task.title
            binding.desc.text = task.description
            checkbox.setOnCheckedChangeListener { _, isSuccess ->
                onSuccess(task.copy(isSuccess = isSuccess))
            }
            itemView.setOnLongClickListener {
                onLongClickItem(task)
                true
            }
            itemView.setOnClickListener {
                onClickItem(task)
            }


        }


    }
}
