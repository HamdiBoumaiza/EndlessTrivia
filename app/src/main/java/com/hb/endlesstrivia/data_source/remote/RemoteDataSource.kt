package com.hb.endlesstrivia.data_source.remote

import com.hb.endlesstrivia.data.RequestListTrivia
import com.hb.endlesstrivia.data.ResponseListTrivia
import com.hb.endlesstrivia.data.ResultApi

interface RemoteDataSource {

    suspend fun listTrivia(requestListTrivia: RequestListTrivia): ResultApi<ResponseListTrivia>

}