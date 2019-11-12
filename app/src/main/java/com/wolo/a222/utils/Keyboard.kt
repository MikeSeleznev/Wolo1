package com.wolo.a222.utils

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager

class Keyboard {

    fun hideKeyboard(act: Activity) {
        if (act.currentFocus != null) {
            val inputManager = act.applicationContext.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputManager.hideSoftInputFromWindow(act.currentFocus!!.windowToken, 0)
        }
        //inputManager.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0)
    }
}