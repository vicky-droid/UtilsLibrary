package com.vigneshtheagarajan.utils.one

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import com.vigneshtheagarajan.utils.one.app.UtilsLib
import com.vigneshtheagarajan.utils.one.app.UtilsLib.context
import com.vigneshtheagarajan.utils.one.app.UtilsLib1

inline fun <reified T : Any> Context.startActivity() = startActivity(IntentFor<T>(this))

inline fun <reified T : Any> IntentFor(context: Context): Intent = Intent(context, T::class.java)

inline fun Context.toast(text: CharSequence): Toast =
    Toast.makeText(this, text, Toast.LENGTH_SHORT).apply { show() }

inline fun Context.longToast(text: CharSequence): Toast =
    Toast.makeText(this, text, Toast.LENGTH_LONG).apply { show() }

inline fun longToast(text: CharSequence): Toast =
    Toast.makeText(context, text, Toast.LENGTH_LONG).apply { show() }

inline fun toast(text: CharSequence): Toast =
    Toast.makeText(context, text, Toast.LENGTH_SHORT).apply { show() }

inline fun <reified T : Activity> Context.openActivity(noinline extra: Intent.() -> Unit) {
    val intent = Intent(this, T::class.java)
    intent.extra()
    startActivity(intent)
}

inline fun <reified T : Activity> Context.openActivity() {
    startActivity(Intent(this, T::class.java))
}

val TAG = "UtilsLibrary"

fun log(s: Any?, tag: String? = "") {
    Log.w("MyLog $tag", "$s")
}