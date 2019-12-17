package com.hb.endlesstrivia.ui.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel : ViewModel() {

    val toggleLoading = MutableLiveData<Boolean>()

    open fun onPause() {
        toggleLoading.value = false
    }


    open fun onStart() {
    }

    open fun onResume() {
    }

    open fun onStop() {
    }

    open fun onDestroy() {
    }

}