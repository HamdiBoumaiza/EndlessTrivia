package com.hb.endlesstrivia.utils

import android.content.Context
import android.view.View
import android.widget.Toast
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders


fun Context.toast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}

fun View.show() {
    visibility = View.VISIBLE
}

fun View.hide() {
    visibility = View.GONE
}

@ColorInt
internal fun View.color(@ColorRes attribute: Int): Int = ContextCompat.getColor(context, attribute)

/**
 * For Actvities, allows declarations like
 * ```
 * val myViewModel = viewModelProvider(myViewModelFactory)
 * ```
 */
inline fun <reified VM : ViewModel> FragmentActivity.viewModelProvider(
    provider: ViewModelProvider.Factory
) =
    ViewModelProviders.of(this, provider).get(VM::class.java)