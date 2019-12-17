package com.hb.endlesstrivia.utils

import android.app.Activity
import android.view.inputmethod.InputMethodManager

class KeyboardUtil{
    companion object{

        /**
         * Helper to hide the keyboard
         *
         * @param activity
         */
        fun hideKeyboard(activity: Activity?) {
            if (activity != null && activity.currentFocus != null) {
                val inputMethodManager =
                    activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
                inputMethodManager.hideSoftInputFromWindow(activity.currentFocus!!.windowToken, 0)
            }
        }

    }
}