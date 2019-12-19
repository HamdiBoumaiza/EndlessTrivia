package com.hb.endlesstrivia.repository

import com.hb.endlesstrivia.data.RemoteDataNotFoundException
import com.hb.endlesstrivia.data.RequestListTrivia
import com.hb.endlesstrivia.data.ResultApi
import com.hb.endlesstrivia.data_source.local.AppDao
import com.hb.endlesstrivia.data_source.remote.RemoteDataSource
import com.hb.endlesstrivia.di.IoDispatcher
import com.hb.endlesstrivia.model.Trivia
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class AppRepositoryImpl(
    private val remoteDataSource: RemoteDataSource,
    private val appDao: AppDao,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : AppRepository {

    override suspend fun getListTriviaApi(
        requestListTrivia: RequestListTrivia
    ): ResultApi<List<Trivia>> {
        return when (val result = remoteDataSource.listTrivia(requestListTrivia)) {
            is ResultApi.Success -> {
                val response = result.data.results
                withContext(ioDispatcher) { appDao.setListTrivias(response) }
                ResultApi.Success(response)
            }
            is ResultApi.Error -> {
                ResultApi.Error(RemoteDataNotFoundException())
            }
        }
    }

    override suspend fun getListTriviaDb(): List<Trivia> =
        withContext(ioDispatcher) {
            appDao.getListTrivias()
        }


}