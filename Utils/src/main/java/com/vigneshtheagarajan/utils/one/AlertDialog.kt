package com.vigneshtheagarajan.utils.one

import android.content.Context
import android.util.Log
import androidx.appcompat.app.AlertDialog

fun Context.showListAlert(list: Array<String>, title: String? = null, value: (Int) -> Unit) {
    // setup the alert builder
    val builder = AlertDialog.Builder(this)
    if (title != null)
        builder.setTitle(title)

//    val animals = arrayOf("horse", "cow", "camel", "sheep", "goat")
    builder.setItems(list) { dialog, which ->
        Log.d(TAG, "showListAlert() called with: dialog = $dialog, which = $which")
        value.invoke(which)
        dialog.dismiss()
    }

// create and show the alert dialog
    val dialog = builder.create()
    dialog.show()
}

fun Context.showRadioButtonListAlert(
    list: Array<String>,
    title: String? = null,
    isShowOKButton: Boolean = false,
    value: (Int) -> Unit
) {

    // setup the alert builder
    val builder = AlertDialog.Builder(this)
    if (title != null)
        builder.setTitle(title)

// add a radio button list
    val checkedItem = 1
    builder.setSingleChoiceItems(list, checkedItem) { dialog, which ->
        // user checked an item
        if (!isShowOKButton) {
            value.invoke(which)
            dialog.dismiss()
        }
    }


    if (isShowOKButton) {
// add OK and Cancel buttons
        builder.setPositiveButton("OK") { dialog, which ->
            // user clicked OK
            value.invoke(which)

        }
        builder.setNegativeButton("Cancel", null)
    }
// create and show the alert dialog
    val dialog = builder.create()
    dialog.show()

}


/*
fun Context.showMultiSelectListAlert(
    list: Array<String>,
    title: String? = null,
    isShowOKButton: Boolean = false,
    value: (Int) -> Unit
) {

    // setup the alert builder
    val builder = AlertDialog.Builder(this)
    if (title != null)
        builder.setTitle(title)
// add a checkbox list
    val checkedItems = booleanArrayOf(true, false, false, true, false)
    builder.setMultiChoiceItems(list, checkedItems) { dialog, which, isChecked ->
        // user checked or unchecked a box
        value.invoke(which)

    }


    if (isShowOKButton) {
// add OK and Cancel buttons
        builder.setPositiveButton("OK") { dialog, which ->
            // user clicked OK
            value.invoke(which)

        }
        builder.setNegativeButton("Cancel", null)
    }

// create and show the alert dialog
    val dialog = builder.create()
    dialog.show()


}
*/






