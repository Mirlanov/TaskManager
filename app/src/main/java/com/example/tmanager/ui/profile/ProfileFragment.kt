package com.example.tmanager.ui.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import com.example.tmanager.data.local.Pref
import com.example.tmanager.databinding.FragmentProfileeeBinding


class ProfileFragment : Fragment() {
    private lateinit var binding: FragmentProfileeeBinding
    private val pref: Pref by lazy {
        Pref(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileeeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.etTitle.addTextChangedListener {
            pref.saveName(binding.etTitle.text.toString())
        }
        binding.etTitle.setText(pref.getName())
    }

}