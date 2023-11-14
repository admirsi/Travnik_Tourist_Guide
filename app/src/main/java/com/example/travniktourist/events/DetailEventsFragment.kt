package com.example.travniktourist.events


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import coil.load
import com.example.travniktourist.databinding.FragmentEventsDetailBinding
import android.content.Intent
import android.net.Uri
import android.text.method.ScrollingMovementMethod


class DetailEventsFragment : Fragment() {

    private var viewModel: SharedEventsModel? = null
    private var _binding: FragmentEventsDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEventsDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = activity?.run {
            ViewModelProvider(requireActivity())[SharedEventsModel::class.java]
        }

        viewModel?.selectedEvents?.observe(viewLifecycleOwner) { events ->
            with(events) {
                binding.eventsImage.load(imageFile)
                binding.eventsNameText.text = name
                binding.descriptionText.text = description
                binding.timeText.text = time
                binding.priceText.text = price
                binding.goToLocationButton.text = location
            }
        }

        val goToLocationButton = binding.goToLocationButton
        goToLocationButton.setOnClickListener {
            val location = viewModel?.selectedEvents?.value?.location
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