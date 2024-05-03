package com.example.storeapp.data.product.network

import com.example.storeapp.data.product.model.CatalogResponseDAO
import retrofit2.Call
import retrofit2.http.GET

interface ProductService {

    @GET("palcalde/6c19259bd32dd6aafa327fa557859c2f/raw/ba51779474a150ee4367cda4f4ffacdcca479887/Products.json")
    fun getProductsCatalog(): Call<CatalogResponseDAO>
}