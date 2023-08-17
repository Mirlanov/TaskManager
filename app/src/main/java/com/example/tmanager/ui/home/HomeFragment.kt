package com.example.tmanager.ui.home

import com.example.tmanager.model.Task
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.fragment.findNavController
import com.example.tmanager.App
import com.example.tmanager.R
import com.example.tmanager.databinding.FragmentHomeBinding
import com.example.tmanager.ui.home.adapter.TaskAdapter
import com.example.tmanager.ui.task.TaskFragment


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val adapter = TaskAdapter()

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        _binding = FragmentHomeBinding.inflate(inflater, container, false)


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerView.adapter = adapter
        val data = App.db.taskDao().getAll()
        adapter.addTask(data)
        binding.fab.setOnClickListener {
            findNavController().navigate(R.id.taskFragment)
        }

    }
}


