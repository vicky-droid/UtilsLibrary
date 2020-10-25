package com.vigneshtheagarajan.utils.one.view

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.net.ParseException
import android.view.View
import android.widget.EditText
import android.widget.TextView
import com.vigneshtheagarajan.utils.R
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

/**
 * generate Date String in currentTImeMillis
 *
 * @param[format] optional. default is yyyy-MM-dd HH:mm:ss (2020-08-15 18:40:00)
 */
@JvmOverloads
fun nowDateTimeString(format: String? = "yyyy-MM-dd HH:mm:ss") = System.currentTimeMillis().asDateString(format)

@JvmOverloads
fun nowDateString(format: String? = "yyyy-MM-dd") = System.currentTimeMillis().asDateString(format)

@JvmOverloads
fun nowTimeHMSString(format: String? = "HH:mm:ss") = System.currentTimeMillis().asDateString(format)

@JvmOverloads
fun nowTimeHMString(format: String? = "HH:mm") = System.currentTimeMillis().asDateString(format)

@JvmOverloads
fun Date.asDateString(format: String? = "yyyy-MM-dd HH:mm:ss"): String = SimpleDateFormat(format, Locale.getDefault()).format(this)

@JvmOverloads
fun Long.asDateString(format: String? = "yyyy-MM-dd HH:mm:ss"): String = Date(this).asDateString(format)

private fun EditText.datepicker(context: Context, format: String?){
    var cal = Calendar.getInstance()

    DatePickerDialog(context , R.style.MyTimePickerDialogTheme,DatePickerDialog.OnDateSetListener { p0, y, m, d ->

        cal.set(Calendar.YEAR, y)
        cal.set(Calendar.MONTH, m)
        cal.set(Calendar.DAY_OF_MONTH, d)

        val sdf = SimpleDateFormat(format, Locale.ENGLISH)
        val dt=sdf.format(cal.time)
        this.setText(dt)

    },
        cal.get(Calendar.YEAR),
        cal.get(Calendar.MONTH),
        cal.get(Calendar.DAY_OF_MONTH))
        .show()
}

private fun TextView.datepicker(context: Context, format: String) {
    var cal = Calendar.getInstance()

    DatePickerDialog(
        context,
        R.style.MyTimePickerDialogTheme,
        DatePickerDialog.OnDateSetListener { p0, y, m, d ->

            cal.set(Calendar.YEAR, y)
            cal.set(Calendar.MONTH, m)
            cal.set(Calendar.DAY_OF_MONTH, d)

            val sdf = SimpleDateFormat(format, Locale.ENGLISH)
            val dt = sdf.format(cal.time)
            this.text = dt

        },
        cal.get(Calendar.YEAR),
        cal.get(Calendar.MONTH),
        cal.get(Calendar.DAY_OF_MONTH)
    )
        .show()
}

fun TextView.setTimepicker(format: String?="HH:mm") {

        val cal = Calendar.getInstance()

        val timeSetListener = TimePickerDialog.OnTimeSetListener { timePicker, hour, minute ->
            cal.set(Calendar.HOUR_OF_DAY, hour)
            cal.set(Calendar.MINUTE, minute)
            this.text = SimpleDateFormat(format,Locale.ENGLISH).format(cal.time)
        }

        this.setOnClickListener {
            TimePickerDialog(context,R.style.MyTimePickerDialogTheme, timeSetListener, cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), true).show()

        }

}

fun EditText.setTimepicker(format: String?="HH:mm") {

        val cal = Calendar.getInstance()

        val timeSetListener = TimePickerDialog.OnTimeSetListener { timePicker, hour, minute ->
            cal.set(Calendar.HOUR_OF_DAY, hour)
            cal.set(Calendar.MINUTE, minute)
            this.setText(SimpleDateFormat(format,Locale.ENGLISH).format(cal.time))
        }

        this.setOnClickListener {
            TimePickerDialog(context,
                R.style.MyTimePickerDialogTheme, timeSetListener, cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), true).show()

        }

}


fun View.setDatePickerET(format:String? = "dd-MM-yyyy") {
    this as EditText
    this.setOnClickListener {
        this.datepicker(this.context,format)
    }
}

fun View.setDatePickerTV(format:String? = "dd-MM-yyyy") {
    this as TextView
    this.setOnClickListener {
        this.datepicker(this.context,format!!)
    }
}


fun getAge(year: Int, month: Int, day: Int): String? {
    val dob = Calendar.getInstance()
    val today = Calendar.getInstance()
    dob[year, month] = day
    var age = today[Calendar.YEAR] - dob[Calendar.YEAR]
    if (today[Calendar.DAY_OF_YEAR] < dob[Calendar.DAY_OF_YEAR]) {
        age--
    }
    val ageInt = age
    return ageInt.toString()
}

fun getDaysBetweenDates(start: String?, end: String?): Long {
    val myFormat = "dd-MM-yyyy" // mention the format you need

    val dateFormat = SimpleDateFormat(myFormat, Locale.ENGLISH)
    val startDate: Date
    val endDate: Date
    var numberOfDays: Long = 0
    try {
        startDate = dateFormat.parse(start)
        endDate = dateFormat.parse(end)
        numberOfDays = getUnitBetweenDates(startDate, endDate, TimeUnit.DAYS)
    } catch (e: ParseException) {
        e.printStackTrace()
    }
//    var noNegative = Math.abs(numberOfDays)
//    return noNegative
    return numberOfDays
}

private fun getUnitBetweenDates(startDate: Date, endDate: Date, unit: TimeUnit): Long {
    val timeDiff = endDate.time - startDate.time
    return unit.convert(timeDiff, TimeUnit.MILLISECONDS)
}


