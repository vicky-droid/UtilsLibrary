package com.vigneshtheagarajan.utilslibrary

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.vigneshtheagarajan.utils.one.setDatePickerET
import com.vigneshtheagarajan.utils.one.setTimepicker
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
        rv_data.setOnClickListener {

        }

    }
}