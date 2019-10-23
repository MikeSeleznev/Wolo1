package com.wolo.a222.feature.selecttask.view.adapter

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
import com.wolo.a222.feature.selecttask.presenter.SelectTaskVM

class SelectTaskAdapter1(val context: Context, private val pack: List<SelectTaskVM>) : BaseAdapter() {

    override fun getItem(position: Int): Any {
        return pack[position]
    }

    override fun getItemId(position: Int): Long {
        return 1
    }

    override fun getCount(): Int {
        return pack.size
    }

    @SuppressLint("CheckResult", "ViewHolder", "InflateParams")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        val view = LayoutInflater.from(context).inflate(R.layout.item_pack, null)
        val image = view.findViewById<ImageView>(R.id.image_pack)
        image.setImageResource(R.drawable.alldecks)
        val packName = view.findViewById<TextView>(R.id.pack_name)
        packName.text = pack[position].namePack
        val quantity = view.findViewById<TextView>(R.id.quantity)
        quantity.text = pack[position].quantityNow.toString() + "/" + pack[position].quantity.toString()

        Glide.with(context).load(pack[position].urlImage)
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