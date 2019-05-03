package hossein.gheisary.imdbfilms.film.list.model

import hossein.gheisary.data.remote.model.Outcome
import hossein.gheisary.data.remote.model.SearchResult
import io.reactivex.Single
import io.reactivex.subjects.PublishSubject

class FilmListDataStore {
    interface Repository {
        val filmOutcome: PublishSubject<Outcome<SearchResult>>
        fun searchFilms(searchKey: String, page:Int)
        fun saveFilmsInfo(searchResult: SearchResult)
        fun handleError(error: Throwable)
    }

    interface Local {
        fun saveFilmsInfo(searchResult: SearchResult)
    }

    interface Remote {
        fun searchFilms(searchKey: String, page:Int): Single<SearchResult>
    }
}