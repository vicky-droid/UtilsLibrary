package com.vigneshtheagarajan.utils.one.view

import android.R
import android.app.Activity
import android.content.Context
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import com.vigneshtheagarajan.utils.one.TAG


fun Context.showListAlert(list: Array<String>, title: String? = null, value: (Int) -> Unit) {
    // setup the alert builder
    val builder = AlertDialog.Builder(this)
    if (title != null)
        builder.setTitle(title)

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
    val checkedItem = 0
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


/**
 * showAlertDialogBox
 *
 * @param message
 * @param title
 * @param textPositiveBtn
 * @param textNegativeBtn
 * @param onlyPostiveBtn
 * @param postiveBtnColour
 * @param negativeBtnColour
 * @param btnClicked
 */

fun Activity.showDialogBox(
    message: String?, title: String? = null,
    textPositiveBtn: String? = null,
    textNegativeBtn: String? = null,
    onlyPostiveBtn: Boolean? = false,
    postiveBtnColour: Int? = null,
    negativeBtnColour: Int? = null,
    btnClicked: (postive: Boolean?, negative: Boolean?) -> Unit,

    ) {
    val alertDialogBuilder = AlertDialog.Builder(this)


    title?.let {
        alertDialogBuilder.setTitle(it)
    }
    alertDialogBuilder.setMessage(message)

    alertDialogBuilder.setPositiveButton(
        textPositiveBtn ?: "Ok"
    ) { arg0, arg1 ->
        btnClicked(true, null)
    }
    if (onlyPostiveBtn == false)
        alertDialogBuilder.setNegativeButton(
            textNegativeBtn ?: "cancel"
        ) { arg0, arg1 ->
            btnClicked(null, true)
        }
    val alertDialog = alertDialogBuilder.create()

    val ptiveBtnColour = postiveBtnColour ?: ContextCompat.getColor(this, com.vigneshtheagarajan.utils.R.color.black)
    val ntiveBtnColour = negativeBtnColour ?: ContextCompat.getColor(this, com.vigneshtheagarajan.utils.R.color.gray)
    alertDialog.setOnShowListener { dialog -> //
        dialog as AlertDialog
        dialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(ptiveBtnColour);
        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(ntiveBtnColour);
    }

    alertDialog.show()
}


fun hh(context: Activity) {
    context.showDialogBox("", "", "", "") { l, p ->

    }

}
