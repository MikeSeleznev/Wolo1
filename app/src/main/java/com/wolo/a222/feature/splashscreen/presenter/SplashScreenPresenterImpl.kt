package com.wolo.a222.feature.splashscreen.presenter

import com.ironz.binaryprefs.Preferences
import com.jakewharton.rxrelay2.BehaviorRelay
import com.wolo.a222.ConstInfoFields
import com.wolo.a222.feature.common.di.Scope.PerScreen
import com.wolo.a222.feature.common.entity.Pack
import com.wolo.a222.feature.common.entity.SkuDeck
import com.wolo.a222.feature.common.presenter.BasePresenter
import com.wolo.a222.feature.common.repository.WoloRepository
import com.wolo.a222.feature.splashscreen.model.interactor.SplashScreenInteractor
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

import javax.inject.Inject


@PerScreen
class SplashScreenPresenterImpl
@Inject constructor(
    private val splashScreenInteractor: SplashScreenInteractor,
    private val woloRepository: WoloRepository,
    private val preferences: Preferences
) : BasePresenter<SplashScreenView>, SplashScreenPresenter {

    private val compositeDisposable = CompositeDisposable()
    private var cacheList = emptyList<Pack>()

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
                        loadInfo()
                    }
                }
    }

    override fun loadDate(version: Long) {

        splashScreenInteractor.loadPacks()
            .subscribeOn(Schedulers.io())
            .flatMap {
                cacheList = it
                woloRepository.setPacks(it)
                    .toFlowable()
                    .subscribeOn(Schedulers.io())
            }
            .flatMap {
                val listId = mutableListOf<String>()
                cacheList.map {
                    if (it.id != "") listId.add(it.id)
                }
                splashScreenInteractor.loadSku(listId)
            }
            .flatMap { skuDetails ->
                val skuList = skuDetails.map {
                    SkuDeck(it.sku, it.title, it.price)
                }
                woloRepository.setSkuDecks(skuList)
                    .toFlowable()
                    .subscribeOn(Schedulers.io())
            }.flatMap {
                splashScreenInteractor.loadPurchases()
            }.flatMap {
                woloRepository.setPurchases(it)
                    .toFlowable()
                    .subscribeOn(Schedulers.io())
            }
            .doOnSubscribe { state = state.copy(screenText = "Загрузка данных", dateIsLoaded = false) }
            .subscribe { result ->
                preferences.edit().putLong(ConstInfoFields.VERSION, version).commit()
                state = state.copy(screenText = "Данные загружены", dateIsLoaded = true)
            }
            .also { compositeDisposable.add(it) }
    }

    override fun loadInfo() {
        splashScreenInteractor.loadInfo()
            .doOnSuccess {
                val currentVersion = preferences.getLong(ConstInfoFields.VERSION, 0L)
                if (currentVersion < it) {
                    loadDate(it)
                } else {
                    state = state.copy(screenText = "Данные загружены", dateIsLoaded = true)
                }
            }

            .subscribe()
            .also { compositeDisposable.add(it) }

    }
}