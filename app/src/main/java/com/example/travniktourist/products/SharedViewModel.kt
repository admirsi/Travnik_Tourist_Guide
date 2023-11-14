package com.example.travniktourist.products

import androidx.lifecycle.*
import com.example.travniktourist.data.Product
import com.example.travniktourist.data.ProductRepository

class SharedViewModel : ViewModel() {

    private var productRepository: ProductRepository = ProductRepository()

    val selectedProduct: MutableLiveData<Product> = MutableLiveData()

    val products: LiveData<List<Product>> = liveData {
        val data = productRepository.getProducts()
        emit(data)
    }
}