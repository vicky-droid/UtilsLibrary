package com.vigneshtheagarajan.utils.one.view

import android.view.View

var View.visible
    get() = visibility == View.VISIBLE
    set(value) {
        visibility = if (value) View.VISIBLE else View.GONE
    }

fun View.hide(gone: Boolean = true) {
    visibility = if (gone) View.GONE else View.INVISIBLE
}

fun View.setViewVisible() {
    visibility = View.VISIBLE
}

fun View.setViewInVisible() {
    visibility = View.INVISIBLE
}

fun View.setViewGone() {
    visibility = View.INVISIBLE
}