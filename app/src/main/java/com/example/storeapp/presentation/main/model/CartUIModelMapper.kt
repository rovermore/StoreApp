package com.example.storeapp.presentation.main.model

import com.example.storeapp.domain.cart.model.CartDTO
import com.example.storeapp.domain.cart.model.CartItemDTO
import javax.inject.Inject

class CartUIModelMapper @Inject constructor() {

    fun map(cartDTO: CartDTO): CartUIModel {
        return CartUIModel(
            items = mapItems(cartDTO.items),
            totalBeforeDiscount = cartDTO.totalBeforeDiscount.toBigDecimal().stripTrailingZeros().toPlainString(),
            totalAmount = cartDTO.totalAmount.toBigDecimal().stripTrailingZeros().toPlainString(),
            discount = cartDTO.discount.toBigDecimal().stripTrailingZeros().toPlainString()
        )
    }

    private fun mapItems(itemsDTO: List<CartItemDTO>): List<CartItemUIModel> {
        val list = mutableListOf<CartItemUIModel>()
        itemsDTO.map {
            list.add(
                CartItemUIModel(
                    code = it.code,
                    name = it.name,
                    totalAmount = it.totalAmount.toBigDecimal().stripTrailingZeros().toPlainString(),
                    totalItem = it.totalItem.toString()
                )
            )
        }
        return list
    }
}