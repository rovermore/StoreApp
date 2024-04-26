package com.example.storeapp.domain.base

sealed class Error(val message: String) {
    class UncompletedOperation(message: String = "") : Error(message)
    class OperationCompletedWithError(message: String = ""): Error(message)
    class Unauthorized(message: String = ""): Error(message)
    class Unmapped(message: String = "", ): Error(message)
    class ConnectionError(message: String = ""): Error(message)

}