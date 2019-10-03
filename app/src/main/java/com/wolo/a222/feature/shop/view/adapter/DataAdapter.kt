package com.wolo.a222.feature.shop.view.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.wolo.a222.model.sku.SkuDeck

class DataAdapter(context: Context, skuDeck: List<SkuDeck>) : BaseAdapter() {
    private val skuDeck: List<SkuDeck> = skuDeck

    override fun getItem(position: Int): Any {
        return skuDeck.get(position)
    }

    override fun getItemId(position: Int): Long {
        return 1
    }

    override fun getCount(): Int {
        return skuDeck.size
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        return convertView!!
    }
}