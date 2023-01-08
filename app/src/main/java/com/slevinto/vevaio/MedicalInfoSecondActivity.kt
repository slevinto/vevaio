package com.slevinto.vevaio

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MedicalInfoSecondActivity : AppCompatActivity() {
    private lateinit var btnReturn: ImageButton
    private lateinit var btnMinus: ImageButton
    private lateinit var dozeNumber: TextView
    private lateinit var btnPlus: ImageButton
    private lateinit var takePerDay: Spinner
    private lateinit var takeTime: Spinner
    private lateinit var alertEveryDay: Switch
    private lateinit var btnSave: Button


    @SuppressLint("MissingInflatedId", "ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_medical_info_second)

        btnReturn = findViewById(R.id.btn_return)
        btnReturn.setOnClickListener{
            val i = Intent(this, MedicalInfoFirstActivity::class.java)
            startActivity(i)
        }

        btnMinus = findViewById(R.id.btn_minus)
        btnPlus = findViewById(R.id.btn_plus)
        dozeNumber = findViewById(R.id.et_number)

        var counter = Integer.parseInt(dozeNumber.text.toString())

        btnMinus.setOnClickListener{
            if (counter == 0) counter = 0
            else counter--

            dozeNumber.text = counter.toString()
        }

        btnPlus.setOnClickListener{
            counter++
            dozeNumber.text = counter.toString()
        }

        takePerDay = findViewById(R.id.take_per_day)
        val objects = arrayOf<String?>(
            "one time", "two times", "three times", "four times", "five times"
        )

        val adapter: ArrayAdapter<*> = ArrayAdapter<Any?>(
            applicationContext, android.R.layout.simple_list_item_1, objects
        )
        takePerDay.setAdapter(adapter)

        takePerDay.setOnItemSelectedListener(this)

        takeTime = findViewById(R.id.take_time)
        val objectsa = arrayOf<String?>(
            "8:00", "9:00", "10:00", "11:00", "12:00", "13:00", "14:00"
        )

        val adaptera: ArrayAdapter<*> = ArrayAdapter<Any?>(
            applicationContext, android.R.layout.simple_list_item_1, objectsa
        )
        takeTime.setAdapter(adaptera)

        takeTime.setOnItemSelectedListener(this)

        alertEveryDay = findViewById(R.id.sw_alert)
        btnSave = findViewById(R.id.btn_save)

    }

}

private fun Spinner.setOnItemSelectedListener(medicalInfoSecondActivity: MedicalInfoSecondActivity) {

}

