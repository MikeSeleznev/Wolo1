package com.wolo.a222.utils

import android.content.Context
import android.os.Build
import java.util.*
import javax.inject.Inject

class CommonUtils @Inject constructor(private val context: Context){

    fun getLanguage(): String {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            context.resources.configuration.locales.get(0).language
        } else {
            Locale.getDefault().language
        }
    }
}