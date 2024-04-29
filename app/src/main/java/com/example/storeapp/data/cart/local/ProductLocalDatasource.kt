package com.example.storeapp.data.cart.local

import com.example.storeapp.domain.base.Error
import com.example.storeapp.domain.base.Result
import com.example.storeapp.domain.base.map
import com.example.storeapp.domain.cart.model.ProductSelectionDTO
import com.example.storeapp.parseJSON
import com.google.gson.GsonBuilder
import javax.inject.Inject

class ProductLocalDatasource @Inject constructor(
    private val productDataSource: CartDataStore
) {

    companion object {
        const val CART_KEY = "CART_KEY"
    }

    fun saveProducts(cart: ProductSelectionDTO): Result<Boolean, Error> {
        val cartObject = GsonBuilder().create().toJson(cart, ProductSelectionDTO::class.java)
        return productDataSource.save<String>(CART_KEY, cartObject)
    }

    fun retrieveProducts(): Result<ProductSelectionDTO, Error> {
        return productDataSource.read<String>(CART_KEY)
            .map {
                return it.parseJSON<ProductSelectionDTO>()
            }
    }

    fun deleteProducts(): Result<Boolean, Error> {
        return productDataSource.delete<String>(CART_KEY)
    }

}