package com.hb.endlesstrivia.ui.filter_trivia

import androidx.lifecycle.ViewModel
import com.hb.endlesstrivia.data.RequestListTrivia
import com.hb.endlesstrivia.utils.*
import javax.inject.Inject

class FilterViewModel @Inject constructor(
    private val appSharedPreferences: AppSharedPreferences
) : ViewModel() {


    fun saveUserPreferences(requestListTrivia: RequestListTrivia) {
        appSharedPreferences.putObject(AppSharedPreferences.KEY_REQUEST_TRIVIA, requestListTrivia)
    }

    fun getUserPreferences(): RequestListTrivia? =
        appSharedPreferences.getObject(
            AppSharedPreferences.KEY_REQUEST_TRIVIA,
            RequestListTrivia::class.java
        )


    fun getListCategories(): List<Pair<String, String>> {
        return listOf(
            Pair("Any Category", TRIVIA_CATEGORY_ANY),
            Pair("General knowledge", TRIVIA_CATEGORY_GENERAL_KNOWLEDGE),
            Pair("Science & Nature", TRIVIA_CATEGORY_SCIENCE),
            Pair("Sports", TRIVIA_CATEGORY_SPORTS),
            Pair("Celebrities", TRIVIA_CATEGORY_CELEBRITIES),
            Pair("Animals", TRIVIA_CATEGORY_ANIMALS),
            Pair("Vehicles", TRIVIA_CATEGORY_VEHICLES)
        )
    }


    fun getListDifficulties(): List<String> {
        return listOf(TRIVIA_DIFFICULTY_EASY, TRIVIA_DIFFICULTY_MEDIUM, TRIVIA_DIFFICULTY_HARD)
    }

    fun getListTriviaNumber(): List<String> {
        return listOf(
            TRIVIA_NUMBER_10,
            TRIVIA_NUMBER_20,
            TRIVIA_NUMBER_30,
            TRIVIA_NUMBER_40,
            TRIVIA_NUMBER_50
        )
    }

    fun getListTypes(): List<Pair<String, String>> {
        return listOf(
            Pair("Multiple Choices", TRIVIA_TYPE_MULTIPLE),
            Pair("True/False", TRIVIA_TYPE_BOOLEAN)
        )
    }
}