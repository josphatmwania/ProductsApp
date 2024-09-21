package com.josphat.productsapp.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.josphat.productsapp.data.ProductsRepository
import com.josphat.productsapp.data.model.Product
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import com.josphat.productsapp.data.Result

class ProductsViewModel(
    private val productsRepository: ProductsRepository
) : ViewModel() {

    private val _products = MutableStateFlow<List<Product>>(emptyList())
    val products = _products.asStateFlow()

    private val _showErrorToastChannel = Channel<Boolean>()
    val showErrorToastChannel = _showErrorToastChannel.receiveAsFlow()

    init {
        viewModelScope.launch {
            Log.d("ProductsViewModel", "Fetching product list...")
            productsRepository.getProductList().collectLatest { result ->
                when (result) {
                    is Result.Success -> {
                        result.data?.let { productsList ->
                            Log.d("ProductsViewModel", "Fetched products: $productsList")
                            _products.update { productsList }
                        }
                    }
                    is Result.Error -> {
                        Log.e("ProductsViewModel", "Error fetching products")
                        _showErrorToastChannel.send(true)
                    }
                }
            }
        }
    }
}
