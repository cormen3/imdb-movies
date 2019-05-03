package hossein.gheisary.imdbfilms.film.list.model

import hossein.gheisary.data.remote.core.RestDataSource
import hossein.gheisary.data.remote.model.SearchResult
import io.reactivex.Single

class FilmListRemote(private val restDataSource: RestDataSource): FilmListDataStore.Remote {
    override fun searchFilms(searchKey: String, page:Int): Single<SearchResult> {
        return restDataSource.searchFilms(searchKey, page)
    }
}