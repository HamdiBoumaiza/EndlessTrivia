package com.hb.endlesstrivia.utils

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.GsonBuilder
import com.hb.endlesstrivia.BuildConfig
import javax.inject.Singleton


@Singleton
class AppSharedPreferences(context: Context) {
    private val gson = GsonBuilder().create()

    companion object {
        const val KEY_REQUEST_TRIVIA = "request_trivia"
    }


    private val prefs: Lazy<SharedPreferences> = lazy {
        context.applicationContext.getSharedPreferences(
            BuildConfig.APPLICATION_ID, Context.MODE_PRIVATE
        )
    }

    /**
     * Saves object into the Preferences.
     * Only the fields are stored. Methods, Inner classes, Nested classes and inner interfaces are not stored.
     **/
    fun <T> putObject(key: String, y: T) {
        val inString = gson.toJson(y)
        prefs.value.edit().putString(key, inString).apply()
    }

    /**
     * Saves collection of objects into the Preferences.
     * Only the fields are stored. Methods, Inner classes, Nested classes and inner interfaces are not stored.
     **/

    fun <T> getObject(key: String, c: Class<T>): T? {
        val value = prefs.value.getString(key, null)
        if (value != null) {
            return gson.fromJson(value, c)
        } else {
            throw IllegalArgumentException("No object with key: $key was saved")
        }
    }

}