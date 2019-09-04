package com.wolo.a222


import com.wolo.a222.Presenter.FirebasePresenter
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