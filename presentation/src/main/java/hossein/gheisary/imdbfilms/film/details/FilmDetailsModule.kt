package hossein.gheisary.imdbfilms.film.details

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import hossein.gheisary.data.local.preference.PreferenceService
import hossein.gheisary.data.remote.core.RestDataSource
import hossein.gheisary.data.remote.core.Scheduler
import hossein.gheisary.data.remote.exception.ExceptionHandler
import hossein.gheisary.imdbfilms.injection.ViewModelKey
import io.reactivex.disposables.CompositeDisposable
import hossein.gheisary.imdbfilms.film.details.model.FilmDetailsDataStore
import hossein.gheisary.imdbfilms.film.details.model.FilmDetailsLocalData
import hossein.gheisary.imdbfilms.film.details.model.FilmDetailsRemote
import hossein.gheisary.imdbfilms.film.details.model.FilmDetailsRepository

@Module
abstract class FilmDetailsModule {
    @Binds
    abstract fun provideFilmDetailsFragment(filmDetailsFragment: FilmDetailsFragment): FilmDetailsFragment

    @Binds
    @IntoMap
    @ViewModelKey(FilmDetailsViewModel::class)
    abstract fun bindFilmDetailsViewModel(filmDetailsViewModel: FilmDetailsViewModel): ViewModel

    @Module
    companion object {
        @JvmStatic
        @Provides
        internal fun provideFilmDetailsLocalData(preferenceService: PreferenceService): FilmDetailsDataStore.Local {
            return FilmDetailsLocalData(preferenceService)
        }

        @JvmStatic
        @Provides
        internal fun provideFilmDetailsRemote(restDataSource: RestDataSource): FilmDetailsDataStore.Remote {
            return FilmDetailsRemote(restDataSource)
        }

        @JvmStatic
        @Provides
        internal fun provideFilmDetailsRepository(local: FilmDetailsDataStore.Local,
                                                  remote: FilmDetailsDataStore.Remote,
                                                  scheduler: Scheduler,
                                                  exceptionHandler: ExceptionHandler,
                                                  compositeDisposable: CompositeDisposable
        ): FilmDetailsDataStore.Repository {
            return FilmDetailsRepository(local, remote, scheduler, exceptionHandler,compositeDisposable)
        }
    }
}