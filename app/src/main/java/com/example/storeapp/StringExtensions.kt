package com.example.storeapp

import com.example.storeapp.domain.base.Error
import com.example.storeapp.domain.base.Failure
import com.example.storeapp.domain.base.Success
import com.google.gson.GsonBuilder

inline fun <reified T> String?.parseJSON() =
    try {
        this ?: Failure(Error("Data is empty"))
        val result = GsonBuilder().create().fromJson(
            this,
            T::class.java,
        )
        result?.let {
            Success(result)
        } ?: Failure(Error.UncompletedOperation("Data couldn't be read"))
    } catch (e: java.lang.Exception) {
        Failure(Error.UncompletedOperation("Data couldn't be read"))
    }