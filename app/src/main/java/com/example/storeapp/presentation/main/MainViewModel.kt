package com.example.storeapp.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.storeapp.domain.base.map
import com.example.storeapp.domain.base.mapFailure
import com.example.storeapp.domain.cart.usecase.CartUseCase
import com.example.storeapp.domain.product.usecase.ProductUseCase
import com.example.storeapp.presentation.base.ErrorUI
import com.example.storeapp.presentation.base.ErrorUIMapper
import com.example.storeapp.presentation.main.model.ProductUIModel
import com.example.storeapp.presentation.main.model.ProductUIModelMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class MainViewModel(
    private val productUseCase: ProductUseCase,
    private val errorUIMapper: ErrorUIMapper,
    private val productUIModelMapper: ProductUIModelMapper,
    private val cartUseCase: CartUseCase
): ViewModel() {

    private val _productsCatalog = MutableStateFlow<List<ProductUIModel>>(emptyList())
    val productsCatalog: StateFlow<List<ProductUIModel>> get() = _productsCatalog

    private val _error = MutableStateFlow<ErrorUI>(ErrorUI.None)
    val error: StateFlow<ErrorUI> get() = _error

    fun getProductCatalog() {
        viewModelScope.launch(Dispatchers.IO) {
            productUseCase.getProductsCatalog()
                .map {
                    _productsCatalog.value = productUIModelMapper.mapList(it)
                }.mapFailure {
                    _error.value = errorUIMapper.map(it)
                }
        }
    }

}