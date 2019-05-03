package hossein.gheisary.imdbfilms.film.details.model

import hossein.gheisary.data.remote.core.Scheduler
import hossein.gheisary.data.remote.exception.ExceptionHandler
import hossein.gheisary.data.remote.model.FilmDetailsResponse
import hossein.gheisary.data.remote.model.Outcome
import hossein.gheisary.imdbfilms.utility.extensions.*
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.PublishSubject


class FilmDetailsRepository (
    private val local: FilmDetailsDataStore.Local,
    private val remote: FilmDetailsDataStore.Remote,
    private val scheduler: Scheduler,
    private val exceptionHandler: ExceptionHandler,
    private val compositeDisposable: CompositeDisposable
): FilmDetailsDataStore.Repository {

    override val activeWorksDetailOutcome: PublishSubject<Outcome<FilmDetailsResponse>> = PublishSubject.create<Outcome<FilmDetailsResponse>>()


    override fun getFilmDetails(projectId:String) {
        remote.getFilmDetails(projectId)
            .performOnBackOutOnMain(scheduler)
            .doOnSubscribe{activeWorksDetailOutcome.loading(true)}
            .subscribe(
                {loginInfo -> activeWorksDetailOutcome.loading(false); activeWorksDetailOutcome.success(loginInfo)},
                {error -> activeWorksDetailOutcome.loading(false); handleError(error)}
            )
            .addTo(compositeDisposable)
    }


    override fun handleError(error: Throwable) {
        val exception = exceptionHandler.handleError(error)
        activeWorksDetailOutcome.failed(exception)
    }

}