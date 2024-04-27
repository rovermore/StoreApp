package com.example.storeapp.domain.product.repository

import com.example.storeapp.domain.base.Error
import com.example.storeapp.domain.base.Result
import com.example.storeapp.domain.product.model.ProductDTO

interface ProductRepository {

    fun getProductCatalog(): Result<List<ProductDTO>, Error>
}