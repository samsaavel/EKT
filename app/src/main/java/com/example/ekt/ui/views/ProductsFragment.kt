package com.example.ekt.ui.views


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ekt.data.Product
import com.example.ekt.databinding.FragmentProductsBinding
import com.example.ekt.vm.EKTIntent
import com.example.ekt.vm.EKTState
import com.example.ekt.vm.EKTViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class ProductsFragment() : Fragment(), ProductSelectedListener {
    private var _binding: FragmentProductsBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: EKTViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(EKTViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentProductsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("******ProductsFragment", "View created")
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        observeViewModel()
    }

    private fun observeViewModel() {
        lifecycleScope.launch {
            viewModel.ektState.collectLatest { state ->
                when (state) {
                    EKTState.Failure -> {
                        Log.e("******ProductsFragment", "Failed to load data")
                        binding.progressBar.visibility = View.GONE
                        Toast.makeText(context, "Failed to load data", Toast.LENGTH_SHORT).show()

                    }

                    EKTState.Loading -> {
                        Log.d("******ProductsFragment", "Loading data")
                        binding.progressBar.visibility = View.VISIBLE
                    }

                    EKTState.None -> {
                        Log.d("******ProductsFragment", "None state")
                        viewModel.handleIntent(EKTIntent.getProductsIntent)
                    }

                    is EKTState.Success -> {
                        binding.progressBar.visibility = View.GONE
                        val products = state.response.resultado.productos
                        val categoria = state.response.resultado.categoria
                        binding.recyclerView.adapter =
                            ProductAdapter(products, categoria, this@ProductsFragment)
                        Log.d("******ProductsFragment", "observeViewModel, setup adapter ")
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onProductSelected(product: Product, categoria: String) {
        val action = ProductsFragmentDirections.productsFragmentToDetailProduct(product, categoria)
        findNavController().navigate(action)
    }
}