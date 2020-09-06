package com.vigneshtheagarajan.utils.one

import android.annotation.SuppressLint
import android.view.MotionEvent
import android.widget.EditText

@SuppressLint("ClickableViewAccessibility")
fun EditText.onRightDrawableClicked(onClicked: (view: EditText) -> Unit) {
    this.setOnTouchListener { v, event ->
        var hasConsumed = false
        if (v is EditText) {
            if (event.x >= v.width - v.totalPaddingRight) {
                if (event.action == MotionEvent.ACTION_UP) {
                    onClicked(this)
                }
                hasConsumed = true
            }
        }
        hasConsumed
    }
}
@SuppressLint("ClickableViewAccessibility")
fun EditText.onLeftDrawableClicked(onClicked: (view: EditText) -> Unit) {
    this.setOnTouchListener { v, event ->
        var hasConsumed = false
        if (v is EditText) {
            if (event.x >= v.width - v.totalPaddingLeft) {
                if (event.action == MotionEvent.ACTION_UP) {
                    onClicked(this)
                }
                hasConsumed = true
            }
        }
        hasConsumed
    }
}

fun EditText.getString() = this.text.toString()

fun EditText.getStringValidation(): String {
    if (this.text.trim().isEmpty()) {
        this.error = "Must Not be Empty!"
    } else
        this.error = null
    return this.text.toString()
}