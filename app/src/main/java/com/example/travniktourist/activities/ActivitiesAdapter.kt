package com.example.travniktourist.activities

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.travniktourist.databinding.ActivitiesItemBinding

class ActivitiesAdapter(private val items: List<Activities>, private val onItemClick: (Activities) -> Unit)
    : RecyclerView.Adapter<ActivitiesAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ActivitiesItemBinding)
        : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ActivitiesItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val activities = items[position]

        with(holder.binding) {
            activitiesImage.load(activities.imageFile) {
                crossfade(true)
            }
            activitiesNameText.text = activities.name
            durationText.text = activities.duration
            priceText.text = activities.price
        }

        holder.itemView.setOnClickListener {
            onItemClick(activities)
        }
    }

    override fun getItemCount(): Int = items.size
}