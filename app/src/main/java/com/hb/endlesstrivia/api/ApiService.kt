package com.hb.endlesstrivia.api

import com.hb.endlesstrivia.data.ResponseListTrivia
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET(BASE_URL)
    suspend fun getListTrivia(
        @Query("amount") amount: String,
        @Query("category") category: String,
        @Query("difficulty") difficulty: String,
        @Query("type") type: String
    ): Deferred<ResponseListTrivia>

}