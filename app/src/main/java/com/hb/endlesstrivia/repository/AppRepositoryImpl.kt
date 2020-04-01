package com.hb.endlesstrivia.repository

import com.hb.endlesstrivia.data.RequestListTrivia
import com.hb.endlesstrivia.data.ResultData
import com.hb.endlesstrivia.data.network.tryResult
import com.hb.endlesstrivia.data_source.local.AppDao
import com.hb.endlesstrivia.data_source.remote.RemoteDataSource
import com.hb.endlesstrivia.di.IoDispatcher
import com.hb.endlesstrivia.model.Trivia
import com.hb.endlesstrivia.utils.InternetUtil
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class AppRepositoryImpl(
    private val remoteDataSource: RemoteDataSource,
    private val appDao: AppDao,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : AppRepository {

    private val isInternetOn = InternetUtil.isInternetOn()

    override suspend fun getListTriviaApi(
        requestListTrivia: RequestListTrivia
    ): ResultData<List<Trivia>> {
        return when (val result = tryResult {
            remoteDataSource.listTrivia(requestListTrivia)
        }) {
            is ResultData.Success -> {
                val response = result.data.results
                withContext(ioDispatcher) { appDao.setListTrivias(response) }
                ResultData.Success(response)
            }
            is ResultData.Error -> {
                ResultData.Error(result.exception)
            }
        }
    }

    override suspend fun getListTriviaDb(): ResultData<List<Trivia>> =
        withContext(ioDispatcher) {
            ResultData.Success(appDao.getListTrivias())
        }

    override suspend fun getListTrivia(requestListTrivia: RequestListTrivia): ResultData<List<Trivia>> {
        return if (isInternetOn) {
            getListTriviaApi(requestListTrivia)
        } else {
            getListTriviaDb()
        }
    }


}