package com.wolo.a222.market

import android.app.Activity
import android.content.Context
import android.view.View

class ButtonOnClick(v: View, context: Context, observableBilling: Billing, activity: Activity, pack: String):View.OnClickListener {

    private var context: Context = context
    private var observableBilling: Billing = observableBilling
    private var activity: Activity = activity
    private var pack: String = pack


    override fun onClick(v: View?) {
        observableBilling.startConnection(activity, pack)
    }
}