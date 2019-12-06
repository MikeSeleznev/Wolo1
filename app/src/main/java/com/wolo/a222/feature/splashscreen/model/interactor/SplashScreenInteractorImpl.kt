package com.wolo.a222.feature.splashscreen.model.interactor

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import com.android.billingclient.api.SkuDetails
import com.wolo.a222.Const
import com.wolo.a222.ConstInfoFields
import com.wolo.a222.feature.common.billing.Billing
import com.wolo.a222.feature.common.di.scope.PerFeature
import com.wolo.a222.feature.common.entity.Pack
import com.wolo.a222.feature.common.entity.Purchases
import com.wolo.a222.feature.common.model.repository.FB
import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
@PerFeature
class SplashScreenInteractorImpl
@Inject
constructor(
    private val fireBase: FB,
    private val billing: Billing,
    private val context: Context) : SplashScreenInteractor{

    override fun loadPacks(): Flowable<List<Pack>> =
        fireBase.getFireBaseDocuments(Const.FBCollection).toFlowable()
            .subscribeOn(Schedulers.newThread())
            .map { it ->
                val listPacks = mutableListOf<Pack>()
                it.map { s ->
                    var name = ""
                    var tasks = listOf<String>()
                    var paid = false
                    var id = ""
                    var activeImage = ""
                    var nonActiveImage = ""
                    var priority = 0L
                    var alwaysActive = false
                    val keys = s.data?.keys
                    var enTasks = listOf<String>()
                    var enName = ""
                    for (i in keys!!) {
                        when (i) {
                            "name" -> name = s.data?.get(i) as String
                            "cards" -> tasks = s.data?.get(i) as List<String>
                            "paid" -> paid = s.data?.get(i) as Boolean
                            "id" -> id = s.data?.get(i) as String
                            "activeImage" -> activeImage = s.data?.get(i) as String
                            "nonActiveImage" -> nonActiveImage = s.data?.get(i).toString()
                            "priority" -> priority = s.data?.get(i) as Long
                            "alwaysActive" -> alwaysActive = s.data?.get(i) as Boolean
                            "enCards" -> enTasks = s.data?.get(i) as List<String>
                            "enName" -> enName = s.data?.get(i) as String
                        }
                    }
                    listPacks.add(Pack(id, name, tasks, paid, activeImage, nonActiveImage, priority, tasks.size, alwaysActive, enTasks, enName))
                }
                listPacks.toList()
            }

    override fun loadSku(idList: List<String>): Flowable<List<SkuDetails>> =
        billing.getSkuInfo(context, idList)

    override fun loadPurchases(): Flowable<List<Purchases>> =
        billing.getPurchase(context)

    override fun loadInfo(): Single<Long> {
       return fireBase.getFireBaseDocuments(Const.FBInfo)
           .map {
               var version = 0L
               it.map {document ->
                   val keys = document.data?.keys
                   for (i in keys!!) {
                       if (i == ConstInfoFields.VERSION) version = document.data?.get(i) as Long
                   }
               }
               version
           }


    }
}


