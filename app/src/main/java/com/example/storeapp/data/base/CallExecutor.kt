package com.example.storeapp.data.base

import com.example.storeapp.domain.base.Failure
import com.example.storeapp.domain.base.Result
import com.example.storeapp.domain.base.Success
import retrofit2.Call
import javax.inject.Inject

class CallExecutor @Inject constructor(
    private val networkExceptionsMapper: NetworkExceptionsMapper
) {

    fun <T> executeCall(call: Call<T>): Result<T, APIError> {
        return try {
            val response = call.execute()
            if (response.isSuccessful && response.body() != null) {
                Success(response.body()!!)
            } else {
                val errorBody = response.errorBody()?.string() ?: ""
                Failure(networkExceptionsMapper.mapNetworkException(
                    response.code(),
                    errorBody))
            }
        } catch (e: Exception) {
            Failure(networkExceptionsMapper.mapException(e))
        }
    }
}