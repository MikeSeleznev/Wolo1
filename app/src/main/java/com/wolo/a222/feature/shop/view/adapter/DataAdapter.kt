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
import com.bumptech.glide.request.target.Target
import com.wolo.a222.R
import com.wolo.a222.model.sku.SkuDeck

class DataAdapter(val context: Context, private val skuDeck: List<SkuDeck>) : BaseAdapter() {

    override fun getItem(position: Int): Any {
        return skuDeck[position]
    }

    override fun getItemId(position: Int): Long {
        return 1
    }

    override fun getCount(): Int {
        return skuDeck.size
    }

    @SuppressLint("CheckResult", "ViewHolder", "InflateParams")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        val view = LayoutInflater.from(context).inflate(R.layout.item_shop, null)
        val image = view.findViewById<ImageView>(R.id.image_pack)
        image.setImageResource(R.drawable.alldecks)
        val packName = view.findViewById<TextView>(R.id.pack_name)
        packName.text = skuDeck[position].name
        val price = view.findViewById<TextView>(R.id.price_text)
        price.text = skuDeck[position].price

        val setImage = if (skuDeck[position].isBought) {
            skuDeck[position].nonActiveImage
        } else {
            skuDeck[position].activeImage
        }

        Glide.with(context).load(setImage)
                .listener(object : RequestListener<Drawable>{
                    override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
                        return false
                    }

                    override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                        image.setImageDrawable(resource)
                        return true
                    }
                })
                .into(image)
        return view
    }


}