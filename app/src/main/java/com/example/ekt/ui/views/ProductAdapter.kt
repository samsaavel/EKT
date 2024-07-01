package com.example.ekt.ui.views

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ekt.data.Product
import com.example.ekt.databinding.ProductRowBinding

class ProductAdapter(
    private var products: List<Product>,
    private val category: String,
    private val productSelectedListener: ProductSelectedListener,
) : RecyclerView.Adapter<ProductViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = ProductRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductViewHolder(binding, productSelectedListener)
    }

    override fun getItemCount(): Int = products.size

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bindModelToView(products[position], category)
    }

}