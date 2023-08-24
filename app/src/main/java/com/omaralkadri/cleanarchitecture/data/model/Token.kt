package com.omaralkadri.cleanarchitecture.data.model

import com.google.gson.annotations.SerializedName

data class Token(
    @SerializedName("access_token")
    val accessToken: String ,

    @SerializedName("token_type")
    val tokenType: String,
)
