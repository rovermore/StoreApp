package com.example.storeapp.data.product

import com.example.storeapp.data.base.APIErrorMapper
import com.example.storeapp.data.cart.local.ProductLocalDatasource
import com.example.storeapp.data.product.model.ProductResponseMapper
import com.example.storeapp.data.product.network.ProductNetworkDatasource
import com.example.storeapp.domain.base.Error
import com.example.storeapp.domain.base.Failure
import com.example.storeapp.domain.base.Result
import com.example.storeapp.domain.base.Success
import com.example.storeapp.domain.base.map
import com.example.storeapp.domain.base.mapFailure
import com.example.storeapp.domain.product.model.ProductDTO
import com.example.storeapp.domain.product.repository.ProductRepository
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val productNetworkDatasource: ProductNetworkDatasource,
    private val apiErrorMapper: APIErrorMapper,
    private val productResponseMapper: ProductResponseMapper,
): ProductRepository {

    override fun getProductCatalog(): Result<List<ProductDTO>, Error> {
        return productNetworkDatasource.getProductsCatalog()
            .map {
                return Success(productResponseMapper.mapList(it))
            }.mapFailure {
                return Failure(apiErrorMapper.map(it))
            }
    }

}