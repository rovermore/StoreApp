package com.example.storeapp.data.cart

import com.example.storeapp.data.cart.local.ProductLocalDatasource
import com.example.storeapp.domain.base.Error
import com.example.storeapp.domain.base.Result
import com.example.storeapp.domain.cart.model.ProductSelectionDTO
import com.example.storeapp.domain.cart.repository.CartRepository
import javax.inject.Inject

class CartRepositoryImpl @Inject constructor(
    private val productLocalDatasource: ProductLocalDatasource
): CartRepository {

    override fun saveProducts(cart: ProductSelectionDTO): Result<Boolean, Error> {
        return productLocalDatasource.saveProducts(cart)
    }

    override fun getProducts(): Result<ProductSelectionDTO, Error> {
        return productLocalDatasource.retrieveProducts()
    }

    override fun clearProducts(): Result<Boolean, Error> {
        return productLocalDatasource.deleteProducts()
    }
}