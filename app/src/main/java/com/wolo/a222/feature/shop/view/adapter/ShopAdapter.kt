package com.wolo.a222.feature.shop.view.adapter

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import com.hannesdorfmann.adapterdelegates4.AdapterDelegatesManager
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import com.wolo.a222.feature.common.entity.SkuDeck
import com.wolo.a222.feature.shop.presenter.ShopVM


class ShopAdapter : AsyncListDifferDelegationAdapter<ShopVM>(ShopDiffCallBack()){
    fun addDelegate(delegate: AdapterDelegate<List<ShopVM>>): AdapterDelegatesManager<MutableList<ShopVM>>? =
        delegatesManager.addDelegate(delegate)
}

class ShopDiffCallBack : DiffUtil.ItemCallback<ShopVM>() {
    override fun areItemsTheSame(oldItem: ShopVM, newItem: ShopVM): Boolean {
        return oldItem == newItem
    }

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: ShopVM, newItem: ShopVM): Boolean {
        return oldItem == newItem
    }

}