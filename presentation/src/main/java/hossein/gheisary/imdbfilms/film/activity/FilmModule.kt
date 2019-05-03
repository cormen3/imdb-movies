package hossein.gheisary.imdbfilms.film.activity

import dagger.Module
import dagger.Provides

@Module
class FilmModule {
    @Provides
    fun provideFilmNavigator(filmActivity: FilmActivity) = FilmNavigator(filmActivity)
}