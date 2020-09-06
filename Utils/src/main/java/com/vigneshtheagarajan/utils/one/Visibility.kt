package com.vigneshtheagarajan.utils.one

import android.view.View

var View.visible
    get() = visibility == View.VISIBLE
    set(value) {
        visibility = if (value) View.VISIBLE else View.GONE
    }

inline fun View.hide(gone: Boolean = true) {
    visibility = if (gone) View.GONE else View.INVISIBLE
}

inline fun View.show() {
    visibility = View.VISIBLE
}