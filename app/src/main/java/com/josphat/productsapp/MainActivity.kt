package com.josphat.productsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.lifecycle.ViewModel
import androidx.activity.viewModels
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import com.josphat.productsapp.data.ProductsRepositoryImpl
import com.josphat.productsapp.data.remote.RetrofitInstance
import com.josphat.productsapp.presentation.screens.ProductsScreen
import com.josphat.productsapp.presentation.viewmodel.ProductsViewModel
import com.josphat.productsapp.ui.theme.ProductsAppTheme

// ViewModel factory & pass Products repository implementation
class MainActivity : ComponentActivity() {

    // Create an instance of ProductsViewModel with a custom ViewModel factory
    // to inject ProductsRepositoryImpl that uses Retrofit for network calls.
    private val viewModel by viewModels<ProductsViewModel>(factoryProducer = {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                // Return an instance of ProductsViewModel with the repository
                return ProductsViewModel(ProductsRepositoryImpl(RetrofitInstance.productAPI)) as T
            }
        }
    })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProductsAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // Pass the viewModel to ProductsScreen to use in the UI layer
                    ProductsScreen(viewModel)
                }
            }
        }
    }
}
