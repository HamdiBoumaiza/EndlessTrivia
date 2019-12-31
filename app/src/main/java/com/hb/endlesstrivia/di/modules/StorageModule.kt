package com.hb.endlesstrivia.di.modules

import android.app.Application
import androidx.room.Room
import com.hb.endlesstrivia.data_source.local.AppDao
import com.hb.endlesstrivia.data_source.local.AppDb
import com.hb.endlesstrivia.utils.AppSharedPreferences
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class StorageModule(private val application: Application) {

    private var appDb: AppDb =
        Room.databaseBuilder(application, AppDb::class.java, "demo-db").build()

    @Singleton
    @Provides
    fun providesRoomDatabase(): AppDb {
        return appDb
    }

    @Singleton
    @Provides
    fun providesAppDao(demoDatabase: AppDb): AppDao {
        return demoDatabase.appDao()
    }


    @Singleton
    @Provides
    fun provideSharedPreferences(): AppSharedPreferences = AppSharedPreferences(application)


}
