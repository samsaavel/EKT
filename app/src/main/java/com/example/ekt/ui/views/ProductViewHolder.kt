package com.example.ekt.ui.views

import android.icu.text.DecimalFormat
import android.util.Log
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ekt.data.Product
import com.example.ekt.databinding.ProductRowBinding

interface ProductSelectedListener {
    fun onProductSelected(product: Product, categoria: String)
}

class ProductViewHolder(
    private val binding: ProductRowBinding,
    private val productSelectedListener: ProductSelectedListener,
) : RecyclerView.ViewHolder(binding.root) {

    fun bindModelToView(product: Product, category: String) {
        Log.d("ProductViewHolder", "Binding product: $product, Category: $category")
        val cardView: CardView = binding.cardProduct
        cardView.setOnClickListener {
            cardView.isSelected = !cardView.isSelected
        }
        binding.productName.text = product.nombre
        binding.productPrice.text = "$" + DecimalFormat("#.00").format(product.precioFinal)
        binding.category.text = category
        val url: String = product.urlImagenes.first()
        Glide.with(binding.imageView.context)
            .load(url)
            .into(binding.imageView)
//        binding.imageView = product.urlImagenes.first()
        binding.root.setOnClickListener {
            productSelectedListener.onProductSelected(product, category)
        }

    }
}
