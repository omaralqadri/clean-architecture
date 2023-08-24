package com.omaralkadri.cleanarchitecture.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class User(
    @SerializedName("id")
    val id: Int,

    @SerializedName("birth_date")
    val birthdate: String?,

    @SerializedName("code")
    val code: String?,

    @SerializedName("country")
    val country: String?,

    @SerializedName("email")
    val email: String?,

    @SerializedName("first_name")
    val firstName: String?,

    @SerializedName("gander")
    val gander: String?,

    @SerializedName("is_active")
    val isActive: Boolean?,

    @SerializedName("is_female_verified")
    val isFemaleVerified: Boolean?,

    @SerializedName("last_name")
    val lastName: String?,

    @SerializedName("phone_number")
    val phoneNumber: String?,

    @SerializedName("profile_photo")
    val profilePhoto: String?,

    @SerializedName("user_name")
    val userName: String?,
) : Serializable