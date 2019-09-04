package com.wolo.a222.feature.common.di.module

import android.content.Context
import com.google.firebase.firestore.FirebaseFirestore
import com.wolo.a222.feature.common.model.repository.FB
import com.wolo.a222.feature.common.di.Scope.PerApplication
import com.wolo.a222.feature.common.navigation.Navigator
import com.wolo.a222.feature.common.navigation.NavigatorImpl
import com.wolo.a222.feature.common.presenter.MainActivityPresenter
import com.wolo.a222.feature.common.presenter.MainActivityPresenterImpl
import dagger.Module
import dagger.Provides

@Module
class AppModule(private val context: Context) {

    @Provides
    @PerApplication
    fun provideContext(): Context = context

    @Provides
    @PerApplication
    fun provideNavigator(navigator: NavigatorImpl): Navigator = navigator

    @Provides
    @PerApplication
    fun provideMainActivityPresenter(presenter: MainActivityPresenterImpl): MainActivityPresenter = presenter

    @Provides
    @PerApplication
    fun provideFirebase(): FB {
        //FirebaseApp.initializeApp(context)
        return FB(FirebaseFirestore.getInstance())
    }
}