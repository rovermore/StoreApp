package com.example.storeapp.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.storeapp.domain.base.map
import com.example.storeapp.domain.base.mapFailure
import com.example.storeapp.domain.base.then
import com.example.storeapp.domain.cart.usecase.CartUseCase
import com.example.storeapp.domain.product.model.ProductDTO
import com.example.storeapp.domain.product.usecase.ProductUseCase
import com.example.storeapp.presentation.base.ErrorUI
import com.example.storeapp.presentation.base.ErrorUIMapper
import com.example.storeapp.presentation.main.model.CartUIModel
import com.example.storeapp.presentation.main.model.CartUIModelMapper
import com.example.storeapp.presentation.main.model.ProductUIModel
import com.example.storeapp.presentation.main.model.ProductUIModelMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val productUseCase: ProductUseCase,
    private val errorUIMapper: ErrorUIMapper,
    private val productUIModelMapper: ProductUIModelMapper,
    private val cartUseCase: CartUseCase,
    private val cartUIModelMapper: CartUIModelMapper
): ViewModel() {

    private val _productsCatalog = MutableStateFlow<List<ProductUIModel>>(emptyList())
    val productsCatalog: StateFlow<List<ProductUIModel>> get() = _productsCatalog

    private val _cart = MutableStateFlow<CartUIModel>(CartUIModel())
    val cart: StateFlow<CartUIModel> get() = _cart

    private val _error = MutableStateFlow<ErrorUI>(ErrorUI.None)
    val error: StateFlow<ErrorUI> get() = _error

    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> get() = _isLoading

    private val _isCartLoading = MutableStateFlow(true)
    val isCartLoading: StateFlow<Boolean> get() = _isCartLoading

    init {
        getProductCatalog()
        getCart()
    }

    private fun getProductCatalog() {
        viewModelScope.launch(Dispatchers.IO) {
            productUseCase.getProductsCatalog()
                .map {
                    delay(2000)
                    _productsCatalog.value = productUIModelMapper.mapList(it)
                }.mapFailure {
                    _error.value = errorUIMapper.map(it)
                }.then {
                    _isLoading.value = false
                }
        }
    }

    fun addProduct(product: ProductUIModel) {
        viewModelScope.launch(Dispatchers.IO) {
            cartUseCase.addProduct(ProductDTO(product.code, product.name, product.price.toDouble()))
                .map {
                    getCart()
                }
        }
    }

    fun deleteProduct(product: ProductUIModel) {
        viewModelScope.launch(Dispatchers.IO) {
            cartUseCase.deleteProduct(ProductDTO(product.code, product.name, product.price.toDouble()))
                .map {
                    getCart()
                }
        }
    }

    fun getCart() {
        viewModelScope.launch(Dispatchers.IO) {
            cartUseCase.getCart()
                .map {
                    _cart.value = cartUIModelMapper.map(it)
                }.then {
                    _isCartLoading.value = false
                }
        }
    }

    fun clearCart() {
        viewModelScope.launch(Dispatchers.IO) {
            cartUseCase.clearCart()
                .map {
                    getCart()
                }
        }
    }

}