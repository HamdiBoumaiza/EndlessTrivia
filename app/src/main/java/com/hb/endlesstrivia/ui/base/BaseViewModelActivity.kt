package com.hb.endlesstrivia.ui.base

import android.os.Bundle
import androidx.lifecycle.Observer

abstract class BaseViewModelActivity<out T : BaseViewModel> : BaseActivity() {

    protected abstract fun getViewModel(): T?

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initObservers()
    }

    private fun initObservers() {
        getViewModel()?.toggleLoading?.observe(this, Observer { toggleLoading(it!!) })
    }

}