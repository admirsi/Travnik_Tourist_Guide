package com.example.travniktourist.events

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.travniktourist.LOG_TAG
import com.example.travniktourist.R.id.action_action_events_to_detailEventsFragment
import com.example.travniktourist.databinding.FragmentEventsBinding

class EventsFragment : Fragment() {

    private var viewModel: SharedEventsModel? = null
    private var _binding: FragmentEventsBinding? = null
    private val binding get() = _binding!!

    private val onItemClick: (Events) -> Unit = { events ->
        Log.i(LOG_TAG, "the selected event: $events")
        viewModel?.selectedEvents?.value = events
        findNavController().navigate(action_action_events_to_detailEventsFragment)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEventsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = activity?.run {
            ViewModelProvider(requireActivity())[SharedEventsModel::class.java]
        }

        viewModel?.events?.observe(viewLifecycleOwner) { events ->
            binding.eventsList.adapter = EventsAdapter(events, onItemClick)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}