package com.wolo.a222.Presenter


import com.wolo.a222.View.Activity.IntroActivity


class IntroActivityPresenter {

    private var view: IntroActivity? = null

    fun attachView(introActivity: IntroActivity){
        view = introActivity
    }

    fun detachView(){
        view = null
    }


    fun getPacks(){


    }
}