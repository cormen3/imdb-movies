package hossein.gheisary.data.remote.core

import hossein.gheisary.data.remote.model.FilmDetailsResponse
import hossein.gheisary.data.remote.model.SearchResult
import io.reactivex.Single

interface ApiRepository {
    companion object {
            val BASE_URL: String get() = "http://www.omdbapi.com/"
            val API_KEY: String get() = "9af01761"
    }

    fun searchFilms(searchKey: String, page: Int): Single<SearchResult>
    fun getFilmDetails(id: String): Single<FilmDetailsResponse>
}