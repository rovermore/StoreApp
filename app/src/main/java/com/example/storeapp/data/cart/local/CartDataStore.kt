package com.example.storeapp.data.cart.local

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.floatPreferencesKey
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.storeapp.domain.base.Error
import com.example.storeapp.domain.base.Failure
import com.example.storeapp.domain.base.Result
import com.example.storeapp.domain.base.Success
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking
import javax.inject.Inject
import javax.inject.Singleton

private val Context.dataStore by preferencesDataStore(
    name = "CART_DATA_STORE"
)

@Singleton
class CartDataStore @Inject constructor(context: Context) {

    private val productDataStore = context.dataStore

    @Suppress("NON_PUBLIC_CALL_FROM_PUBLIC_INLINE")
    inline fun <reified T> save(key: String, value: T): Result<Boolean, Error> {
        return try {
            val keyString = getKey<T>(key)
            runBlocking {
                productDataStore.edit { productData ->
                    productData[keyString] = value
                }
            }
            Success(true)
        } catch (e: InterruptedException) {
            Failure(Error.UncompletedOperation(e.message ?: "Error saving data"))
        } catch (e: ClassNotFoundException) {
            Failure(Error.UncompletedOperation(e.message ?: "Error saving data"))
        }
    }

    @Suppress("NON_PUBLIC_CALL_FROM_PUBLIC_INLINE")
    inline fun <reified T> read(key: String): Result<T, Error> {
        return try {
            val keyString = getKey<T>(key)
            val value = runBlocking {
                productDataStore.data.map { productData ->
                    productData[keyString]
                }.firstOrNull()
            }
            if (value == null)
                Failure(Error.UncompletedOperation("Data couldn't be read"))
            else
                Success(value)
        } catch (e: InterruptedException) {
            Failure(Error.UncompletedOperation(e.message ?: "Error reading data"))
        } catch (e: ClassNotFoundException) {
            Failure(Error.UncompletedOperation(e.message ?: "Error reading data"))
        }
    }

    @Suppress("NON_PUBLIC_CALL_FROM_PUBLIC_INLINE")
    inline fun <reified T> update(key: String, value: T): Result<Boolean, Error> {
        return try {
            val keyString = getKey<T>(key)
            runBlocking {
                productDataStore.edit { productData ->
                    productData[keyString] = value
                }
            }
            Success(true)
        } catch (e: InterruptedException) {
            Failure(Error.UncompletedOperation(e.message ?: "Error saving data"))
        } catch (e: ClassNotFoundException) {
            Failure(Error.UncompletedOperation(e.message ?: "Error saving data"))
        }
    }

    @Suppress("NON_PUBLIC_CALL_FROM_PUBLIC_INLINE")
    inline fun <reified T> delete(key: String): Result<Boolean, Error> {
        return try {
            val keyString = getKey<T>(key)
            runBlocking {
                productDataStore.edit { productData ->
                    productData.remove(keyString)
                }
            }
            Success(true)
        } catch (e: InterruptedException) {
            Failure(Error.UncompletedOperation(e.message ?: "Error deleting data"))
        } catch (e: ClassNotFoundException) {
            Failure(Error.UncompletedOperation(e.message ?: "Error deleting data"))
        }
    }

    @Throws(ClassNotFoundException::class)
    inline fun <reified T> getKey(key: String): Preferences.Key<T> {
        return when (T::class) {
            String::class -> stringPreferencesKey(key) as Preferences.Key<T>
            Boolean::class -> booleanPreferencesKey(key) as Preferences.Key<T>
            Float::class -> floatPreferencesKey(key) as Preferences.Key<T>
            Long::class -> longPreferencesKey(key) as Preferences.Key<T>
            else -> throw ClassNotFoundException()
        }
    }

}