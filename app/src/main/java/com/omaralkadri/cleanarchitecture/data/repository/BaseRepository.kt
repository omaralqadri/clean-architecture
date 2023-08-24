package com.omaralkadri.cleanarchitecture.data.repository

import com.google.gson.Gson
import com.omaralkadri.cleanarchitecture.data.model.ErrorModel
import com.omaralkadri.cleanarchitecture.data.model.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException


abstract class BaseRepository {
    suspend fun <T> safeApiCall(
        apiCall: suspend () -> T
    ): Resource<T> {
        return withContext(Dispatchers.IO) {
            try {
                Resource.Success(apiCall.invoke())
            } catch (e: Throwable) {
                when (e) {
                    is HttpException -> {
                        val errorBody = e.response()?.errorBody()?.string()
                        val gson = Gson()
                        try {
                            try {
                                val errorModel = gson.fromJson(errorBody, ErrorModel::class.java)
                                if (errorModel.errors.isNotEmpty()) {
                                    if (errorModel.errors[0].detail.isEmpty())
                                        Resource.Failure(false, "Error",e.code())
                                    else Resource.Failure(false, errorModel.errors[0].detail,e.code())
                                } else {
                                    Resource.Failure(false, e.message.toString(),e.code())
                                }
                            } catch (error: Exception) {
                                Resource.Failure(false, e.message.toString(),e.code())
                            }
                        } catch (error: Exception) {
                            Resource.Failure(false, e.message.toString(),e.code())
                        }
                    }
                    else -> Resource.Failure(false, e.message)
                }
            }
        }
    }
}
