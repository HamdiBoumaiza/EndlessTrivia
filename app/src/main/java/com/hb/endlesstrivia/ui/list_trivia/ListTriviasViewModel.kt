package com.hb.endlesstrivia.ui.list_trivia

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hb.endlesstrivia.data.RequestListTrivia
import com.hb.endlesstrivia.data.ResultData
import com.hb.endlesstrivia.model.Trivia
import com.hb.endlesstrivia.repository.AppRepositoryImpl
import com.hb.endlesstrivia.utils.AppSharedPreferences
import kotlinx.coroutines.launch
import javax.inject.Inject

class ListTriviasViewModel @Inject constructor(
    private val repositoryImpl: AppRepositoryImpl,
    private val appSharedPreferences: AppSharedPreferences
) : ViewModel() {

    private var _resultListTrivia = MutableLiveData<ResultData<List<Trivia>>>()
    var resultListTrivia: LiveData<ResultData<List<Trivia>>> = _resultListTrivia


    fun getListOfTrivias(requestListTrivia: RequestListTrivia) {
        _resultListTrivia.value = (ResultData.Loading(true))
        viewModelScope.launch {
            _resultListTrivia.value = (repositoryImpl.getListTrivia(requestListTrivia))
            _resultListTrivia.value = (ResultData.Loading(false))
        }
    }

    fun getUserPreferences(): RequestListTrivia? =
        appSharedPreferences.getObject(
            AppSharedPreferences.KEY_REQUEST_TRIVIA,
            RequestListTrivia::class.java
        )
}















