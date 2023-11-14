package com.example.travniktourist.products

import android.content.Intent
import android.icu.text.NumberFormat
import android.net.Uri
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import coil.load
import com.example.travniktourist.R.string
import com.example.travniktourist.databinding.FragmentDetailBinding

class DetailFragment : Fragment() {

    private var viewModel: SharedViewModel? = null
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = activity?.run {
            ViewModelProvider(requireActivity())[SharedViewModel::class.java]
        }

        viewModel?.selectedProduct?.observe(viewLifecycleOwner) { product ->
            with(product) {
                binding.productImage.load(imageFile)
                binding.productNameText.text = name
                binding.descriptionText.text = description
                binding.durationText.text = duration
                binding.addressText.text = address
                binding.typeText.text = type
                binding.goToLocationButton.text = location //binding.priceText.text = NumberFormat.getCurrencyInstance().format(property.price)
            }
        }

        val goToLocationButton = binding.goToLocationButton
        goToLocationButton.setOnClickListener {
            val location = viewModel?.selectedProduct?.value?.location
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