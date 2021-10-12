package com.vigneshtheagarajan.utilslibrary

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.vigneshtheagarajan.utils.one.openActivity
import com.vigneshtheagarajan.utils.one.view.setDatePickerET
import com.vigneshtheagarajan.utils.one.view.setTimepicker
import com.vigneshtheagarajan.utilslibrary.app.ViewHelperActivity
import com.vigneshtheagarajan.utilslibrary.app.network.NetworkActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        picker()
    }

    private fun picker() {
        time.setTimepicker()
        date.setDatePickerET()
        network.setOnClickListener {
            openActivity<NetworkActivity>()
        }
        viewHelper.setOnClickListener {
            openActivity<ViewHelperActivity>()
        }

    }


}




