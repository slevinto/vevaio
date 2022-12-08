package com.slevinto.vevaio

import android.app.DatePickerDialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.text.DateFormatSymbols
import java.util.*

class BirthdayActivity : AppCompatActivity() {

    private lateinit var dateText: TextView
    private lateinit var btnContinue: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_birthday)

        dateText = findViewById(R.id.tv_word_date)

        btnContinue = findViewById(R.id.btn_continue)

        val c = Calendar.getInstance()
        val yearValue = c.get(Calendar.YEAR)
        val monthValue = c.get(Calendar.MONTH)
        val dayValue = c.get(Calendar.DAY_OF_MONTH)
        val dpd = DatePickerDialog(this, android.R.style.Theme_Holo_Light_Dialog_MinWidth,
            { _, year, monthOfYear, dayOfMonth ->  (dayOfMonth.toString() + " " + DateFormatSymbols().months[monthOfYear].substring(0, 3) + " " + year.toString()).also { dateText.text = it }
            }, yearValue, monthValue, dayValue)
        dpd.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        dateText.setOnClickListener {
            dpd.show()
        }

        btnContinue.setOnClickListener {
            val i = Intent(this, WeighActivity::class.java)
            val b = Bundle()
            b.putString("email", intent.extras?.getString("email"))
            b.putString("password", intent.extras?.getString("password"))
            b.putString("displayname", intent.extras?.getString("displayname"))
            b.putString("phone", intent.extras?.getString("phone"))
            b.putString("code", intent.extras?.getString("code"))
            i.putExtras(b)
            startActivity(i)
        }
    }
}