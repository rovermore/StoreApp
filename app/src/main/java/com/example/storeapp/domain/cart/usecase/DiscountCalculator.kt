package com.example.storeapp.domain.cart.usecase

import com.example.storeapp.domain.cart.model.CartItemDTO
import javax.inject.Inject

class DiscountCalculator @Inject constructor() {

    enum class ProductCode(val code: String) {
        VOUCHER("VOUCHER"),
        TSHIRT("TSHIRT")
    }

    fun calculateDiscount(itemDTOList: List<CartItemDTO>): Double {
        return if (itemDTOList.isNotEmpty()) {
            val voucherDiscount: Double = itemDTOList.filter { it.productDTO.code == ProductCode.VOUCHER.code }.let { list ->
                if (list.isNotEmpty() && list[0].totalItem > 1) {
                    with(list[0]) {
                        if (totalItem.mod(2) == 0)
                            totalAmount / 2
                        else
                            (totalAmount - productDTO.price) / 2
                    }
                } else 0.0
            }
            val tshirtDiscount: Double = itemDTOList.filter { it.productDTO.code == ProductCode.TSHIRT.code }.let {list ->
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
}