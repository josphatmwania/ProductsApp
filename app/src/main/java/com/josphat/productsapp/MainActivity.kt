package com.josphat.productsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.rememberNavController
import com.josphat.productsapp.data.ProductsRepositoryImpl
import com.josphat.productsapp.data.db.ProductDatabase
import com.josphat.productsapp.data.remote.RetrofitInstance
import com.josphat.productsapp.navigation.AppNavigation
import com.josphat.productsapp.presentation.viewmodel.ProductsViewModel
import com.josphat.productsapp.ui.theme.ProductsAppTheme
import androidx.compose.ui.Modifier // Ensure this import is present

// Custom ViewModel Factory
class ProductsViewModelFactory(
    private val repository: ProductsRepositoryImpl
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProductsViewModel::class.java)) {
            return ProductsViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

class MainActivity : ComponentActivity() {

    // Create an instance of ProductsViewModel with a custom ViewModel factory
    private val viewModel: ProductsViewModel by viewModels {
        ProductsViewModelFactory(
            ProductsRepositoryImpl(
                RetrofitInstance.productAPI,
                ProductDatabase.getDatabase(application).productDao()
            )
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProductsAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // Set up navigation
                    val navController = rememberNavController()
                    AppNavigation(navController = navController, viewModel = viewModel)
                }
            }
        }
    }
}
