package com.slevinto.vevaio

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageButton
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity


class CaregiversActivity : AppCompatActivity() {
    private lateinit var btnReturn: ImageButton
    private lateinit var btnDoctorA: ImageButton
    private lateinit var btnDoctorB: ImageButton
    private lateinit var btnCancelCaregiverA: ImageButton
    private lateinit var btnCancelCaregiverB: ImageButton
    private lateinit var caregivers: Spinner
    private lateinit var btnSave: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_caregivers)

        btnReturn = findViewById(R.id.btn_return)
        btnReturn.setOnClickListener{
            val i = Intent(this, SettingsFirstActivity::class.java)
            startActivity(i)
        }

        btnDoctorA = findViewById(R.id.btn_caregiver_a)
        btnDoctorA.setOnClickListener{
            val ia = Intent(this, CaregiverFirstActivity::class.java)
            startActivity(ia)
        }

        btnDoctorB = findViewById(R.id.btn_caregiver_b)

        btnCancelCaregiverA = findViewById(R.id.btn_cancel_caregiver_a)
        btnCancelCaregiverB = findViewById(R.id.btn_cancel_caregiver_b)

        caregivers = findViewById(R.id.add_caregiver_spinner)
        val objects = arrayOf<String?>(
            "Doctor1", "Doctor2", "Doctor3"
        )

        val adapter: ArrayAdapter<*> = ArrayAdapter<Any?>(
            applicationContext, android.R.layout.simple_list_item_1, objects
        )
        caregivers.setAdapter(adapter)

        caregivers.setOnItemSelectedListener(this)

        btnSave = findViewById(R.id.btn_save)
    }
}

private fun Spinner.setOnItemSelectedListener(caregiversActivity: CaregiversActivity) {

}
