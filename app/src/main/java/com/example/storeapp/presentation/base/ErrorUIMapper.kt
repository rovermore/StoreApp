package com.example.storeapp.presentation.base

import javax.inject.Inject
import com.example.storeapp.domain.base.Error

class ErrorUIMapper @Inject constructor() {

    fun map(error: Error) = when (error) {
        is Error.ConnectionError -> ErrorUI.ConnectionError(error.message)
        is Error.UncompletedOperation -> ErrorUI.GenericError(error.message)
        is Error.OperationCompletedWithError -> ErrorUI.GenericError(error.message)
        is Error.Unauthorized -> ErrorUI.UnauthorizedError(error.message)
        is Error.Unmapped -> ErrorUI.UnmappedError(error.message)
    }
}