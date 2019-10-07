package com.wolo.a222.feature.common.view

import android.os.Bundle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import com.wolo.a222.R
import com.wolo.a222.feature.auth.view.AuthFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.navigation_layout.*


class MainActivity : BaseActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.navigation_layout)

        val drawerLayout = findViewById<DrawerLayout>(R.id.drawer_layout)

        top_menu_button.setOnClickListener {
            drawerLayout.openDrawer(GravityCompat.END) }

        val navListener = NavigationView.OnNavigationItemSelectedListener{

            when (it.itemId) {
                R.id.nav_shop -> {
                    navigator.showShop()
                }
                R.id.nav_delete_gamer -> true
                R.id.nav_rules -> false
            }
            drawerLayout.closeDrawer(GravityCompat.END)
            true
        }
        nav_view.bringToFront()
        nav_view.setNavigationItemSelectedListener(navListener)


        if (savedInstanceState == null){
           navigator.onStartMainActivity()
        }

    }

    override fun onBackPressed() {
        val fragmentClass = supportFragmentManager.findFragmentById(R.id.content)!!::class
        if (fragmentClass == AuthFragment::class){
            var a ="a"
        }else{
            supportFragmentManager.popBackStack()
            navigator.onStartMainActivity()
        }
        super.onBackPressed()
    }
}
