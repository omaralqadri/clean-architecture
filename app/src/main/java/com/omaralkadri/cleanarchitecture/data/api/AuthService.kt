package com.omaralkadri.cleanarchitecture.data.api

import com.omaralkadri.cleanarchitecture.data.model.BaseResponse
import com.omaralkadri.cleanarchitecture.data.model.request.LoginRequestEnvelope
import com.omaralkadri.cleanarchitecture.data.model.response.LoginResponseModel
import com.omaralkadri.cleanarchitecture.utils.Constants.Companion.BASE_URL
import retrofit2.http.*

interface AuthService {
    @POST(BASE_URL + "login")
    suspend fun login(@Body loginEnvelope: LoginRequestEnvelope): BaseResponse<LoginResponseModel>

    @GET(BASE_URL + "login/{provider}/callback")
    suspend fun loginSocial(
        @Path("provider") provider: String,
        @Query("token") token: String,
        @Query("access_token") isAccessToken: Boolean?,
    ): BaseResponse<LoginResponseModel>
}