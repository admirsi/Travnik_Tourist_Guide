package com.example.travniktourist.activities

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.travniktourist.databinding.FragmentActivitiesBinding
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.travniktourist.LOG_TAG
import com.example.travniktourist.R.id.action_activitiesFragment_to_detailActivitiesFragment

class ActivitiesFragment : Fragment() {

    private var viewModel: SharedActivitiesModel? = null
    private var _binding: FragmentActivitiesBinding? = null
    private val binding get() = _binding!!

    private val onItemClick: (Activities) -> Unit = { activities ->
        Log.i(LOG_TAG, "the selected activities: $activities")
        viewModel?.selectedActivities?.value = activities
        findNavController().navigate(action_activitiesFragment_to_detailActivitiesFragment)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentActivitiesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = activity?.run {
            ViewModelProvider(requireActivity())[SharedActivitiesModel::class.java]
        }

        viewModel?.activities?.observe(viewLifecycleOwner) { activities ->
            binding.activitiesList.adapter = ActivitiesAdapter(activities, onItemClick)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}