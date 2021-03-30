package com.vigneshtheagarajan.utils.one.app

import android.app.Application
import android.content.Context

class UtilsLib1 {

    private var app: Application? = null


    private fun getApp():Application? {
        if (app==null)
            throw IllegalStateException(("Sorry, you should call MVVMs.onCreate() " + "method on your custom application first."))
        return app
    }

    fun getContext() : Context? = getApp()?.applicationContext


    companion object {
       private var singleInstance: UtilsLib1? = null
        val instance: UtilsLib1?
            get() {
                if (singleInstance == null) {
                    singleInstance = UtilsLib1()
                }
                return singleInstance
            }

    }

        fun initialize(application: Application) {
              app = application
//        UtilsLib.init(application)
            // If you want to use uix, you don't need to initialize it in your application manually.
        }


}




 object UtilsLib{
        private var app: Application? = null

        val context: Context? by lazy {
                 getApp()?.applicationContext
        }

        private fun getApp():Application? {
            if (app==null)
                throw IllegalStateException(("Sorry, you should call MVVMs.onCreate() " + "method on your custom application first."))
            return app
        }

        fun initialize(application: Application) {
            app = application
            context
        }

}







