package hossein.gheisary.data.remote.core

import hossein.gheisary.data.remote.core.ApiRepository
import io.reactivex.Single
import hossein.gheisary.data.remote.core.ApiRepository.Companion.API_KEY
import hossein.gheisary.data.remote.core.Restapi
import hossein.gheisary.data.remote.model.FilmDetailsResponse
import hossein.gheisary.data.remote.model.SearchResult

class  RestDataSource constructor(var restapi: Restapi): ApiRepository {
    override fun getFilmDetails(id: String): Single<FilmDetailsResponse> {
        return restapi.getFilmDetails(API_KEY, id)
    }

    override fun searchFilms(searchKey: String, page:Int): Single<SearchResult> {
        return restapi.searchFilms(API_KEY, searchKey, page)
    }

}