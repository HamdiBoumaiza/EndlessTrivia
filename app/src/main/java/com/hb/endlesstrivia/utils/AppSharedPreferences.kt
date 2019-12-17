package com.hb.endlesstrivia.utils

import android.content.Context
import android.content.SharedPreferences
import com.hb.endlesstrivia.BuildConfig


const val KEY_DEVICE_ID_PARENT = "device_id_parent"


class FinTuneSharedPreferences(context: Context) {

    private val prefs: SharedPreferences = context.getSharedPreferences(BuildConfig.APPLICATION_ID, 0)

    var deviceId_parent: String?
        get() = prefs.getString(KEY_DEVICE_ID_PARENT, "")
        set(value) = prefs.edit().putString(KEY_DEVICE_ID_PARENT, value).apply()

}