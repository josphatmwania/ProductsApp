package com.josphat.productsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.josphat.productsapp.data.ProductsRepositoryImpl
import com.josphat.productsapp.data.remote.RetrofitInstance
import com.josphat.productsapp.presentation.screens.ProductsScreen
import com.josphat.productsapp.presentation.viewmodel.ProductsViewModel
import com.josphat.productsapp.ui.theme.ProductsAppTheme


//Todo: Viewmodel factory & pass Products repository implementation
private val viewModel by viewModels<ProductsViewModel>(factoryProducer = {
    object : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return ProductsViewModel(ProductsRepositoryImpl(RetrofitInstance.productAPI))
                    as T
        }
    }
})


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProductsAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ProductsScreen(viewModel)
                }
            }
        }
    }
}

