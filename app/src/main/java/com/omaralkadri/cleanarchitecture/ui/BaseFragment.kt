package com.omaralkadri.cleanarchitecture.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.omaralkadri.cleanarchitecture.utils.cache.ApplicationCache
import javax.inject.Inject

abstract class BaseFragment<VB : ViewBinding> : Fragment() {

    // region variables
    lateinit var binding: VB

    @Inject
    lateinit var cache: ApplicationCache

    //endregion

    //region fragment lifecycle
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = getViewBinding()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        currentContext = requireContext()
    }

    //endregion

    //region tools

    abstract fun getViewBinding(): VB

    fun intent(targetActivity: Class<*>) {
        val intent = Intent(requireContext(), targetActivity)
        startActivity(intent)
    }

    //endregion
}
