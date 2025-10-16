package com.example.productsapplication.ui.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs

import kotlinx.coroutines.launch
import coil.load
import com.example.productsapplication.R
import com.example.productsapplication.data.db.AppDatabase
import com.example.productsapplication.data.db.AppState
import com.example.productsapplication.data.db.CartItem
import com.example.productsapplication.data.db.FavoriteItem
import com.example.productsapplication.repository.FavoritesRepository
import com.example.productsapplication.databinding.FragmentProductDetailBinding
import com.example.productsapplication.repository.AppRepository
import com.example.productsapplication.repository.CartRepository
import com.example.productsapplication.repository.ProductRepository
import com.example.productsapplication.viewmodel.ProductDetailViewModel
import kotlinx.coroutines.MainScope

class ProductDetailFragment : Fragment() {

    private var _binding: FragmentProductDetailBinding? = null
    private val binding get() = _binding!!
    private val args: ProductDetailFragmentArgs by navArgs()


    private lateinit var appRepo: AppRepository

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentProductDetailBinding.inflate(inflater, container, false)
        val dao = AppDatabase.getDatabase(requireContext()).appStateDao()
        appRepo = AppRepository(dao)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val animation = AnimationUtils.loadAnimation(requireContext(), R.anim.slide_fade_in_right)
        binding.root.startAnimation(animation)

        val productRepo = ProductRepository(requireContext())
        val viewModel: ProductDetailViewModel by viewModels {
            object : androidx.lifecycle.ViewModelProvider.Factory {
                override fun <T : androidx.lifecycle.ViewModel> create(modelClass: Class<T>): T {
                    return ProductDetailViewModel(productRepo) as T
                }
            }
        }
        val productId = args.productId

        viewModel.getProductById(productId).observe(viewLifecycleOwner) { result ->
            result.onSuccess { product->
                binding.productName.text = product.title
                binding.productPrice.text = "₹${product.price}"
                binding.productDescription.text = product.description
                binding.productImage.load(product.image) {
                    placeholder(android.R.drawable.ic_menu_report_image)
                    error(android.R.drawable.stat_notify_error)
                    crossfade(true)
                }
            }.onFailure { error ->
                binding.productName.text = "Error loading product"
                binding.productPrice.text = ""
                binding.productDescription.text = ""
                binding.productImage.setImageResource(android.R.drawable.stat_notify_error)
            }
        }
        binding.backButton.setOnClickListener {
            findNavController().navigateUp()
        }

        // Inside onViewCreated in ProductDetailFragment.kt

        val cartDao = AppDatabase.getDatabase(requireContext()).cartDao()
        val favoritesDao = AppDatabase.getDatabase(requireContext()).favoritesDao()

        val cartRepo = CartRepository(cartDao)
        val favRepo = FavoritesRepository(favoritesDao)

        fun updateButtons() {
            lifecycleScope.launch {
                val inCart = cartRepo.isInCart(productId)
                val inFav = favRepo.isInFavorites(productId)
                binding.btnCart.text = if (inCart) "Remove from Cart" else "Add to Cart"
                binding.btnFavorite.text = if (inFav) "Remove from Favorites" else "Add to Favorites"
            }
        }

// Call initially
        updateButtons()

        binding.btnCart.setOnClickListener {
            lifecycleScope.launch {
                val inCart = cartRepo.isInCart(productId)
                if (inCart) {
                    cartRepo.removeFromCart(productId)
                } else {
                    cartRepo.addToCart(CartItem(productId))
                }
                updateButtons()
            }
        }

        binding.btnFavorite.setOnClickListener {
            lifecycleScope.launch {
                val inFav = favRepo.isInFavorites(productId)
                if (inFav) {
                    favRepo.removeFromFavorites(productId)
                } else {
                    favRepo.addToFavorites(FavoriteItem(productId))
                }
                updateButtons()
            }
        }

        lifecycleScope.launch {
            appRepo.saveAppState(AppState(id = 0, lastScreen = "details", productId = productId))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}