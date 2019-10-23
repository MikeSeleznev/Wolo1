package com.wolo.a222.feature.shop.view

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.wolo.a222.R
import com.wolo.a222.feature.common.view.PresenterFragment
import com.wolo.a222.feature.shop.presenter.ShopPresenter
import com.wolo.a222.feature.shop.presenter.ShopState
import com.wolo.a222.feature.shop.presenter.ShopView
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.fragment_shop.*
import com.wolo.a222.feature.shop.view.adapter.ShopAdapter
import com.wolo.a222.model.sku.SkuDeck
import ru.ireca.kitchen.feature.stoplist.pagefragment.view.pageAdapter.OnClickItemCallback
import ru.ireca.kitchen.feature.stoplist.pagefragment.view.pageAdapter.ShopDelegate
import javax.inject.Inject

class ShopFragment : PresenterFragment<ShopPresenter>(), ShopView, OnClickItemCallback {

    companion object {
        fun newInstance() = ShopFragment()
    }

    @Inject
    override lateinit var presenter: ShopPresenter

    override val layoutResId: Int
        get() = R.layout.fragment_shop

    private val adapter: ShopAdapter by lazy {
        ShopAdapter().also {
            it.addDelegate(ShopDelegate(this))
        }
    }

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

        packs.layoutManager = GridLayoutManager(requireContext(), 2)
        packs.adapter = adapter

        val onClickClose = View.OnClickListener {
            presenter.closeShop()
        }
        close_shop_button.setOnClickListener(onClickClose)
    }

    private fun handleState(state: ShopState) {
        adapter.items = state.skuDeck
    }

    override fun onClickItem(item: SkuDeck) {
        presenter.buyDeck(item, activity!!)
    }
}