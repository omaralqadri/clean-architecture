package com.omaralkadri.cleanarchitecture.ui.login.view

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.omaralkadri.cleanarchitecture.data.model.BaseResponse
import com.omaralkadri.cleanarchitecture.data.model.Resource
import com.omaralkadri.cleanarchitecture.data.model.request.LoginRequestEnvelope
import com.omaralkadri.cleanarchitecture.data.model.response.LoginResponseModel
import com.omaralkadri.cleanarchitecture.databinding.ActivityLoginBinding
import com.omaralkadri.cleanarchitecture.ui.BaseActivity
import com.omaralkadri.cleanarchitecture.ui.login.viewmodel.LoginVM
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : BaseActivity<ActivityLoginBinding>() {

    //region variables
    private lateinit var loginVM: LoginVM

    //endregion


    //region lifecycle
    override fun getViewBinding() = ActivityLoginBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    override fun onStart() {
        super.onStart()
        setObservers()
    }

    override fun onStop() {
        super.onStop()
        removeObservers()
    }
    //endregion

    //region init
    private fun init() {
        loginVM = ViewModelProvider(this)[LoginVM::class.java]
    }

    //endregion

    //tool region
    private fun login(loginRequestEnvelope: LoginRequestEnvelope) {
        loginVM.login(loginRequestEnvelope)
    }

    //endregion

    //region listeners
    private fun setObservers() {
        loginVM.loginData.observe(this, loginDataObserver)
    }

    private fun removeObservers() {
        loginVM.loginData.removeObserver(loginDataObserver)
    }

    //endregion

    //region apply data
    private val loginDataObserver =
        Observer<Resource<BaseResponse<LoginResponseModel>>> { response ->
            response?.let { responseBody ->
                when (responseBody) {
                    is Resource.Success -> {
                        cache.setUserData(responseBody.value.data)
                       finish()
                    }
                    is Resource.Failure -> {
                        handleApiError(responseBody)
                    }
                }
                loginVM.loginData.value = null
            }
        }
    //endregion
}