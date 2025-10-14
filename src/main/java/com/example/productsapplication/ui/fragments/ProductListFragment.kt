package com.example.productsapplication.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.productsapplication.R
import com.example.productsapplication.data.db.AppDatabase
import com.example.productsapplication.data.db.AppState
import com.example.productsapplication.databinding.FragmentProductListBinding
import com.example.productsapplication.repository.AppRepository
import com.example.productsapplication.repository.ProductRepository
import com.example.productsapplication.viewmodel.ProductListViewModel
import kotlinx.coroutines.launch

class ProductListFragment : Fragment() {

    private var _binding: FragmentProductListBinding? = null
    private val binding get() = _binding!!

//    private val productRepo = ProductRepository()
//    private val viewModel: ProductListViewModel by viewModels {
//        object : androidx.lifecycle.ViewModelProvider.Factory {
//            override fun <T : androidx.lifecycle.ViewModel> create(modelClass: Class<T>): T {
//                return ProductListViewModel(productRepo) as T
//            }
//        }
//    }

    private lateinit var appRepo: AppRepository

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentProductListBinding.inflate(inflater, container, false)
        val dao = AppDatabase.getDatabase(requireContext()).appStateDao()
        appRepo = AppRepository(dao)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val logoAnim = AnimationUtils.loadAnimation(requireContext(), R.anim.item_slide_left)
        val titleAnim = AnimationUtils.loadAnimation(requireContext(), R.anim.slide_down_fade)
        titleAnim.startOffset = 200 // Slight delay for smoother effect

        binding.appLogo.startAnimation(logoAnim)
        binding.headerTitle.startAnimation(titleAnim)

        val repoWithContext = ProductRepository(requireContext())
        val vmFactory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return ProductListViewModel(repoWithContext) as T
            }
        }
        val viewModel: ProductListViewModel by viewModels { vmFactory }

        val recyclerView = binding.recyclerView
        val noInternetLayout = binding.noInternetLayout
        val retryButton = binding.retryButton

        fun loadProducts() {
            viewModel.products().observe(viewLifecycleOwner) { result ->
                result.onSuccess { list ->
                    recyclerView.visibility = View.VISIBLE
                    noInternetLayout.visibility = View.GONE

                    val adapter = ProductAdapter(list) { product ->
                        lifecycleScope.launch {
                            appRepo.saveAppState(AppState(id = 0, lastScreen = "details", productId = product.id))
                        }
                        val action = ProductListFragmentDirections
                            .actionProductListFragmentToProductDetailFragment(product.id)
                        findNavController().navigate(action)
                    }
                    recyclerView.layoutManager = LinearLayoutManager(requireContext())
                    recyclerView.adapter = adapter
                }.onFailure { error ->
                    recyclerView.visibility = View.GONE
                    noInternetLayout.visibility = View.VISIBLE
                }
            }
        }

        retryButton.setOnClickListener { loadProducts() }

        loadProducts()

        lifecycleScope.launch {
            appRepo.saveAppState(AppState(id = 0, lastScreen = "list"))
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}