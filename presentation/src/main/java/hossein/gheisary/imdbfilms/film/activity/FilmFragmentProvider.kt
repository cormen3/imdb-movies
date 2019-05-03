package hossein.gheisary.imdbfilms.film.activity

import dagger.Module
import dagger.android.ContributesAndroidInjector
import hossein.gheisary.imdbfilms.film.details.FilmDetailsFragment
import hossein.gheisary.imdbfilms.film.details.FilmDetailsModule
import hossein.gheisary.imdbfilms.film.list.FilmListFragment
import hossein.gheisary.imdbfilms.film.list.FilmListModule

@Module
abstract class FilmFragmentProvider {
    @ContributesAndroidInjector(modules = [FilmListModule::class])
    abstract fun provideFilmListFragment(): FilmListFragment


    @ContributesAndroidInjector(modules = [FilmDetailsModule::class])
    abstract fun provideFilmDetailsFragment(): FilmDetailsFragment

}