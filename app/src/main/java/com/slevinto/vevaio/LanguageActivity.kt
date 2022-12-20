package com.slevinto.vevaio

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity

class LanguageActivity : AppCompatActivity() {
    private lateinit var btnReturn: ImageButton
    private lateinit var btnEnglish: ImageButton
    private lateinit var checkBoxEnglish: CheckBox
    private lateinit var btnGerman: ImageButton
    private lateinit var btnSpanish: ImageButton
    private lateinit var btnHebrew: ImageButton
    private lateinit var btnSave: Button
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_language)

        btnReturn = findViewById(R.id.btn_return)
        btnReturn.setOnClickListener{
            val i = Intent(this, SettingsFirstActivity::class.java)
            startActivity(i)
        }

        btnEnglish = findViewById(R.id.btn_english)
        checkBoxEnglish = findViewById(R.id.check_box_english)
        btnGerman = findViewById(R.id.btn_german)
        btnSpanish = findViewById(R.id.btn_spanish)
        btnHebrew = findViewById(R.id.btn_hebrew)
        btnSave = findViewById(R.id.btn_save)
    }
}