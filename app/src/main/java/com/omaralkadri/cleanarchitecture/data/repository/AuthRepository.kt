package com.omaralkadri.cleanarchitecture.data.repository

import com.omaralkadri.cleanarchitecture.data.api.AuthService
import com.omaralkadri.cleanarchitecture.data.model.request.LoginRequestEnvelope
import javax.inject.Inject

class AuthRepository @Inject constructor(private val authService: AuthService) : BaseRepository() {

    suspend fun login(loginEnvelope: LoginRequestEnvelope) = safeApiCall {
        authService.login(loginEnvelope)
    }
}
