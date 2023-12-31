package com.example.tmanager.ui.home

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.tmanager.App
import com.example.tmanager.R
import com.example.tmanager.databinding.FragmentHomeBinding
import com.example.tmanager.model.Task
import com.example.tmanager.ui.home.adapter.TaskAdapter


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val adapter = TaskAdapter(this::onLongClickItem, this::onClick, this::onSuccess)
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
        setData()
        binding.fab.setOnClickListener {
            findNavController().navigate(R.id.taskFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun onClick(task: Task) {
        findNavController().navigate(R.id.taskFragment, bundleOf(TASK_FOR_EDIT to task))
    }

    private fun onLongClickItem(task: Task) {
        showAlertDialog(task)
    }

    private fun showAlertDialog(task: Task) {
        val dialog = AlertDialog.Builder(requireContext())
        dialog.setTitle(task.title.toString())
            .setMessage("Do you want to delete this message?")
            .setCancelable(true)
            .setPositiveButton("Yes") { _, _ ->
                App.db.taskDao().delete(task)
                val data = App.db.taskDao().getAll()
                adapter.addTasks(data)
            }
            .setNegativeButton("No") { _, _ ->

            }
            .show()
    }

    private fun onSuccess(task: Task) {
        App.db.taskDao().update(task)
        setData()

    }


    companion object {
        const val TASK_FOR_EDIT = "task.edit"


    }

    private fun setData() {
        val data = App.db.taskDao().getAll()
        adapter.addTasks(data)
    }
}


