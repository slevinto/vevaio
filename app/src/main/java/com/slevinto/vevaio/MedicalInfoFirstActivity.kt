package com.slevinto.vevaio

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageButton
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity

class MedicalInfoFirstActivity : AppCompatActivity() {
    private lateinit var btnReturn: ImageButton
    private lateinit var addMedicationOrSupplement: Spinner
    private lateinit var btnContinue: Button

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_medical_info_first)

        btnReturn = findViewById(R.id.btn_return)
        btnReturn.setOnClickListener{
            val i = Intent(this, OverviewActivity::class.java)
            startActivity(i)
        }
        addMedicationOrSupplement = findViewById(R.id.add_medicine_spinner)
        val objects = arrayOf<String?>(
            "Option1", "Option2", "Option3", "Option4"
        )

        val adapter: ArrayAdapter<*> = ArrayAdapter<Any?>(
            applicationContext, android.R.layout.simple_list_item_1, objects
        )
        addMedicationOrSupplement.setAdapter(adapter)

        addMedicationOrSupplement.setOnItemSelectedListener(this)

        btnContinue = findViewById(R.id.btn_continue)
    }
}

private fun Spinner.setOnItemSelectedListener(medicalInfoFirstActivity: MedicalInfoFirstActivity) {

}
