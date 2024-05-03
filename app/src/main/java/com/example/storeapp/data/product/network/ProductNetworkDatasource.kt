package com.example.storeapp.data.product.network

import com.example.storeapp.data.base.APIError
import com.example.storeapp.data.base.CallExecutor
import com.example.storeapp.data.product.model.CatalogResponseDAO
import com.example.storeapp.domain.base.Result

class ProductNetworkDatasource(
    private val callExecutor: CallExecutor,
    private val service: ProductService
) {

    fun getProductsCatalog(): Result<CatalogResponseDAO, APIError> {
        return callExecutor.executeCall(service.getProductsCatalog())
    }
}