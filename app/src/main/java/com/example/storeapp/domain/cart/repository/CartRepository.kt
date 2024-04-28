package com.example.storeapp.domain.cart.repository

import com.example.storeapp.domain.base.Error
import com.example.storeapp.domain.base.Result
import com.example.storeapp.domain.cart.model.CartDTO

interface CartRepository {

    fun saveCart(cart: CartDTO): Result<Boolean, Error>

    fun getCart(): Result<CartDTO, Error>

    fun clearCart(): Result<Boolean, Error>
}