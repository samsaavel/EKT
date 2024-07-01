package com.example.ekt.ui.views

import android.icu.text.DecimalFormat
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.ekt.databinding.FragmentDetailBinding

class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!
    private lateinit var imageSliderAdapter: ImageSliderAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args: DetailFragmentArgs by navArgs()
        val imageUrls = args.product.urlImagenes
        imageSliderAdapter = ImageSliderAdapter(imageUrls)
        binding.imageSlider.adapter = imageSliderAdapter
        binding.productName.text = args.product.nombre
        binding.productPrice.text = "$" + DecimalFormat("#.00").format(args.product.precioFinal)
//        binding.productPrice.text = args.product.precioFinal.toString()
        binding.category.text = args.category
    }
}