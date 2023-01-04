package com.slevinto.vevaio

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity


class CaregiversActivity : AppCompatActivity() {
    private lateinit var btnReturn: ImageButton
    private lateinit var btnDoctorA: ImageButton
    private lateinit var doctorNameA: TextView
    private lateinit var doctorTypeA: TextView
    private lateinit var doctorIndicatorsA: TextView
    private lateinit var btnDoctorB: ImageButton
    private lateinit var doctorNameB: TextView
    private lateinit var doctorTypeB: TextView
    private lateinit var doctorIndicatorsB: TextView
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

        doctorNameA = findViewById(R.id.tv_doctor_a)
        doctorNameA.setOnClickListener{
            val ib = Intent(this, CaregiverFirstActivity::class.java)
            startActivity(ib)
        }
        doctorTypeA = findViewById(R.id.tv_doctor_type_a)
        doctorTypeA.setOnClickListener{
            val ic = Intent(this, CaregiverFirstActivity::class.java)
            startActivity(ic)
        }
        doctorIndicatorsA = findViewById(R.id.tv_indicators_a)
        doctorIndicatorsA.setOnClickListener{
            val id = Intent(this, CaregiverFirstActivity::class.java)
            startActivity(id)
        }

        btnDoctorB = findViewById(R.id.btn_caregiver_b)
        btnDoctorB.setOnClickListener{
            val ib = Intent(this, CaregiverFirstActivity::class.java)
            startActivity(ib)
        }

        doctorNameB = findViewById(R.id.tv_doctor_b)
        doctorNameB.setOnClickListener{
            val ie = Intent(this, CaregiverFirstActivity::class.java)
            startActivity(ie)
        }
        doctorTypeB = findViewById(R.id.tv_doctor_type_b)
        doctorTypeB.setOnClickListener{
            val ig = Intent(this, CaregiverFirstActivity::class.java)
            startActivity(ig)
        }
        doctorIndicatorsB = findViewById(R.id.tv_indicators_b)
        doctorIndicatorsB.setOnClickListener{
            val ih = Intent(this, CaregiverFirstActivity::class.java)
            startActivity(ih)
        }

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
