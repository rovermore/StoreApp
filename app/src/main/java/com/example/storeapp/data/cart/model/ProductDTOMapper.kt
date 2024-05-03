package com.example.storeapp.data.cart.model

import com.example.storeapp.data.product.model.ProductDAO
import com.example.storeapp.domain.cart.model.ProductSelectionDTO
import javax.inject.Inject

class ProductDTOMapper @Inject constructor() {

    fun map(productSelectionDTO: ProductSelectionDTO): ProductSelectionDAO {
        val list = mutableListOf<ProductDAO>()
        productSelectionDTO.products.map {
            list.add(
                ProductDAO(
                    code = it.code,
                    name = it.name,
                    price= it.price
                )
            )
        }
        return ProductSelectionDAO(products = list)
    }
}