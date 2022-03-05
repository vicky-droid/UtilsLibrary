package com.vigneshtheagarajan.utils.one.network.logger

import android.util.Log
import com.vigneshtheagarajan.utils.one.TAG

object AdvancedLog {

    private const val TOP_LEFT_CORNER = '┌'
    private const val BOTTOM_LEFT_CORNER = '└'
    private const val MIDDLE_CORNER = '├'
    private const val HORIZONTAL_LINE = '│'
    private const val DOUBLE_DIVIDER = "────────────────────────────────────────────────────────"
    private const val SINGLE_DIVIDER = "┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄"
    private const val TOP_BORDER = TOP_LEFT_CORNER.toString() + DOUBLE_DIVIDER + DOUBLE_DIVIDER
    private const val BOTTOM_BORDER = BOTTOM_LEFT_CORNER.toString() + DOUBLE_DIVIDER + DOUBLE_DIVIDER
    private const val MIDDLE_BORDER = MIDDLE_CORNER.toString() + SINGLE_DIVIDER + SINGLE_DIVIDER


    fun debug(message: () -> Any?): Unit {
        Log.i(TAG, StackTraceInfo.invokingFileNameFqn)
        BaseLogger().logFull(Log.DEBUG, TAG,"${message.invoke()}")
    }

    fun d(message: () -> Any?): Unit {
        Log.i(TAG, StackTraceInfo.invokingFileNameFqn)
        BaseLogger().logFull(Log.DEBUG, TAG,"${message.invoke()}")
    }


    fun debugBeautify(message: () -> Any?): Unit {
        Log.i(TAG, TOP_BORDER)
        Log.i(TAG,"$HORIZONTAL_LINE  ${ StackTraceInfo.invokingFileNameFqn}")
        Log.i(TAG, MIDDLE_BORDER)
        BaseLogger().logFull(Log.DEBUG, TAG,"$HORIZONTAL_LINE  ${message.invoke()}")
        Log.i(TAG, BOTTOM_BORDER)
    }

    fun db(message: () -> Any?): Unit {
        Log.i(TAG, TOP_BORDER)
        Log.i(TAG,"$HORIZONTAL_LINE  ${ StackTraceInfo.invokingFileNameFqn}")
        Log.i(TAG, MIDDLE_BORDER)
        BaseLogger().logFull(Log.DEBUG, TAG,"$HORIZONTAL_LINE  ${message.invoke()}")
        Log.i(TAG, BOTTOM_BORDER)
    }



    fun logD(s: Any?, tag: String? = "") {
        Log.w("MyLog $tag", "$s")
        Log.i(TAG, TOP_BORDER)
        Log.i(TAG,"$HORIZONTAL_LINE  ${ StackTraceInfo.invokingFileNameFqn}")
        Log.i(TAG, MIDDLE_BORDER)
        BaseLogger().logFull(Log.DEBUG, TAG,"$HORIZONTAL_LINE  $s")
        Log.i(TAG, BOTTOM_BORDER)
    }




}