package com.omaralkadri.cleanarchitecture.ui

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.ActivityInfo
import android.graphics.Rect
import android.os.Bundle
import android.view.MenuItem
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.omaralkadri.cleanarchitecture.data.model.Resource
import com.omaralkadri.cleanarchitecture.utils.cache.ApplicationCache
import javax.inject.Inject

abstract class BaseActivity<VB : ViewBinding> : AppCompatActivity() {

    // region variables
    lateinit var binding: VB

    @Inject
    lateinit var cache: ApplicationCache

    //endregion

    //region activity lifecycle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = getViewBinding()
        setContentView(binding.root)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_NOSENSOR
        currentContext = applicationContext
    }

    //endregion

    //region tools

    abstract fun getViewBinding(): VB

    fun intent(targetActivity: Class<*>) {
        val intent = Intent(this, targetActivity)
        startActivity(intent)
    }

    fun intent(targetActivity: Class<*>, bundle: Bundle) {
        val intent = Intent(this, targetActivity)
        intent.putExtras(bundle)
        startActivity(intent)
    }

    //endregion

    //region listeners

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackButtonPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun dispatchTouchEvent(event: MotionEvent): Boolean {
        if (event.action == MotionEvent.ACTION_DOWN) {
            val v = currentFocus
            if (v is EditText) {
                val outRect = Rect()
                v.getGlobalVisibleRect(outRect)
                if (!outRect.contains(event.rawX.toInt(), event.rawY.toInt())) {
                    v.clearFocus()
                    val imm: InputMethodManager =
                        getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0)
                }
            }
        }
        return super.dispatchTouchEvent(event)
    }

    private fun onBackButtonPressed() {
        if (supportFragmentManager.backStackEntryCount > 0) {
            supportFragmentManager.popBackStack()
        } else {
            super.onBackPressed()
        }
    }

    fun handleApiError(
        failure: Resource.Failure
    ) {
        when {
            failure.isNetworkError -> Toast.makeText(
                this,
                "Please check your internet connection", Toast.LENGTH_SHORT
            ).show()
            else -> {
                val error = failure.errorBody!!
                Toast.makeText(
                    this,
                    error, Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
    //endregion
}

@SuppressLint("StaticFieldLeak")
lateinit var currentContext: Context
