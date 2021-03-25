package com.vigneshtheagarajan.utils.one.view

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.util.Log
import android.view.Window
import com.vigneshtheagarajan.utils.R
import com.vigneshtheagarajan.utils.one.TAG
import com.vigneshtheagarajan.utils.one.app.UtilsLib.context

class ProgressLoader {
    var mDialog: Dialog? = null
    fun ProgressLoader(mContext: Context?) {
        try {
            mDialog?.let {
                if (it.isShowing)
                    return
            }

            mDialog = Dialog(mContext!!).apply {
                requestWindowFeature(Window.FEATURE_NO_TITLE)
                setContentView(R.layout.progress_item)
                window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                setCancelable(false)
                setCanceledOnTouchOutside(false)
                show()
            }

        } catch (e: Exception) {
            Log.e(TAG, e.message.toString())

        }
    }

    fun hideProgress() {
        try {
            mDialog?.apply {
                if (isShowing)
                    dismiss()
            }
        } catch (e: Exception) {
            Log.e(e.message.toString(), "Progress")
        }
    }

    companion object {
        var mProgressLoader: ProgressLoader? = null
        val instance: ProgressLoader?
            get() {
                if (mProgressLoader == null) {
                    mProgressLoader = ProgressLoader()
                }
                return mProgressLoader
            }
    }
}

fun showNewLoader() {
    try {
        ProgressLoader.instance?.ProgressLoader(context)
    } catch (e: Exception) {
        Log.e(TAG, e.message.toString())
    }
}

fun hideNewLoader() {
    ProgressLoader.instance?.hideProgress()
}