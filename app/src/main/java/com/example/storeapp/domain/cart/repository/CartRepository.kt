package com.example.storeapp.domain.cart.repository

import com.example.storeapp.domain.base.Error
import com.example.storeapp.domain.base.Result
import com.example.storeapp.domain.cart.model.ProductSelectionDTO

interface CartRepository {

    fun saveProducts(cart: ProductSelectionDTO): Result<Boolean, Error>

    fun getProducts(): Result<ProductSelectionDTO, Error>

    fun clearProducts(): Result<Boolean, Error>
}