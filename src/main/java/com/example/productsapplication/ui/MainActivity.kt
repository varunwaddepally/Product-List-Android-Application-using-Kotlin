package com.example.productsapplication.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.productsapplication.R
import com.example.productsapplication.data.db.AppDatabase
import com.example.productsapplication.repository.AppRepository

import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var appRepo: AppRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val dao = AppDatabase.getDatabase(this).appStateDao()
        appRepo = AppRepository(dao)

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        if (savedInstanceState == null) {
            lifecycleScope.launch {
                val state = appRepo.getAppState()
                if (state != null) {
                    if (state.lastScreen == "details" && state.productId != null) {
                        val bundle = Bundle().apply { putInt("productId", state.productId) }
                        navController.navigate(R.id.productDetailFragment, bundle)
                    } else {
                        navController.navigate(R.id.productListFragment)
                    }
                } else {
                    navController.navigate(R.id.productListFragment)
                }
            }
        }
    }
}