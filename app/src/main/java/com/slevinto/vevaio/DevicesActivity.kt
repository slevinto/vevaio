package com.slevinto.vevaio

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageButton
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity

class DevicesActivity : AppCompatActivity() {
    private lateinit var btnReturn: ImageButton
    private lateinit var btnArrowForwardDevices: ImageButton
    private lateinit var devices: Spinner
    private lateinit var btnSave: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_devices)

        btnReturn = findViewById(R.id.btn_return)
        btnReturn.setOnClickListener{
            val i = Intent(this, SettingsFirstActivity::class.java)
            startActivity(i)
        }

        btnArrowForwardDevices = findViewById(R.id.btn_cancel_caregiver_b)
        devices = findViewById(R.id.add_caregiver_spinner)
        val objects = arrayOf<String?>(
            "Germin", "Germin2", "Germin3"
        )

        val adapter: ArrayAdapter<*> = ArrayAdapter<Any?>(
            applicationContext, android.R.layout.simple_list_item_1, objects
        )
        devices.setAdapter(adapter)

        devices.setOnItemSelectedListener(this)

        btnSave = findViewById(R.id.btn_save)
    }
}

private fun Spinner.setOnItemSelectedListener(devices: DevicesActivity) {

}
