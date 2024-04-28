package com.example.storeapp.di

import com.example.storeapp.data.cart.CartRepositoryImpl
import com.example.storeapp.data.product.ProductRepositoryImpl
import com.example.storeapp.domain.cart.repository.CartRepository
import com.example.storeapp.domain.product.repository.ProductRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class RepositoryModule {

    @Binds
    abstract fun bindProductRepository(impl: ProductRepositoryImpl): ProductRepository

    @Binds
    abstract fun bindCartRepository(impl: CartRepositoryImpl): CartRepository
}