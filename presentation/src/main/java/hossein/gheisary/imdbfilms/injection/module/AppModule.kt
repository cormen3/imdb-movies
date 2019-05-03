package hossein.gheisary.imdbfilms.injection.module

import android.app.Application
import android.content.Context
import dagger.Binds
import dagger.Module
import dagger.Provides
import hossein.gheisary.data.local.preference.PreferenceService
import io.reactivex.disposables.CompositeDisposable
import simra.androidtest.gheisary.twtest.injection.module.NetworkModule

@Module(includes = [NetworkModule::class, BaseModule::class, ViewModelModule::class])
abstract class AppModule {

    @Binds
    abstract fun provideContext(application: Application): Context

    @Module
    companion object {
        @JvmStatic
        @Provides
        internal fun providePreferenceService(application: Application): PreferenceService {
            return PreferenceService(application)
        }

        @JvmStatic
        @Provides
        internal fun provideCompositeDisposable(): CompositeDisposable = CompositeDisposable()
    }
}