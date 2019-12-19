/*
 * *
 *  * Created by Ahlem Jarrar on 8/23/19 9:05 AM
 *  * Copyright (c) 2019 . All rights reserved.
 *  * Last modified 8/23/19 9:05 AM
 *
 */

package com.hb.endlesstrivia.data_source.remote

import com.hb.endlesstrivia.api.ApiService
import com.hb.endlesstrivia.data.RequestListTrivia
import com.hb.endlesstrivia.data.ResponseListTrivia
import com.hb.endlesstrivia.data.ResultApi
import com.hb.endlesstrivia.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class RemoteDataSourceImpl(
    private val api: ApiService,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) :
    RemoteDataSource {

    override suspend fun listTrivia(
        requestListTrivia: RequestListTrivia
    ): ResultApi<ResponseListTrivia> =
        withContext(ioDispatcher) {
            val request =
                api.getListTriviaAsync(
                    requestListTrivia.amount,
                    requestListTrivia.category,
                    requestListTrivia.difficulty,
                    requestListTrivia.type

                )
            ResultApi.Success(request.await())
        }

}