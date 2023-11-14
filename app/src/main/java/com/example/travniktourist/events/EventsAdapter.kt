package com.example.travniktourist.events

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.travniktourist.databinding.EventsItemBinding

class EventsAdapter(private val items: List<Events>, private val onItemClick: (Events) -> Unit)
    : RecyclerView.Adapter<EventsAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: EventsItemBinding)
        : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            EventsItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val events = items[position]

        with(holder.binding) {
            eventsImage.load(events.imageFile) {
                crossfade(true)
            }
            eventsNameText.text = events.name
            durationText.text = events.time
            priceText.text = events.price
        }

        holder.itemView.setOnClickListener {
            onItemClick(events)
        }
    }

    override fun getItemCount(): Int = items.size
}