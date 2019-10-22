package com.wolo.a222.feature.shop.view

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import com.wolo.a222.R
import com.wolo.a222.feature.common.view.PresenterFragment
import com.wolo.a222.feature.shop.presenter.ShopPresenter
import com.wolo.a222.feature.shop.presenter.ShopState
import com.wolo.a222.feature.shop.presenter.ShopView
import com.wolo.a222.feature.shop.view.adapter.DataAdapter
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

    override fun onAttach(context: Context) {
        injector.getShopScreen().inject(this)
        super.onAttach(context)
    }

    override fun onDestroy() {
        super.onDestroy()
        if (isFinishing) injector.releaseShopScreen()
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

        val onClickClose = View.OnClickListener {
            presenter.closeShop()
        }
        close_shop_button.setOnClickListener(onClickClose)
    }

    private fun handleState(state: ShopState) {

        grid_view.adapter = DataAdapter(activity!!.applicationContext, state.skuDeck)

        grid_view.setOnItemClickListener { adapterView: AdapterView<*>, view1: View, i: Int, l: Long ->
          presenter.buyDeck(i, activity!!)
        }
    }
}