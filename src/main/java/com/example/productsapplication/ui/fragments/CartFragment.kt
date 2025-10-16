package com.example.productsapplication.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.productsapplication.R
import com.example.productsapplication.data.db.AppDatabase
import com.example.productsapplication.data.db.CartItem
import com.example.productsapplication.data.model.Product
import com.example.productsapplication.databinding.FragmentCartBinding
import com.example.productsapplication.repository.CartRepository
import com.example.productsapplication.repository.ProductRepository
import com.example.productsapplication.ui.fragments.ProductAdapter
import kotlinx.coroutines.launch

class CartFragment : Fragment() {

    private var _binding: FragmentCartBinding? = null
    private val binding get() = _binding!!

    private lateinit var cartRepo: CartRepository
    private lateinit var productRepo: ProductRepository
    private var cartProducts = mutableListOf<Product>()
    private var totalAmount = 0.0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize repositories
        cartRepo = CartRepository(AppDatabase.getDatabase(requireContext()).cartDao())
        productRepo = ProductRepository(requireContext())

        // Setup RecyclerView
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // Load cart data
        loadCartItems()

        // Checkout button click
        binding.btnCheckout.setOnClickListener {
            if (cartProducts.isEmpty()) {
                Toast.makeText(requireContext(), "Your cart is empty!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(
                    requireContext(),
                    "Proceeding to checkout (Total: ₹${String.format("%.2f", totalAmount)})",
                    Toast.LENGTH_LONG
                ).show()
                // Example navigation (you can create a CheckoutFragment later)
                // findNavController().navigate(R.id.checkoutFragment)
            }
        }
    }

    private fun loadCartItems() {
        lifecycleScope.launch {
            val cartItems: List<CartItem> = cartRepo.getAllCartItems()
            cartProducts.clear()
            totalAmount = 0.0

            for (item in cartItems) {
                val result = productRepo.getProductById(item.productId)
                result.onSuccess { product ->
                    cartProducts.add(product)
                    totalAmount += product.price
                }
            }

            binding.recyclerView.adapter = ProductAdapter(cartProducts) { product ->
                val bundle = Bundle().apply { putInt("productId", product.id) }
                findNavController().navigate(R.id.productDetailFragment, bundle)
            }

            // Update total amount text
            binding.totalAmountTextView.text = "Total: ₹${String.format("%.2f", totalAmount)}"
            binding.cartCount.text = "${cartProducts.size} items"
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
