package hossein.gheisary.imdbfilms.injection.module

import dagger.Module
import hossein.gheisary.imdbfilms.film.details.FilmDetailsModule
import hossein.gheisary.imdbfilms.film.list.FilmListModule


@Module(includes = [ FilmListModule::class, FilmDetailsModule::class])
abstract class BaseModule