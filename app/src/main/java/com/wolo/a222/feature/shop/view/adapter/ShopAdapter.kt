package com.wolo.a222.feature.shop.view.adapter

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import com.hannesdorfmann.adapterdelegates4.AdapterDelegatesManager
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import com.wolo.a222.model.sku.SkuDeck


class ShopAdapter : AsyncListDifferDelegationAdapter<SkuDeck>(ShopDiffCallBack()){
    fun addDelegate(delegate: AdapterDelegate<List<SkuDeck>>): AdapterDelegatesManager<MutableList<SkuDeck>>? =
        delegatesManager.addDelegate(delegate)
}

class ShopDiffCallBack : DiffUtil.ItemCallback<SkuDeck>() {
    override fun areItemsTheSame(oldItem: SkuDeck, newItem: SkuDeck): Boolean {
        return oldItem == newItem
    }

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: SkuDeck, newItem: SkuDeck): Boolean {
        return oldItem == newItem
    }

}