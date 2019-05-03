package hossein.gheisary.imdbfilms.film.details.model

import hossein.gheisary.data.remote.model.FilmDetailsResponse
import hossein.gheisary.data.remote.model.Outcome
import io.reactivex.Single
import io.reactivex.subjects.PublishSubject

class FilmDetailsDataStore {
    interface Repository {
        val activeWorksDetailOutcome: PublishSubject<Outcome<FilmDetailsResponse>>
        fun getFilmDetails(projectId:String)
        fun handleError(error: Throwable)
    }

    interface Local {}

    interface Remote {
        fun getFilmDetails(projectId:String) : Single<FilmDetailsResponse>
    }
}