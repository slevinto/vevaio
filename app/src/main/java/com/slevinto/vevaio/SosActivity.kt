package com.slevinto.vevaio

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageButton
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity


class SosActivity : AppCompatActivity() {
    private lateinit var btnReturn: ImageButton
    private lateinit var btnEmergency: ImageButton
    private lateinit var btnEmergencyContactA: ImageButton
    private lateinit var btnEmergencyContactB: ImageButton
    private lateinit var btnCancelEmergencyA: ImageButton
    private lateinit var btnCancelEmergencyB: ImageButton
    private lateinit var addEmergency: Spinner
    private lateinit var btnSave: Button

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sos)

        btnReturn = findViewById(R.id.btn_return)
        btnReturn.setOnClickListener{
            val i = Intent(this, SettingsFirstActivity::class.java)
            startActivity(i)
        }

        btnEmergency = findViewById(R.id.btn_emergency)

        btnEmergencyContactA = findViewById(R.id.btn_emergency_a)
        btnEmergencyContactB = findViewById(R.id.btn_emergency_b)
        btnCancelEmergencyA = findViewById(R.id.btn_cancel_emergency_a)
        btnCancelEmergencyB = findViewById(R.id.btn_cancel_emergency_b)

        addEmergency = findViewById(R.id.add_caregiver_spinner)
        val objects = arrayOf<String?>(
            "Choose1", "Choose2", "Choose3"
        )

        val adapter: ArrayAdapter<*> = ArrayAdapter<Any?>(
            applicationContext, android.R.layout.simple_list_item_1, objects
        )
        addEmergency.setAdapter(adapter)

        addEmergency.setOnItemSelectedListener(this)

        btnSave = findViewById(R.id.btn_save)

    }
}

private fun Spinner.setOnItemSelectedListener(sosActivity: SosActivity) {

}
