package com.wolo.a222.feature.shop.view.adapter

import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate
import com.wolo.a222.R
import com.wolo.a222.feature.common.view.adapter.BaseViewHolder
import com.wolo.a222.model.sku.SkuDeck

class ShopDelegate (private val callback: OnClickItemCallback): AbsListItemAdapterDelegate<SkuDeck, SkuDeck, ShopViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup): ShopViewHolder {
        val view  = LayoutInflater.from(parent.context).inflate(R.layout.item_shop, parent, false)
        return ShopViewHolder(view, callback)
    }

    override fun isForViewType(item: SkuDeck, items: MutableList<SkuDeck>, position: Int): Boolean = true

    override fun onBindViewHolder(
            item: SkuDeck,
            holder: ShopViewHolder,
            payloads: MutableList<Any>
    ) {
        return holder.bind(item, payloads)
    }
}

interface OnClickItemCallback {
    fun onClickItem(item: SkuDeck)
}

class ShopViewHolder(itemView: View, private val callback: OnClickItemCallback) : BaseViewHolder<SkuDeck>(itemView) {

    override fun bind(item: SkuDeck, payloads: List<Any>) {
        super.bind(item, payloads)

        val image = itemView.findViewById<ImageView>(R.id.image_pack)
        image.setImageResource(R.drawable.alldecks)
        val packName = itemView.findViewById<TextView>(R.id.pack_name)
        packName.text = item.name
        val price = itemView.findViewById<TextView>(R.id.price_text)
        price.text = item.price

        val setImage = if (item.isBought) {
            item.nonActiveImage
        } else {
            item.activeImage
        }

        image.setOnClickListener {
            callback.onClickItem(item)
        }

        Glide.with(context).asBitmap()
                .apply {
                    RequestOptions().fitCenter()
                }.load(setImage)
                .listener(object : RequestListener<Bitmap> {

                    override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Bitmap>?, isFirstResource: Boolean): Boolean {
                        return false
                    }

                    override fun onResourceReady(resource: Bitmap?, model: Any?, target: Target<Bitmap>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                        image.setImageBitmap(resource)
                        return true
                    }

                })
                .into(image)
    }
}