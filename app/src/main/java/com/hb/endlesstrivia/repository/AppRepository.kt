package com.hb.endlesstrivia.repository

import com.hb.endlesstrivia.data.RequestListTrivia
import com.hb.endlesstrivia.data.ResultData
import com.hb.endlesstrivia.model.Trivia

interface AppRepository {

    suspend fun getListTriviaApi(requestListTrivia: RequestListTrivia): ResultData<List<Trivia>>

    suspend fun getListTriviaDb(): ResultData<List<Trivia>>

    suspend fun getListTrivia(requestListTrivia: RequestListTrivia): ResultData<List<Trivia>>


}