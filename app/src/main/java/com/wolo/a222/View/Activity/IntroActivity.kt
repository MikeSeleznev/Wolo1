package com.wolo.a222.View.Activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.wolo.a222.Presenter.FirebasePresenter
import com.wolo.a222.R

class IntroActivity : AppCompatActivity() {

    private lateinit var loadingText: TextView
    private lateinit var presenter: FirebasePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)
        loadingText = findViewById(R.id.loadingText)
        presenter = FirebasePresenter()
        presenter.init(this)

    }

    override fun onStart() {
        super.onStart()
        presenter.bindView(this)
        presenter.startGame()

    }

    override fun onStop() {
        super.onStop()
        presenter.saveData()
        presenter.unbindView(this)
    }
}
