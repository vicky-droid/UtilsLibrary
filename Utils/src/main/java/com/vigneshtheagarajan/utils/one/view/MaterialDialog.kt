package com.vigneshtheagarajan.utils.one.view

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Typeface
import android.text.InputType
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.RelativeLayout
import androidx.appcompat.app.AlertDialog
import com.vigneshtheagarajan.utils.R
import com.vigneshtheagarajan.utils.one.TAG
import kotlinx.android.synthetic.main.material_dilaog.*
import kotlinx.android.synthetic.main.material_dilaog.mainLayout
import kotlinx.android.synthetic.main.material_dilaog_empty.*

class MaterialDialog {

    enum class POSITIONS {
        CENTER, BOTTOM
    }

    companion object {

        lateinit var layoutInflater: LayoutInflater

        /***
         * core method For Alert Dialog
         * */
        fun build(
            context: Activity
        ): AlertDialog {
            layoutInflater = LayoutInflater.from(context)
            val alertDialog =
                AlertDialog.Builder(
                    context, R.style.full_screen_dialog
                )
                    .setView(R.layout.material_dilaog)
            val alert: AlertDialog = alertDialog.create()
            // Let's start with animation work. We just need to create a style and use it here as follows.
            //Pop In and Pop Out Animation yet pending
//            alert.window?.attributes?.windowAnimations = R.style.SlidingDialogAnimation
            alert.show()
            return alert
        }

    }
}

/***
 * Title Properties For Alert Dialog
 * */
fun AlertDialog.title(
    title: String,
    fontStyle: Typeface? = null,
    titleColor: Int = 0
): AlertDialog {
    this.title.text = title.trim()
    if (fontStyle != null) {
        this.title.typeface = fontStyle
    }
    if (titleColor != 0) {
        this.title.setTextColor(titleColor)
    }
    this.title.show()
    return this
}


/***
 * Dialog Background properties For Alert Dialog
 * */
fun AlertDialog.background(
    dialogBackgroundColor: Int? = null
): AlertDialog {
    if (dialogBackgroundColor != null) {
        this.mainLayout.setBackgroundResource(dialogBackgroundColor)
    }
    return this
}

/***
 * Positions of Alert Dialog
 * */
fun AlertDialog.position(
    position: MaterialDialog.POSITIONS = MaterialDialog.POSITIONS.BOTTOM
): AlertDialog {
    val layoutParams = mainLayout.layoutParams as RelativeLayout.LayoutParams
    if (position == MaterialDialog.POSITIONS.CENTER) {
        layoutParams.addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE)
    } else if (position == MaterialDialog.POSITIONS.BOTTOM) {
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE)
    }
    mainLayout!!.layoutParams = layoutParams
    return this
}

/***
 * Sub Title or Body of Alert Dialog
 * */
fun AlertDialog.body(
    body: String,
    fontStyle: Typeface? = null,
    color: Int = 0
): AlertDialog {
    this.subHeading.text = body.trim()
    this.subHeading.show()
    if (fontStyle != null) {
        this.subHeading.typeface = fontStyle
    }
    if (color != 0) {
        this.subHeading.setTextColor(color)
    }
    return this
}

/***
 * Icon of  Alert Dialog
 * */
fun AlertDialog.icon(
    icon: Int,
    animateIcon: Boolean = false
): AlertDialog {
    this.image.setImageResource(icon)
    this.image.show()
    // Pulse Animation for Icon
    if (animateIcon) {
        val pulseAnimation = AnimationUtils.loadAnimation(context, R.anim.pulse)
        image.startAnimation(pulseAnimation)
    }
    return this
}

fun AlertDialog.input(
    input: Boolean = false,
    hint: String? = null,
    color: Int? = null
): AlertDialog {
    if (input) {
        hint?.let {
            this.input_text.hint = it
        }

        if (color != null) this.input_text.defaultHintTextColor =  ColorStateList.valueOf(color)

        this.input_text.visibility = View.VISIBLE
    }
    return this
}

fun AlertDialog.setMultiline(
    isMultiline: Boolean = false,
    line: Int = 3
): AlertDialog {
    if (isMultiline) {
        this.input_et.inputType =  InputType.TYPE_TEXT_FLAG_MULTI_LINE
        this.input_et.setHorizontallyScrolling(false)
        this.input_et.minLines = line
        this.input_et.setLines(line)
        this.input_et.gravity = Gravity.TOP
    }
    return this
}

fun AlertDialog.enableCounter(
    boolean: Boolean = false,
    maxSize: Int = 3
): AlertDialog {
    if (boolean) {
        this.input_text.isCounterEnabled = boolean
        this.input_text.counterMaxLength=maxSize
    }
    return this
}




/***
 * onPositive Button Properties For Alert Dialog
 *
 * No Need to call dismiss(). It is calling already
 * */
fun AlertDialog.onPositive(
    text: String,
    buttonBackgroundColor: Int? = null,
    textColor: Int? = null,
    action: ((input:String?) -> Unit)? = null
): AlertDialog {
    this.yesButton.show()
    if (buttonBackgroundColor != null) {
        this.yesButton.setBackgroundResource(buttonBackgroundColor)
    }
    if (textColor != null) {
        this.yesButton.setTextColor(textColor)
    }
    this.yesButton.text = text.trim()
    this.yesButton.setOnClickListener {
        action?.invoke("${this.input_text.editText?.text.toString()}")
        dismiss()
    }
    return this
}

/***
 * onNegative Button Properties For Alert Dialog
 *
 * No Need to call dismiss(). It is calling already
 * */
fun AlertDialog.onNegative(
    text: String,
    buttonBackgroundColor: Int? = null,
    textColor: Int? = null,
    action: (() -> Unit)? = null
): AlertDialog {
    this.noButton.show()
    this.noButton.text = text.trim()
    if (textColor != null) {
        this.noButton.setTextColor(textColor)
    }
    if (buttonBackgroundColor != null) {
        this.noButton.setBackgroundResource(buttonBackgroundColor)
    }
    this.noButton.setOnClickListener {
        action?.invoke()
        dismiss()
    }
    return this
}


private fun View.show() {
    this.visibility = View.VISIBLE
}

fun Context.showListAlertMaterial(list: Array<String>, title: String? = null, value: (Int) -> Unit)   {
    // setup the alert builder
    // val builder = AlertDialog.Builder(this)
    val builder = AlertDialog.Builder(this, R.style.new_dialog)

    if (title != null)
        builder.setTitle(title)

    builder.setItems(list) { dialog, which ->
        Log.d(TAG, "showListAlert() called with: dialog = $dialog, which = $which")
        value.invoke(which)
        dialog.dismiss()
    }

    // create and show the alert dialog
    val dialog = builder.create()
//    dialog.mainLayout.setBackgroundResource(R.color.white)
    dialog.setContentView(R.layout.material_dilaog_empty);
//    dialog.window?.setBackgroundDrawableResource(R.drawable.layout_rounded_white,);
//    dialog.window?.setContentView(R.layout.material_dilaog_empty);

    dialog.show()

//    dialog.position(MaterialDialog.POSITIONS.CENTER)



}


