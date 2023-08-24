package com.omaralkadri.cleanarchitecture.data.model

sealed class Resource<out T> {
    data class Success<T>(val value: T) : Resource<T>()
    data class Failure(
        val isNetworkError: Boolean,
        val errorBody: String?,
        val statusCode: Int? = null
    ) : Resource<Nothing>()
}
