package com.omaralkadri.cleanarchitecture.data.model.response

import com.google.gson.annotations.SerializedName
import com.omaralkadri.cleanarchitecture.data.model.Token
import com.omaralkadri.cleanarchitecture.data.model.User


//region Auth Models
data class LoginResponseModel(
    @SerializedName("token")
    val token: Token,

    @SerializedName("user")
    val user: User
)
