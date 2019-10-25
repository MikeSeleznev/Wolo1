package com.wolo.a222.utils

import android.content.Context
import android.view.inputmethod.InputMethodManager

class Keyboard {

    fun hideKeyboard(context: Context) {
        val inputManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputManager.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0)
    }
}