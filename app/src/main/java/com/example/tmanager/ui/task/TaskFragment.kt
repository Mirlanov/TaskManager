package com.example.tmanager.ui.task

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.tmanager.App
import com.example.tmanager.databinding.FragmentTaskkBinding
import com.example.tmanager.model.Task


class TaskFragment : Fragment() {
    private lateinit var binding: FragmentTaskkBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTaskkBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnSave.setOnClickListener {
            save()
        }
    }

    private fun save() {
        val data = Task(
            title = binding.etTitle.text.toString(),
            description = binding.etTitle.text.toString()
        )
        App.db.taskDao().insert(data)
        findNavController().navigateUp()
    }

    companion object {
        const val REQUEST_RESULT = "request.result"
        const val TASK_KEY = "task.key"
    }
}