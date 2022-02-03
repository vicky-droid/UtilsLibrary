package com.vigneshtheagarajan.utilslibrary.app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.vigneshtheagarajan.utils.one.toast
import com.vigneshtheagarajan.utils.one.view.*
import com.vigneshtheagarajan.utilslibrary.R
import kotlinx.android.synthetic.main.activity_view_helper.*

class ViewHelperActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_helper)
        ini()
    }

    private fun ini() {
        showAlertButton.setOnClickListener {
            showDialogBox(
                "This is test dialog",          // Alert message
                "Title",                           //Title ,This is optional
                "okay",                   //Positive button, This is optional
                null,                    //Negative button, This is optional
                false,                    //if you need single button pass this true, This is optional
                ContextCompat.getColor(this, R.color.design_default_color_primary), //optional, if you need to set colour for primary button
                ContextCompat.getColor(this, R.color.black) //optional, if you need to set colour for secondary button
            )
            { postive, negative ->
                if (postive == true)
                    toast("positive btn clicked")
                if (negative == true)
                    toast("negative btn clicked")

            }
        }

        showNewAlertButton.setOnClickListener {
            MaterialDialog.build(this)
                .title("Congratulations")
                .position(MaterialDialog.POSITIONS.CENTER)
                .body("Enter your name")
                .input(true,"test hint",resources.getColor(R.color.dark_clr))
                .setMultiline(true,5)
                .enableCounter(true,500)
                .onPositive("Go To My Account") {
                    toast("$it")
                }
        }


        showListAlertButton.setOnClickListener {
           val list =  listOf("yes","No").toTypedArray()
            showListAlertMaterial(list,"this is test????"){
                toast(list[it])
            }
        }

        showListAlertButtonMaterial.setOnClickListener {
           val list =  listOf("yes","No").toTypedArray()
            showListAlert(list,"this is test????"){
                toast(list[it])
            }
        }





    }
}