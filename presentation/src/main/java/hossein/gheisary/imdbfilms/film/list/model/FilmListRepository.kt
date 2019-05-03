package hossein.gheisary.imdbfilms.film.list.model

import hossein.gheisary.data.remote.core.Scheduler
import hossein.gheisary.data.remote.exception.ExceptionHandler
import hossein.gheisary.data.remote.model.Outcome
import hossein.gheisary.data.remote.model.SearchResult
import hossein.gheisary.imdbfilms.utility.extensions.*
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.PublishSubject

class FilmListRepository (
    private val local: FilmListDataStore.Local,
    private val remote: FilmListDataStore.Remote,
    private val scheduler: Scheduler,
    private val exceptionHandler: ExceptionHandler,
    private val compositeDisposable: CompositeDisposable
): FilmListDataStore.Repository {

    override val filmOutcome: PublishSubject<Outcome<SearchResult>> = PublishSubject.create<Outcome<SearchResult>>()

    override fun searchFilms(searchKey: String, page:Int) {
        remote.searchFilms(searchKey, page)
                .performOnBackOutOnMain(scheduler)
                .doOnSubscribe{filmOutcome.loading(true)}
                .subscribe(
                        {    info -> if(info.Search!=null){filmOutcome.success(info)}
                            filmOutcome.loading(false)},
                        {error -> handleError(error)
                            filmOutcome.loading(false)}
                )
                .addTo(compositeDisposable)
    }

    override fun saveFilmsInfo(searchResult: SearchResult) {
        local.saveFilmsInfo(searchResult)
    }

    override fun handleError(error: Throwable) {
        val exception = exceptionHandler.handleError(error)
        filmOutcome.failed(exception)
    }
}