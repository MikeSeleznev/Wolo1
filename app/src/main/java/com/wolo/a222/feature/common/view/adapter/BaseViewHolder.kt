package com.wolo.a222.feature.common.view.adapter

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView

open class BaseViewHolder<T : Any>(itemView: View) : RecyclerView.ViewHolder(itemView) {

    protected lateinit var item: T

    protected val context: Context
        get() = itemView.context

    open fun bind(item: T, payloads: List<Any>) {
        this.item = item
    }
}