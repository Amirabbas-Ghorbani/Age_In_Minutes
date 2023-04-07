package com.amir.ageinminutescalculator

import android.app.DatePickerDialog
import android.media.tv.TvView
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import org.w3c.dom.Text
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private var tvSelectedDate: TextView? = null
    private var tvSelectedDateInMinutes: TextView? = null
    private var tvSelectedDateInSeconds: TextView? = null
    private var tvSelectedDateInHours: TextView? = null
    private var tvSelectedDateInDays: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonDatePicker : Button = findViewById(R.id.buttonDatePicker)
        tvSelectedDate = findViewById(R.id.tvSelectedDate)
        tvSelectedDateInSeconds = findViewById(R.id.tvSelectedDateInSeconds)
        tvSelectedDateInMinutes = findViewById(R.id.tvSelectedDateInMinutes)
        tvSelectedDateInHours = findViewById(R.id.tvSelectedDateInHours)
        tvSelectedDateInDays = findViewById(R.id.tvSelectedDateInDays)
        val tvSelectedDateText: TextView = findViewById(R.id.tvSelectedDateText)
        val tvSelectedDateInSecondsText: TextView = findViewById(R.id.tvSelectedDateInSecondsText)
        val tvSelectedDateInMinutesText: TextView = findViewById(R.id.tvSelectedDateInMinutesText)
        val tvSelectedDateInHoursText: TextView = findViewById(R.id.tvSelectedDateInHoursText)
        val tvSelectedDateInDaysText: TextView = findViewById(R.id.tvSelectedDateInDaysText)

        buttonDatePicker.setOnClickListener {
            clickDatePicker()
            tvSelectedDateText.text = "Selected date"
            tvSelectedDateInSecondsText.text = "Your Age In Seconds"
            tvSelectedDateInMinutesText.text = "Yor Age In Minutes"
            tvSelectedDateInHoursText.text = "Your Age In Hours"
            tvSelectedDateInDaysText.text = "Your Age In Days"
        }

    }

    private fun clickDatePicker(){
        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)
        val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener{_, selectedYear, selectedMonth, selectedDayOfMonth ->
            Toast.makeText(this, "Year was $selectedYear, Month was ${selectedMonth+1}, Day of Month was $selectedDayOfMonth"
                , Toast.LENGTH_LONG).show()
            val selectedDate = "$selectedDayOfMonth/${selectedMonth+1}/$selectedYear"
            tvSelectedDate?.text = selectedDate //or: tvSelectedDate?.setText(selectedDate)
            val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
            val theDate = sdf.parse(selectedDate)
            theDate?.let {
                val selectedDateInSeconds = theDate.time / 1_000
                val selectedDateInMinutes = theDate.time / 60_000
                val selectedDateInHours = theDate.time / 3_600_000
                val selectedDateInDays = theDate.time / 86_400_000
                val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
                currentDate?.let {
                    val currentDateInSeconds = currentDate.time / 1_000
                    val currentDateInMinutes = currentDate.time / 60000
                    val currentDateInHours = currentDate.time / 3_600_000
                    val currentDateInDays = currentDate.time / 86_400_000
                    val differenceInSeconds = currentDateInSeconds - selectedDateInSeconds
                    val differenceInMinutes = currentDateInMinutes - selectedDateInMinutes
                    val differenceInHours = currentDateInHours - selectedDateInHours
                    val differenceInDays = currentDateInDays - selectedDateInDays

                    tvSelectedDateInSeconds?.text = differenceInSeconds.toString()
                    tvSelectedDateInMinutes?.text = differenceInMinutes.toString()
                    tvSelectedDateInHours?.text = differenceInHours.toString()
                    tvSelectedDateInDays?.text = differenceInDays.toString() }
               }

        }, year, month, day)
        dpd.datePicker.maxDate = System.currentTimeMillis() - 86_400_000
        dpd.show()
    }


}