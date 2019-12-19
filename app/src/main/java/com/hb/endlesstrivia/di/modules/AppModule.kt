package com.hb.endlesstrivia.di.modules

import android.app.Application
import android.content.Context
import com.hb.endlesstrivia.di.viewmodels.ViewModelModule
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module(includes = [ViewModelModule::class])
class AppModule(private val application: Application) {

    @Provides
    @Singleton
    fun providesContext(): Context {
        return application
    }

}