package com.wolo.a222.feature.shop.view.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.CustomViewTarget
import com.bumptech.glide.request.target.Target
import com.wolo.a222.R
import com.wolo.a222.model.sku.SkuDeck

class DataAdapter(context: Context, skuDeck: List<SkuDeck>) : BaseAdapter() {
    val context = context
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

    @SuppressLint("CheckResult")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        val view = LayoutInflater.from(context).inflate(R.layout.item_shop, null)
        val image = view.findViewById<ImageView>(R.id.image_pack)
        image.setImageResource(R.drawable.alldecks)
        val packName = view.findViewById<TextView>(R.id.pack_name)
        packName.text = skuDeck[position].name
        val price = view.findViewById<TextView>(R.id.price_text)
        price.text = skuDeck[position].price
        Glide.with(context).load(skuDeck[position].activeImage).into(image)

        return view
    }


}