package com.omaralkadri.cleanarchitecture.data.model

data class ErrorModel(val errors: ArrayList<ErrorItem>)

data class ErrorItem(val status: Int, val title: String, val detail: String)