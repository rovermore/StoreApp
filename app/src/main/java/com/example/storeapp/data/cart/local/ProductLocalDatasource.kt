package com.example.storeapp.data.cart.local

import com.example.storeapp.domain.base.Error
import com.example.storeapp.domain.base.Result
import com.example.storeapp.domain.base.map
import com.example.storeapp.domain.cart.model.CartDTO
import com.example.storeapp.parseJSON
import com.google.gson.GsonBuilder
import javax.inject.Inject

class ProductLocalDatasource @Inject constructor(
    private val productDataSource: CartDataStore
) {

    companion object {
        const val CART_KEY = "CART_KEY"
    }

    fun saveCart(cart: CartDTO): Result<Boolean, Error> {
        val cartObject = GsonBuilder().create().toJson(cart, CartDTO::class.java)
        return productDataSource.save<String>(CART_KEY, cartObject)
    }

    fun retrieveCart(): Result<CartDTO, Error> {
        return productDataSource.read<String>(CART_KEY)
            .map {
                return it.parseJSON<CartDTO>()
            }
    }

    fun deleteCart(): Result<Boolean, Error> {
        return productDataSource.delete<String>(CART_KEY)
    }

}