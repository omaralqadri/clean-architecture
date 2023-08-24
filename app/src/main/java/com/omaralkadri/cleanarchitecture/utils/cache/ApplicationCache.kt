package com.omaralkadri.cleanarchitecture.utils.cache

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.omaralkadri.cleanarchitecture.data.model.User
import com.omaralkadri.cleanarchitecture.data.model.response.LoginResponseModel
import javax.inject.Inject

class ApplicationCache @Inject constructor(context: Context, var gson: Gson) {
    private var sharedPreferences: SharedPreferences =
        context.getSharedPreferences(CacheKey.CACHE_KEY, Context.MODE_PRIVATE)

    fun setUserData(userData: LoginResponseModel) {
         setUser(userData.user)
         setUserToken(userData.token.accessToken)
    }

    fun setUserToken(userToken: String?) {
        if (userToken != null)
            sharedPreferences.edit().putString(CacheKey.USER_TOKEN, userToken).apply()
        else
            sharedPreferences.edit().putString(CacheKey.USER_TOKEN, "").apply()
    }

    fun getUserToken(): String? {
        return sharedPreferences.getString(CacheKey.USER_TOKEN, "")
    }

    fun setUser(userData: User) {
        sharedPreferences.edit().putString(CacheKey.USER_DATA, gson.toJson(userData)).apply()
    }

    fun getUser(): User {
        val gsonObject = sharedPreferences.getString(CacheKey.USER_DATA, null)
        return gson.fromJson(gsonObject, User::class.java)
    }

}