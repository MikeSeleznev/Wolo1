package com.wolo.a222


import android.content.Context
import android.net.ConnectivityManager
import com.wolo.a222.Presenter.FirebasePresenter
import com.wolo.a222.View.Activity.IntroActivity
import org.junit.jupiter.api.Test


class Test(){

    @Test
    fun hasConnectiontest() {
        //IntroActivity().onCreate()
        var connection = FirebasePresenter().hasConnection()
        if(connection == true){
        print("Test is ok")}
        else {
            print("Test is bad")
        }
    }
}