package com.omaralkadri.cleanarchitecture.data.model

data class BaseModel<T>(
    val data: T?,
    val message: Any,
    val meta: Meta?
)