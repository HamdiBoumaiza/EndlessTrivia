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
        return  tryResult { remoteDataSource.listTrivia(requestListTrivia).results}
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