package com.hb.endlesstrivia.api

import com.hb.endlesstrivia.data.ResponseListTrivia
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET(LIST_TRIVIA_URL)
    suspend fun getListTriviaAsync(
        @Query("amount") amount: String,
        @Query("category") category: String?,
        @Query("difficulty") difficulty: String?,
        @Query("type") type: String?
    ): ResponseListTrivia

}