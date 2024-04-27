package com.example.storeapp.data.product.model

import com.example.storeapp.domain.product.model.ProductDTO
import javax.inject.Inject

class ProductResponseMapper @Inject constructor() {

    fun map(product: Product): ProductDTO {
        with(product) {
            return ProductDTO(
                code = code ?: "",
                name = name ?: "",
                price = price ?: 0.0
            )
        }
    }

    fun mapList(catalogResponse: CatalogResponse): List<ProductDTO> {
        val list = mutableListOf<ProductDTO>()
        catalogResponse.products.map {  product ->
            product?.let {
                list.add(map(it))
            }
        }
        return list
    }
}