package com.example.storeapp.di

import com.example.storeapp.data.base.CallExecutor
import com.example.storeapp.data.product.network.ProductNetworkDatasource
import com.example.storeapp.data.product.network.ProductService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    fun provideProductNetworkDatasource(retrofit: Retrofit, callExecutor: CallExecutor): ProductNetworkDatasource =
        ProductNetworkDatasource(callExecutor, retrofit.create(ProductService::class.java))
}