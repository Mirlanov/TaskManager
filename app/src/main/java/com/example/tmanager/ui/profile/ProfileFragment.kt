package com.example.tmanager.ui.profile

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import com.example.tmanager.data.local.Pref
import com.example.tmanager.databinding.FragmentProfileeeBinding
import com.example.tmanager.utils.loadImage
import com.theartofdev.edmodo.cropper.CropImage
import com.theartofdev.edmodo.cropper.CropImageView



class ProfileFragment : Fragment() {
    private lateinit var binding: FragmentProfileeeBinding

    private val pref: Pref by lazy {
        Pref(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileeeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.etTitle.setText(pref.getName())
        binding.etTitle.addTextChangedListener {
            pref.saveName(binding.etTitle.text.toString())
        }
        if (pref.getAvatar()?.isNotEmpty() == true) {
            pref.getAvatar()?.let { binding.imgProfile.loadImage(it) }
        }
        binding.imgProfile.setOnClickListener {
            pickImageGallery()
        }
    }

    private fun pickImageGallery() {
        CropImage.activity()
            .setAspectRatio(1,1)
            .setRequestedSize(1080,1080)
            .setCropShape(CropImageView.CropShape.OVAL)
            .start(requireContext(),this)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE || resultCode == Activity.RESULT_OK || data != null) {
            val resultUri = CropImage.getActivityResult(data).uri
            pref.saveAvatar(resultUri.toString())
            pref.getAvatar()?.let { binding.imgProfile.loadImage(it) }

        }
    }
}