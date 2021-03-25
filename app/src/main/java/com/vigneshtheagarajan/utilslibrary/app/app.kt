package com.vigneshtheagarajan.utilslibrary.app

import android.app.Application
import com.vigneshtheagarajan.utils.one.app.UtilsLib
import com.vigneshtheagarajan.utils.one.app.UtilsLib1

class app: Application() {
    override fun onCreate() {
        super.onCreate()
        UtilsLib.initialize(this)
    }
}