package com.example.storeapp.data.product.network

import com.example.storeapp.data.product.model.CatalogResponse
import retrofit2.Call
import retrofit2.http.GET

interface ProductService {

    @GET("/Products.json")
    fun getProductsCatalog(): Call<CatalogResponse>
}