package hossein.gheisary.imdbfilms.film.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import hossein.gheisary.data.remote.model.FilmDetailsResponse
import hossein.gheisary.data.remote.model.Outcome
import hossein.gheisary.imdbfilms.utility.extensions.toLiveData
import io.reactivex.disposables.CompositeDisposable
import hossein.gheisary.imdbfilms.film.details.model.FilmDetailsDataStore
import javax.inject.Inject

class FilmDetailsViewModel @Inject constructor(var repository: FilmDetailsDataStore.Repository, private val compositeDisposable: CompositeDisposable): ViewModel() {

    val activeWorksDetailOutcome: LiveData<Outcome<FilmDetailsResponse>> by lazy {
        repository.activeWorksDetailOutcome.toLiveData(compositeDisposable)
    }

    fun getFilmDetails(projectId:String) {
        repository.getFilmDetails(projectId)
    }
}