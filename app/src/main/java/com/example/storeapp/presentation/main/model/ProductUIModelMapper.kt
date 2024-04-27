package com.example.storeapp.presentation.main.model

import com.example.storeapp.domain.product.model.ProductDTO
import javax.inject.Inject

class ProductUIModelMapper @Inject constructor() {

    fun map(product: ProductDTO): ProductUIModel {
        with(product) {
            return ProductUIModel(
                code = code,
                name = name,
                price = price.toBigDecimal().stripTrailingZeros().toPlainString()
            )
        }
    }

    fun mapList(productDTOList: List<ProductDTO>): List<ProductUIModel> {
        val list = mutableListOf<ProductUIModel>()
        productDTOList.map {  product ->
            list.add(map(product))
        }
        return list
    }
}