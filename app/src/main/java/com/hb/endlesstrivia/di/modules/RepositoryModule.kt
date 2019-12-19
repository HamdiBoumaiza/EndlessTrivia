package com.hb.endlesstrivia.di.modules

import com.hb.endlesstrivia.api.ApiService
import com.hb.endlesstrivia.data_source.local.AppDao
import com.hb.endlesstrivia.data_source.remote.RemoteDataSourceImpl
import com.hb.endlesstrivia.di.IoDispatcher
import com.hb.endlesstrivia.repository.AppRepositoryImpl
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

@Module
class RepositoryModule {


    @Provides
    @Singleton
    fun provideAppRepository(
        @IoDispatcher ioDispatcher: CoroutineDispatcher,
        api: ApiService,
        appDao: AppDao
    ): AppRepositoryImpl {
        val userDataSource = RemoteDataSourceImpl(api, ioDispatcher)
        return AppRepositoryImpl(userDataSource, appDao, ioDispatcher)
    }
}