package com.wolo.a222.utils

import android.content.Context
import android.graphics.Bitmap
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.wolo.a222.feature.common.entity.Pack
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class SaveLoadImage(private val context:Context){

    fun saveImage(pack: MutableList<Pack>){

        Flowable.just{
            pack.map {
                Glide.with(context)
                        .asBitmap()
                        .apply {
                            RequestOptions().fitCenter()
                        }
                        .load(it.activeImage)
                        .listener(object : RequestListener<Bitmap> {
                            override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Bitmap>?, isFirstResource: Boolean): Boolean {
                                return false
                            }

                            override fun onResourceReady(resource: Bitmap?, model: Any?, target: Target<Bitmap>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {

                                try {
                                    val outPutStream = context.openFileOutput(it.name, Context.MODE_PRIVATE)
                                    resource!!.compress(Bitmap.CompressFormat.WEBP, 100, outPutStream)
                                    outPutStream.close()
                                } catch (e: Exception) {
                                    e.printStackTrace()
                                }
                                return true
                            }
                        }).submit().get()
            }
        }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    /*fun save(image: Bitmap?, name: String){
        try {
            val outPutStream = context.openFileOutput(name, Context.MODE_PRIVATE)
            image!!.compress(Bitmap.CompressFormat.WEBP, 100, outPutStream)
            outPutStream.close()
        }catch (e: Exception){
            e.printStackTrace()
        }
    }*/
}