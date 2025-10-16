package com.example.productsapplication.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.productsapplication.R
import com.example.productsapplication.data.db.AppDatabase
import com.example.productsapplication.data.db.FavoriteItem
import com.example.productsapplication.data.model.Product
import com.example.productsapplication.databinding.FragmentFavoritesBinding
import com.example.productsapplication.repository.FavoritesRepository
import com.example.productsapplication.repository.ProductRepository
import kotlinx.coroutines.launch

class FavoritesFragment : Fragment() {

    private var _binding: FragmentFavoritesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())

        val favoritesRepo = FavoritesRepository(AppDatabase.getDatabase(requireContext()).favoritesDao())
        val productRepo = ProductRepository(requireContext())

        lifecycleScope.launch {
            val favoriteItems: List<FavoriteItem> = favoritesRepo.getAllFavorites()
            val products = mutableListOf<Product>()

            // Fetch product details
            for (item in favoriteItems) {
                val result = productRepo.getProductById(item.productId)
                result.onSuccess { products.add(it) }
            }

            // Update favorites count dynamically
            binding.favoritesCount.text = "${products.size} items"

            // Set adapter
            binding.recyclerView.adapter = ProductAdapter(products) { product ->
                val bundle = Bundle().apply { putInt("productId", product.id) }
                findNavController().navigate(R.id.productDetailFragment, bundle)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
