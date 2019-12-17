package com.hb.endlesstrivia.repository

import com.hb.endlesstrivia.data.RequestListTrivia
import com.hb.endlesstrivia.data.ResultApi
import com.hb.endlesstrivia.model.Trivia

interface AppRepository {

    suspend fun getListTriviaApi(requestListTrivia: RequestListTrivia): ResultApi<List<Trivia>>

    suspend fun getListTriviaDb(): List<Trivia>


}