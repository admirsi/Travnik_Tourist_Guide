package com.example.travniktourist.activities


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import coil.load

import com.example.travniktourist.databinding.FragmentActivitiesDetailBinding
import android.content.Intent
import android.net.Uri
import android.text.method.ScrollingMovementMethod

class DetailActivitiesFragment : Fragment() {

    private var viewModel: SharedActivitiesModel? = null
    private var _binding: FragmentActivitiesDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentActivitiesDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = activity?.run {
            ViewModelProvider(requireActivity())[SharedActivitiesModel::class.java]
        }

        viewModel?.selectedActivities?.observe(viewLifecycleOwner) { activities ->
            with(activities) {
                binding.activitiesImage.load(imageFile)
                binding.activitiesNameText.text = name
                binding.descriptionText.text = description
                binding.durationText.text = duration
                binding.priceText.text = price
                binding.addressText.text = address
                binding.typeText.text = type
                binding.goToLocationButton.text = location
            }
        }

        val goToLocationButton = binding.goToLocationButton
        goToLocationButton.setOnClickListener {
            val location = viewModel?.selectedActivities?.value?.location
            val uri = Uri.parse ("geo:0,0?q=$location")
            val intent = Intent (Intent.ACTION_VIEW, uri)
            intent.setPackage ("com.google.android.apps.maps")
            startActivity(intent)
        }

        val descriptionText = binding.descriptionText
        descriptionText.movementMethod = ScrollingMovementMethod ()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}