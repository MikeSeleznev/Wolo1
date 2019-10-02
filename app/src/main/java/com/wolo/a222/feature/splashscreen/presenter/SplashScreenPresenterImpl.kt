package com.wolo.a222.feature.splashscreen.presenter


import com.jakewharton.rxrelay2.BehaviorRelay
import com.wolo.a222.Presenter.BasePresenter
import com.wolo.a222.feature.auth.model.interactor.SplashScreenInteractor
import com.wolo.a222.feature.common.di.Scope.PerScreen
import com.wolo.a222.feature.common.navigation.Navigator
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@PerScreen
class SplashScreenPresenterImpl
@Inject constructor(
        private var interactor: SplashScreenInteractor,
        val navigator: Navigator
        ): BasePresenter<SplashScreenView>, SplashScreenPresenter{

    private val compositeDisposable = CompositeDisposable()

    private val splashSubject = BehaviorRelay.createDefault(SplashScreenState())

    private var state: SplashScreenState
        set(value) = splashSubject.accept(value)
        get() = splashSubject.value!!

    override fun onFinish() {
        compositeDisposable.clear()
    }

    override fun initState() {
        state = SplashScreenState()
    }

    override fun viewState(): Flowable<SplashScreenState> {
        return splashSubject.toFlowable(BackpressureStrategy.LATEST)
                .onBackpressureBuffer(3)
                .subscribeOn(Schedulers.io())
                .doOnSubscribe {
                    if (compositeDisposable.size() == 0) {
                        loadDate()
                    }
                }
    }

    override fun loadDate() {
        interactor.loadPacks()
                .subscribeOn(Schedulers.io())
                .doOnComplete {
                    state = state.copy(screenText = "Карты загружены", dateIsLoaded = true)
                }
                .doOnError {
                        //TODO
                }
                .subscribe().also { compositeDisposable.add(it) }
    }

}