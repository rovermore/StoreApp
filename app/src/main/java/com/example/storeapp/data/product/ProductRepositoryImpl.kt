package com.example.storeapp.data.product

import com.example.storeapp.data.base.APIErrorMapper
import com.example.storeapp.data.product.model.ProductDAOMapper
import com.example.storeapp.data.product.network.ProductNetworkDatasource
import com.example.storeapp.domain.base.Error
import com.example.storeapp.domain.base.Result
import com.example.storeapp.domain.base.map
import com.example.storeapp.domain.base.mapFailure
import com.example.storeapp.domain.product.model.ProductDTO
import com.example.storeapp.domain.product.repository.ProductRepository
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val productNetworkDatasource: ProductNetworkDatasource,
    private val apiErrorMapper: APIErrorMapper,
    private val productDAOMapper: ProductDAOMapper,
): ProductRepository {

    override fun getProductCatalog(): Result<List<ProductDTO>, Error> {
        return productNetworkDatasource.getProductsCatalog()
            .map {
                productDAOMapper.mapCatalog(it)
            }.mapFailure {
                apiErrorMapper.map(it)
            }
    }

}