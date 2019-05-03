package hossein.gheisary.imdbfilms.film.list

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import hossein.gheisary.data.local.preference.PreferenceService
import hossein.gheisary.data.remote.core.RestDataSource
import hossein.gheisary.data.remote.core.Scheduler
import hossein.gheisary.data.remote.exception.ExceptionHandler
import hossein.gheisary.imdbfilms.film.list.FilmListFragment
import hossein.gheisary.imdbfilms.film.list.model.FilmListDataStore
import hossein.gheisary.imdbfilms.film.list.model.FilmListLocalData
import hossein.gheisary.imdbfilms.film.list.model.FilmListRemote
import hossein.gheisary.imdbfilms.film.list.model.FilmListRepository
import hossein.gheisary.imdbfilms.injection.ViewModelKey
import io.reactivex.disposables.CompositeDisposable

@Module
abstract class FilmListModule {
    @Binds
    abstract fun provideFilmListFragment(filmListFragment: FilmListFragment): FilmListFragment

    @Binds
    @IntoMap
    @ViewModelKey(FilmListViewModel::class)
    abstract fun bindFilmListViewModel(filmListViewModel: FilmListViewModel): ViewModel

    @Module
    companion object {
        @JvmStatic
        @Provides
        internal fun provideFilmListLocalData(preferenceService: PreferenceService): FilmListDataStore.Local {
            return FilmListLocalData(preferenceService)
        }

        @JvmStatic
        @Provides
        internal fun provideFilmListRemote(restDataSource: RestDataSource): FilmListDataStore.Remote {
            return FilmListRemote(restDataSource)
        }

        @JvmStatic
        @Provides
        internal fun provideFilmListRepository(local: FilmListDataStore.Local,
                                                  remote: FilmListDataStore.Remote,
                                                  scheduler: Scheduler,
                                                  exceptionHandler: ExceptionHandler,
                                                  compositeDisposable: CompositeDisposable
        ): FilmListDataStore.Repository {
            return FilmListRepository(local, remote, scheduler, exceptionHandler,compositeDisposable)
        }
    }
}