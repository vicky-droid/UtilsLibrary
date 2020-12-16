package com.vigneshtheagarajan.utils.one

import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast

inline fun <reified T : Any> Context.startActivity() = startActivity(IntentFor<T>(this))

inline fun <reified T : Any> IntentFor(context: Context): Intent = Intent(context, T::class.java)

inline fun Context.toast(text: CharSequence): Toast =
    Toast.makeText(this, text, Toast.LENGTH_SHORT).apply { show() }

inline fun Context.longToast(text: CharSequence): Toast =
    Toast.makeText(this, text, Toast.LENGTH_LONG).apply { show() }

val TAG = "UtilsLibrary"

fun log(s: Any?, tag: String? = "") {
    Log.w("MyLog $tag", "$s")
}