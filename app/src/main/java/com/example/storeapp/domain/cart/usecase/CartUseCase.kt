package com.example.storeapp.domain.cart.usecase

import com.example.storeapp.domain.base.Error
import com.example.storeapp.domain.base.Result
import com.example.storeapp.domain.base.map
import com.example.storeapp.domain.base.mapFailure
import com.example.storeapp.domain.cart.model.CartDTO
import com.example.storeapp.domain.cart.model.CartItemDTO
import com.example.storeapp.domain.cart.model.ProductSelectionDTO
import com.example.storeapp.domain.cart.repository.CartRepository
import com.example.storeapp.domain.product.model.ProductDTO
import javax.inject.Inject

class CartUseCase @Inject constructor(
    private val cartRepository: CartRepository
) {

    private fun getProductSelection(): Result<ProductSelectionDTO, Error> {
        return cartRepository.getProducts()
    }

    fun addProduct(productSelected: ProductDTO): Result<Boolean, Error> {
        return getProductSelection()
            .map {
                it.products.add(productSelected)
                return cartRepository.saveProducts(ProductSelectionDTO(products =  it.products))
            }.mapFailure {
                return cartRepository.saveProducts(ProductSelectionDTO(products =  mutableListOf(productSelected)))
            }
    }

    fun deleteProduct(productSelected: ProductDTO): Result<Boolean, Error> {
        return getProductSelection()
            .map { productSelection ->
                productSelection.products.find { it.code == productSelected.code }?.let {
                    productSelection.products.remove(it)
                    cartRepository.saveProducts(ProductSelectionDTO(products = productSelection.products))
                }
                true
            }
    }


    fun getCart(): Result<CartDTO, Error> {
        return getProductSelection().map { products ->
            val itemList = products.products.groupBy { it.code }
            val itemDTOList = mutableListOf<CartItemDTO>()
            itemList.forEach { itemType ->
                itemDTOList.add(
                    CartItemDTO(
                        code = itemType.key,
                        name = itemType.value[0].name,
                        totalItem = itemType.value.size,
                        totalAmount = itemType.value.sumOf { it.price }
                    )
                )
            }
            val discount = calculateDiscount(itemDTOList)
            val totalBeforeDiscount = itemDTOList.sumOf { it.totalAmount }

            CartDTO(
                items = itemDTOList,
                totalBeforeDiscount = totalBeforeDiscount,
                discount = discount,
                totalAmount = totalBeforeDiscount - discount
            )
        }
    }

    private fun calculateDiscount(itemDTOList: List<CartItemDTO>): Double {
        return if (itemDTOList.isNotEmpty()) {
            val voucherDiscount: Double = itemDTOList.filter { it.code == "VOUCHER" }.let { list ->
                if (list.isNotEmpty() && list[0].totalItem > 1) {
                    with(list[0]) {
                        if (totalItem.mod(2) == 0)
                            totalAmount / 2
                        else
                            totalAmount / 2 + (totalAmount / totalItem)
                    }
                } else 0.0
            }
            val tshirtDiscount: Double = itemDTOList.filter { it.code == "TSHIRT" }.let {list ->
                if (list.isNotEmpty()) {
                    with(list[0]) {
                        if (totalItem >= 3)
                            totalItem.toDouble()
                        else
                            0.0
                    }
                } else 0.0
            }

            voucherDiscount + tshirtDiscount
        } else
            0.0
    }

    fun clearCart(): Result<Boolean, Error> {
        return cartRepository.clearProducts()
    }
}