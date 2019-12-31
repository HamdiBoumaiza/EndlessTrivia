package com.hb.endlesstrivia.ui.filter_trivia

import androidx.lifecycle.ViewModel
import com.hb.endlesstrivia.data.RequestListTrivia
import com.hb.endlesstrivia.utils.AppSharedPreferences
import javax.inject.Inject

class FilterViewModel @Inject constructor(
    private val appSharedPreferences: AppSharedPreferences
) : ViewModel() {


    fun saveUserPrefrences(requestListTrivia: RequestListTrivia) {
        appSharedPreferences.putObject(AppSharedPreferences.KEY_REQUEST_TRIVIA, requestListTrivia)
    }

    fun getUserPrefrences(): RequestListTrivia? =
        appSharedPreferences.getObject(
            AppSharedPreferences.KEY_REQUEST_TRIVIA,
            RequestListTrivia::class.java
        )


    fun getListCategories(): List<Pair<String, String?>> {
        return listOf(
            Pair("Any Category", null),
            Pair("General knowledge", "9"),
            Pair("Sports", "21"),
            Pair("Celebrities", "26"),
            Pair("Science & Nature", "17"),
            Pair("Animals", "27"),
            Pair("Vehicles", "28")
        )
    }

    fun getListDifficulties(): List<String> {
        return listOf("Easy", "Medium", "Hard")
    }

    fun getListAmounts(): List<String> {
        return listOf("10", "20", "30", "40", "50")
    }

    fun getListTypes(): List<Pair<String, String>> {
        return listOf(Pair("Multiple Choices", "multiple"), Pair("True/False", "boolean"))
    }
}