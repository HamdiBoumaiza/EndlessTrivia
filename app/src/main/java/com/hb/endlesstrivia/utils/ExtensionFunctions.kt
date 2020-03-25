package com.hb.endlesstrivia.utils

import android.content.Context
import android.view.View
import android.widget.Toast
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.snackbar.Snackbar


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


inline fun <reified VM : ViewModel> FragmentActivity.viewModelProvider(
    provider: ViewModelProvider.Factory
) =
    ViewModelProvider(this, provider).get(VM::class.java)


fun View.showSnackbar(snackbarText: String) {
    Snackbar.make(this, snackbarText, Snackbar.LENGTH_LONG).show()
}

fun ViewPager2.addPageChangeListener(position: (Int) -> Unit) {
    registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
        override fun onPageScrolled(
            pos: Int, positionOffset: Float, positionOffsetPixels: Int) {
            position(pos)
        }
        override fun onPageSelected(position: Int) {
        }

        override fun onPageScrollStateChanged(state: Int) {
        }
    })
}