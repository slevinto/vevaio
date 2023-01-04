package com.slevinto.vevaio

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.Switch
import androidx.appcompat.app.AppCompatActivity

class CaregiverFirstActivity : AppCompatActivity() {
    private lateinit var btnReturn: ImageButton
    private lateinit var switchStress: Switch
    private lateinit var switchBmi: Switch
    private lateinit var switchPa: Switch
    private lateinit var switchBoneMass: Switch
    private lateinit var switchProtein: Switch
    private lateinit var switchFluids: Switch
    private lateinit var switchWater: Switch
    private lateinit var btnSave: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_caregiver_first)

        btnReturn = findViewById(R.id.btn_return)
        btnReturn.setOnClickListener{
            val i = Intent(this, CaregiversActivity::class.java)
            startActivity(i)
        }

        switchStress = findViewById(R.id.sw_stress)
        switchBmi = findViewById(R.id.sw_bmi)
        switchPa = findViewById(R.id.sw_pa)
        switchBoneMass = findViewById(R.id.sw_bone_mass)
        switchProtein = findViewById(R.id.sw_protein)
        switchFluids = findViewById(R.id.sw_fluids)
        switchWater = findViewById(R.id.sw_water)
        btnSave = findViewById(R.id.btn_save)
    }
}