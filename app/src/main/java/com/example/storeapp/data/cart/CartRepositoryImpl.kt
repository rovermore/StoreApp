package com.example.storeapp.data.cart

import com.example.storeapp.data.cart.local.ProductLocalDatasource
import com.example.storeapp.data.cart.model.ProductDTOMapper
import com.example.storeapp.data.product.model.ProductDAOMapper
import com.example.storeapp.domain.base.Error
import com.example.storeapp.domain.base.Result
import com.example.storeapp.domain.base.map
import com.example.storeapp.domain.cart.model.ProductSelectionDTO
import com.example.storeapp.domain.cart.repository.CartRepository
import javax.inject.Inject

class CartRepositoryImpl @Inject constructor(
    private val productLocalDatasource: ProductLocalDatasource,
    private val productDTOMapper: ProductDTOMapper,
    private val productDAOMapper: ProductDAOMapper
): CartRepository {

    override fun saveProducts(cart: ProductSelectionDTO): Result<Boolean, Error> {
        return productLocalDatasource.saveProducts(productDTOMapper.map(cart))
    }

    override fun getProducts(): Result<ProductSelectionDTO, Error> {
        return productLocalDatasource.retrieveProducts()
            .map {
                productDAOMapper.mapSelection(it)
            }
    }

    override fun clearProducts(): Result<Boolean, Error> {
        return productLocalDatasource.deleteProducts()
    }
}