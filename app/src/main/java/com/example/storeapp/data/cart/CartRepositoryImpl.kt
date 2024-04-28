package com.example.storeapp.data.cart

import com.example.storeapp.data.cart.local.ProductLocalDatasource
import com.example.storeapp.domain.base.Error
import com.example.storeapp.domain.base.Result
import com.example.storeapp.domain.cart.model.CartDTO
import com.example.storeapp.domain.cart.repository.CartRepository
import javax.inject.Inject

class CartRepositoryImpl @Inject constructor(
    private val productLocalDatasource: ProductLocalDatasource
): CartRepository {

    override fun saveCart(cart: CartDTO): Result<Boolean, Error> {
        return productLocalDatasource.saveCart(cart)
    }

    override fun getCart(): Result<CartDTO, Error> {
        return productLocalDatasource.retrieveCart()
    }

    override fun clearCart(): Result<Boolean, Error> {
        return productLocalDatasource.deleteCart()
    }
}