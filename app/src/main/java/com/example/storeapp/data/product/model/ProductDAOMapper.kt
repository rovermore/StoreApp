package com.example.storeapp.data.product.model

import com.example.storeapp.data.cart.model.ProductSelectionDAO
import com.example.storeapp.domain.cart.model.ProductSelectionDTO
import com.example.storeapp.domain.product.model.ProductDTO
import javax.inject.Inject

class ProductDAOMapper @Inject constructor() {

    fun map(productDAO: ProductDAO): ProductDTO {
        with(productDAO) {
            return ProductDTO(
                code = code ?: "",
                name = code ?: "",
                price = price ?: 0.0
            )
        }
    }

    fun mapCatalog(catalogResponseDAO: CatalogResponseDAO): List<ProductDTO> {
        val list = mutableListOf<ProductDTO>()
        catalogResponseDAO.productDAO.map { product ->
            product?.let {
                list.add(map(it))
            }
        }
        return list
    }

    fun mapSelection(catalogResponseDAO: ProductSelectionDAO): ProductSelectionDTO {
        val list = mutableListOf<ProductDTO>()
        catalogResponseDAO.products.map { product ->
            product?.let {
                list.add(map(it))
            }
        }
        return ProductSelectionDTO(products = list)
    }
}