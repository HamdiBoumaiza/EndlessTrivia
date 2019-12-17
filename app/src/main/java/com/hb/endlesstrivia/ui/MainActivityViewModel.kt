package com.hb.endlesstrivia.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.hb.endlesstrivia.data.RequestListTrivia
import com.hb.endlesstrivia.data.ResultApi
import com.hb.endlesstrivia.model.Trivia
import com.hb.endlesstrivia.repository.AppRepositoryImpl
import com.hb.endlesstrivia.ui.base.BaseViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainActivityViewModel @Inject constructor(
    private val repositoryImpl: AppRepositoryImpl
) : BaseViewModel() {

    private var _resultListTrivia = MutableLiveData<List<Trivia>>()
    var resultListTrivia: LiveData<List<Trivia>> = _resultListTrivia

    private var _errorMessage = MutableLiveData<String>()
    var errorMessage: LiveData<String> = _errorMessage

    fun getListOfTrivias(requestListTrivia: RequestListTrivia) {
        viewModelScope.launch {
            try {
                when (val response = repositoryImpl.getListTriviaApi(requestListTrivia)) {
                    is ResultApi.Success -> {
                        toggleLoading.postValue(false)
                        _resultListTrivia.postValue(response.data)
                    }
                    is ResultApi.Error -> {
                        toggleLoading.postValue(false)
                    }
                }
            } catch (e: Exception) {
                _errorMessage.postValue(e.message)
                toggleLoading.postValue(false)
            }
        }
    }

    fun getListOfTriviasDB(requestListTrivia: RequestListTrivia) {
        viewModelScope.launch {
            _resultListTrivia.postValue(repositoryImpl.getListTriviaDb())
        }
    }

}















