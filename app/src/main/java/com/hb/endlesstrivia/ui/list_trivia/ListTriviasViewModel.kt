package com.hb.endlesstrivia.ui.list_trivia

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hb.endlesstrivia.data.RequestListTrivia
import com.hb.endlesstrivia.data.ResultData
import com.hb.endlesstrivia.model.Trivia
import com.hb.endlesstrivia.repository.AppRepositoryImpl
import kotlinx.coroutines.launch
import javax.inject.Inject

class ListTriviasViewModel @Inject constructor(
    private val repositoryImpl: AppRepositoryImpl
) : ViewModel() {

    private var _resultListTrivia = MutableLiveData<List<Trivia>>()
    var resultListTrivia: LiveData<List<Trivia>> = _resultListTrivia

    private var _errorMessage = MutableLiveData<String>()
    var errorMessage: LiveData<String> = _errorMessage

    fun getListOfTrivias(requestListTrivia: RequestListTrivia) {
        viewModelScope.launch {
            try {
                when (val response = repositoryImpl.getListTrivia(requestListTrivia)) {
                    is ResultData.Success -> {
                        _resultListTrivia.postValue(response.data)
                    }
                    is ResultData.Error -> {
                        _errorMessage.postValue("")
                    }
                }
            } catch (e: Exception) {
                _errorMessage.postValue(e.message)
            }
        }
    }

}















