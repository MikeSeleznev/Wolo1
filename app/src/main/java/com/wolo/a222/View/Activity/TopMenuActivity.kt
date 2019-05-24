package com.wolo.a222.View.Activity

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button

import com.wolo.a222.R
import com.wolo.a222.View.Activity.InfoActivity
import com.wolo.a222.View.Activity.ShopActivity

class TopMenuActivity : Fragment() {
    private lateinit var buttonRules: Button
    private lateinit var buttonStore: Button

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        //return super.onCreateView(inflater, container, savedInstanceState);
        val rootView = inflater.inflate(R.layout.topmenufragment, container, false)
        buttonRules = rootView.findViewById<View>(R.id.buttonRules) as Button
        buttonStore = rootView.findViewById<View>(R.id.buttonStore) as Button
        buttonRules.setOnClickListener {
            val intent = Intent(activity, InfoActivity::class.java)
            startActivity(intent)
        }

        buttonStore.setOnClickListener {
            val intent = Intent(activity, ShopActivity::class.java)
            startActivity(intent)
        }
        return rootView
    }
}