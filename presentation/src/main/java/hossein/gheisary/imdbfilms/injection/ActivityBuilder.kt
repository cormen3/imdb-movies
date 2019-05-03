package hossein.gheisary.imdbfilms.injection

import dagger.Module
import dagger.android.ContributesAndroidInjector
import hossein.gheisary.imdbfilms.film.activity.FilmActivity
import hossein.gheisary.imdbfilms.film.activity.FilmFragmentProvider
import hossein.gheisary.imdbfilms.film.activity.FilmModule

@Module
abstract class ActivityBuilder {
    @ContributesAndroidInjector(modules = [FilmModule::class, FilmFragmentProvider::class])
    abstract fun bindFilmActivity(): FilmActivity

}