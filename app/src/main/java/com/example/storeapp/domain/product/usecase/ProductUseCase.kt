package com.example.storeapp.domain.product.usecase

import com.example.storeapp.domain.base.Error
import com.example.storeapp.domain.base.Result
import com.example.storeapp.domain.product.model.ProductDTO
import com.example.storeapp.domain.product.repository.ProductRepository
import javax.inject.Inject

class ProductUseCase @Inject constructor(
    private val productRepository: ProductRepository
) {

    fun getProductsCatalog(): Result<List<ProductDTO>, Error> {
        return productRepository.getProductCatalog()
    }
}