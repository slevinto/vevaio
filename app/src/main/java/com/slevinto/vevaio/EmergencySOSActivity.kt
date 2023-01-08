package com.slevinto.vevaio

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView

class EmergencySOSActivity : AppCompatActivity() {
    private lateinit var btnReturn: ImageButton
    private lateinit var btnSOSSpeaker: ImageButton
    private lateinit var btnClose: ImageButton
    private lateinit var btnCancel: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_emergency_sosactivity)

        btnReturn = findViewById(R.id.btn_return)
        btnReturn.setOnClickListener{
            val i = Intent(this, OverviewActivity::class.java)
            startActivity(i)
        }

        btnSOSSpeaker = findViewById(R.id.sos_speaker)
        btnClose = findViewById(R.id.btn_close)
        btnCancel = findViewById(R.id.tv_cancel)

    }
}