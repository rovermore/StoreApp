package com.example.storeapp.domain.cart.usecase

import com.example.storeapp.domain.base.Error
import com.example.storeapp.domain.base.Result
import com.example.storeapp.domain.cart.model.CartDTO
import com.example.storeapp.domain.cart.repository.CartRepository
import javax.inject.Inject

class CartUseCase @Inject constructor(
    private val cartRepository: CartRepository
) {
    fun saveCart(cartDTO: CartDTO): Result<Boolean, Error> {
        return cartRepository.saveCart(cartDTO)
    }

    fun getCart(): Result<CartDTO, Error> {
        return cartRepository.getCart()
    }

    fun clearCart(): Result<Boolean, Error> {
        return cartRepository.clearCart()
    }
}