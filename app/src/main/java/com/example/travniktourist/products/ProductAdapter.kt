package com.example.travniktourist.products

import android.icu.text.NumberFormat
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.travniktourist.R
import com.example.travniktourist.data.Product
import com.example.travniktourist.databinding.ProductItemBinding

class ProductAdapter(private val items: List<Product>, private val onItemClick: (Product) -> Unit)
    : RecyclerView.Adapter<ProductAdapter.ViewHolder>() {

        inner class ViewHolder(val binding: ProductItemBinding)
            : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ProductItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = items[position]

        with(holder.binding) {
            productImage.load(product.imageFile) {
                crossfade(true)
            }
            productNameText.text = product.name
            durationText.text = product.duration
            typeText.text = product.type
        }

        holder.itemView.setOnClickListener {
            onItemClick(product)
        }
    }

    override fun getItemCount(): Int = items.size
}