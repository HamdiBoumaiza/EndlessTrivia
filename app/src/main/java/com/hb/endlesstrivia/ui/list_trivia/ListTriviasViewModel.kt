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

    private var _resultListTrivia = MutableLiveData<List<Trivia>>()
    var resultListTrivia: LiveData<List<Trivia>> = _resultListTrivia

    private var _errorMessage = MutableLiveData<Int>()
    var errorMessage: LiveData<Int> = _errorMessage

    private var _showLoading = MutableLiveData<Boolean>()
    var showLoading: LiveData<Boolean> = _showLoading

    fun getListOfTrivias(requestListTrivia: RequestListTrivia) {
        _showLoading.postValue(true)
        viewModelScope.launch {
            when (val response = repositoryImpl.getListTrivia(requestListTrivia)) {
                is ResultData.Success -> {
                    _showLoading.postValue(false)
                    _resultListTrivia.postValue(response.data)
                }
                is ResultData.Error -> {
                    _showLoading.postValue(false)
                    _errorMessage.postValue(response.exception.messageResource)
                }
            }
        }
    }

    fun getUserPreferences(): RequestListTrivia? =
        appSharedPreferences.getObject(
            AppSharedPreferences.KEY_REQUEST_TRIVIA,
            RequestListTrivia::class.java
        )
}















