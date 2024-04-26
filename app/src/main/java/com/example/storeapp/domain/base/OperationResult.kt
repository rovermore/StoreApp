package com.example.storeapp.domain.base

sealed class OperationResult<out T, out E>

data class Success<out T>(val value: T) : OperationResult<T, Nothing>()
data class Failure<out E>(val reason: E) : OperationResult<Nothing, E>()


inline fun <T, U, E> OperationResult<T, E>.map(f: (T) -> U): OperationResult<U, E> =
    flatMap { value -> Success(f(value)) }

inline fun <T, Tʹ, E> OperationResult<T, E>.flatMap(f: (T) -> OperationResult<Tʹ, E>): OperationResult<Tʹ, E> =
    when (this) {
        is Success<T> -> f(value)
        is Failure<E> -> this
    }

inline fun <T, E, Eʹ> OperationResult<T, E>.mapFailure(f: (E) -> Eʹ): OperationResult<T, Eʹ> =
    flatMapFailure { reason -> Failure(f(reason)) }

inline fun <T, E, Eʹ> OperationResult<T, E>.flatMapFailure(f: (E) -> OperationResult<T, Eʹ>): OperationResult<T, Eʹ> = when (this) {
    is Success<T> -> this
    is Failure<E> -> f(reason)
}


fun <T> OperationResult<T, T>.get() = when (this) {
    is Success<T> -> value
    is Failure<T> -> reason
}