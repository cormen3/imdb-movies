package hossein.gheisary.imdbfilms.film.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import hossein.gheisary.data.remote.model.Outcome
import hossein.gheisary.data.remote.model.SearchResult
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject
import hossein.gheisary.imdbfilms.film.list.model.FilmListDataStore
import hossein.gheisary.imdbfilms.utility.extensions.toLiveData

class FilmListViewModel @Inject constructor(var repository: FilmListDataStore.Repository,
                                            private val compositeDisposable: CompositeDisposable): ViewModel() {

    val loginOutcome: LiveData<Outcome<SearchResult>> by lazy {
        repository.filmOutcome.toLiveData(compositeDisposable)
    }

    fun searchFilms(searchKey: String, page:Int){
        repository.searchFilms(searchKey, page)
    }
}