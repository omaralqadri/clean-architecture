package com.omaralkadri.cleanarchitecture.ui.login.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.omaralkadri.cleanarchitecture.data.model.BaseResponse
import com.omaralkadri.cleanarchitecture.data.model.Resource
import com.omaralkadri.cleanarchitecture.data.model.request.LoginRequestEnvelope
import com.omaralkadri.cleanarchitecture.data.model.response.LoginResponseModel
import com.omaralkadri.cleanarchitecture.data.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginVM @Inject constructor(private val repository: AuthRepository) : ViewModel() {
    val loginData: MutableLiveData<Resource<BaseResponse<LoginResponseModel>>> = MutableLiveData()

    fun login(loginEnvelope: LoginRequestEnvelope) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.login(loginEnvelope)
            loginData.postValue(response)
        }
    }
}