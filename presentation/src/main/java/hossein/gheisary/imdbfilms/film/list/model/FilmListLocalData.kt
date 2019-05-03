package hossein.gheisary.imdbfilms.film.list.model

import hossein.gheisary.data.local.preference.PreferenceService
import hossein.gheisary.data.remote.model.SearchResult

class FilmListLocalData (private val preferenceService: PreferenceService): FilmListDataStore.Local{
    override fun saveFilmsInfo(searchResult: SearchResult) {}
}