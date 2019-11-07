package com.wolo.a222.feature.selecttask.view.adapter

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
import com.wolo.a222.feature.selecttask.presenter.SelectTaskVM

class SelectTaskDelegate (private val callback: OnClickItemSelectTaskCallback): AbsListItemAdapterDelegate<SelectTaskVM, SelectTaskVM, SelectTaskViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup): SelectTaskViewHolder {
        val view  = LayoutInflater.from(parent.context).inflate(R.layout.item_pack, parent, false)
        return SelectTaskViewHolder(view, callback)
    }

    override fun isForViewType(item: SelectTaskVM, items: MutableList<SelectTaskVM>, position: Int): Boolean = true

    override fun onBindViewHolder(
            item: SelectTaskVM,
            holder: SelectTaskViewHolder,
            payloads: MutableList<Any>
    ) {
        return holder.bind(item, payloads)
    }
}

interface OnClickItemSelectTaskCallback {
    fun onClickItem(item: SelectTaskVM)
}

class SelectTaskViewHolder(itemView: View, private val callback: OnClickItemSelectTaskCallback) : BaseViewHolder<SelectTaskVM>(itemView) {

    override fun bind(item: SelectTaskVM, payloads: List<Any>) {
        super.bind(item, payloads)

        val image = itemView.findViewById<ImageView>(R.id.imagePackSelectTask)
        image.setImageResource(R.drawable.alldecks)
        val packName = itemView.findViewById<TextView>(R.id.packNameSelectTask)
        packName.text = item.namePack
        val quantity = itemView.findViewById<TextView>(R.id.quantitySelectTask)
        quantity.text = item.quantityNow.toString() + "/" + item.quantity.toString()
        val lock = itemView.findViewById<ImageView>(R.id.lockSelectTask)
        if (item.isBought) {
            lock.visibility = View.INVISIBLE
        } else {
            lock.visibility = View.VISIBLE
        }

        image.setOnClickListener {
            if (item.isBought) callback.onClickItem(item)
        }

        Glide.with(context).asBitmap()
                .apply {
                    RequestOptions().fitCenter()
                }.load(item.urlImage)
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