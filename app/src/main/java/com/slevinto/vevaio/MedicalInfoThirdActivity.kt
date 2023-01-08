package com.slevinto.vevaio

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.Switch
import androidx.appcompat.app.AppCompatActivity

class MedicalInfoThirdActivity : AppCompatActivity() {
    private lateinit var btnReturn: ImageButton
    private lateinit var medicineFirst: Switch
    private lateinit var medicineSecond: Switch
    private lateinit var medicineThird: Switch
    private lateinit var btnLog: Button
    private lateinit var btnSave: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_medical_info_third)

        btnReturn = findViewById(R.id.btn_return)
        btnReturn.setOnClickListener{
            val i = Intent(this, MedicalInfoFirstActivity::class.java)
            startActivity(i)
        }
        medicineFirst = findViewById(R.id.sw_medicine_first)
        medicineSecond = findViewById(R.id.sw_medicine_second)
        medicineThird = findViewById(R.id.sw_medicine_third)
        btnLog = findViewById(R.id.btn_log)
        btnSave = findViewById(R.id.btn_add_medicine)
    }
}