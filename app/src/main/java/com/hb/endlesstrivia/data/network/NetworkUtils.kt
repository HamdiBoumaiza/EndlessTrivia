package com.hb.endlesstrivia.data.network

import com.hb.endlesstrivia.data.ResultData


suspend fun <T> tryResult(call: suspend () -> T): ResultData<T> {
    return try {
        ResultData.Success(data = call())
    } catch (e: Exception) {
        ResultData.Error(
            RequestErrorHandler.getRequestError(
                e
            )
        )
    }
}