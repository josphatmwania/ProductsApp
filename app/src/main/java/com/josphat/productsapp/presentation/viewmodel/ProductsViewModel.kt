package com.josphat.productsapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.josphat.productsapp.data.ProductsRepository
import com.josphat.productsapp.data.model.Product
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update

class ProductsViewModel(
    private val productsRepository: ProductsRepository
) : ViewModel() {

    //tODO:  immutable stateflow of List of products

    private val _products = MutableStateFlow<List<Product>>(emptyList())

    val products = _products.asStateFlow()


    // tODO: Show a toast on error
    private val _showErrorToastChannel= Channel<Boolean>()
    val showErrorToastChannel = _showErrorToastChannel.receiveAsFlow()



//     todo: get the data in a coroutine

    init {
        productsRepository.getProductList().collectLatest { result ->
            when(result) {
                _showErrorToastChannel.send(true)
            }
            is Result.success(products) -> {
                result.data?.let { products ->
                    _products.update { products }
                }
        }
        }

    }





}