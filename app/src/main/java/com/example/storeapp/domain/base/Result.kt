package com.example.storeapp.domain.base

sealed class Result<out T, out E>

data class Success<out T>(val value: T) : Result<T, Nothing>()
data class Failure<out E>(val reason: E) : Result<Nothing, E>()


inline fun <T, Tʹ, E> Result<T, E>.map(f: (T) -> Tʹ): Result<Tʹ, E> =
    when (this) {
        is Success<T> -> Success(f(value))
        is Failure<E> -> this
    }

inline fun <T, E, Eʹ> Result<T, E>.mapFailure(f: (E) -> Eʹ): Result<T, Eʹ> =
    when (this) {
        is Success<T> -> this
        is Failure<E> -> Failure(f(reason))
    }


fun <T> Result<T, T>.get() = when (this) {
    is Success<T> -> value
    is Failure<T> -> reason
}

inline fun <T, E> Result<T, E>.then(f: () -> Unit) =
    apply { f() }