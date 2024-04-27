package com.example.storeapp.data.product.network

import com.example.storeapp.data.base.APIError
import com.example.storeapp.data.base.CallExecutor
import com.example.storeapp.data.product.model.CatalogResponse
import com.example.storeapp.domain.base.Result

class ProductNetworkDatasource(
    private val callExecutor: CallExecutor,
    private val service: ProductService
) {

    fun getProductsCatalog(): Result<CatalogResponse, APIError> {
        return callExecutor.executeCall(service.getProductsCatalog())
    }
}