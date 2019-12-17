package com.hb.endlesstrivia.ui.base


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.hb.endlesstrivia.R
import com.hb.endlesstrivia.utils.KeyboardUtil


abstract class BaseActivity : AppCompatActivity() {

    private lateinit var container: ViewGroup
    private lateinit var loading: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initLoading()
    }

    private fun initLoading() {
        container = findViewById<View>(android.R.id.content) as ViewGroup
        loading = LayoutInflater.from(this).inflate(R.layout.layout_loading, container, false)
        loading.setOnTouchListener { _, _ -> true }
    }

    fun toggleLoading(show: Boolean) {
        if (!isDestroyed) {
            container.removeView(loading)
            if (show) {
                container.addView(loading)
            }
        }
    }

}