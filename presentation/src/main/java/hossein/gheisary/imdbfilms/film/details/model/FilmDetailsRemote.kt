package hossein.gheisary.imdbfilms.film.details.model

import hossein.gheisary.data.remote.core.RestDataSource
import hossein.gheisary.data.remote.model.FilmDetailsResponse
import io.reactivex.Single

class FilmDetailsRemote(private val restDataSource: RestDataSource): FilmDetailsDataStore.Remote {

    override fun getFilmDetails(id : String): Single<FilmDetailsResponse> {
        return  restDataSource.getFilmDetails(id)
    }
}