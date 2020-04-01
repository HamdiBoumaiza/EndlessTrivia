package com.hb.endlesstrivia.data

import com.hb.endlesstrivia.data.network.DataSourceException

sealed class ResultData<out T> {
    data class Success<out T>(val data: T) : ResultData<T>()
    data class Error<out T>(val exception: DataSourceException) : ResultData<T>()
}