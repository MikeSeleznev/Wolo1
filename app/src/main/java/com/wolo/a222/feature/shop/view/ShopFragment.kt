package com.wolo.a222.feature.shop.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import com.wolo.a222.R
import com.wolo.a222.feature.common.view.PresenterFragment
import com.wolo.a222.feature.shop.presenter.ShopPresenter
import com.wolo.a222.feature.shop.presenter.ShopState
import com.wolo.a222.feature.shop.presenter.ShopView
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.fragment_shop.*
import javax.inject.Inject

class ShopFragment : PresenterFragment<ShopPresenter>(), ShopView {

    companion object {
        fun newInstance() = ShopFragment()
    }

    @Inject
    override lateinit var presenter: ShopPresenter


    override val layoutResId: Int
        get() = R.layout.fragment_shop

    override fun onAttach(context: Context?) {
        injector.getShopScreen().inject(this)
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.viewState()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleState)
                .run { disposeOnDestroyView(this) }




    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val lay = LayoutInflater.from(activity).inflate(R.layout.item_shop,null)

        var button = Button(activity)
        button.text = "Test"

        var button2 = Button(activity)
        button2.text = "Test2"

        var button3 = Button(activity)
        button3.text = "Test3"


        grid_layout.rowCount = 2
        grid_layout.columnCount = 2
        grid_layout.addView(button)
        grid_layout.addView(button2)
        grid_layout.addView(lay)
        /*grid_layout.addView(button)
        grid_layout.addView(button)*/



    }

    private fun handleState(state: ShopState) {

    }
}