package com.slevinto.vevaio

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class SupportActivity : AppCompatActivity() {
    private lateinit var btnReturn: ImageButton
    private lateinit var firstName: EditText
    private lateinit var lastName: EditText
    private lateinit var spEmail: Spinner
    private lateinit var btnSend: Button

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_support)

        btnReturn = findViewById(R.id.btn_return)
        btnReturn.setOnClickListener{
            val i = Intent(this, SettingsFirstActivity::class.java)
            startActivity(i)
        }

        firstName = findViewById(R.id.et_firstname)
        lastName = findViewById(R.id.et_lastname)

        spEmail = findViewById(R.id.spinner_email_address)
        val objects = arrayOf<String?>(
            "Email1", "Email2", "Email3"
        )

        val adapter: ArrayAdapter<*> = ArrayAdapter<Any?>(
            applicationContext, android.R.layout.simple_list_item_1, objects
        )
        spEmail.setAdapter(adapter)

        spEmail.setOnItemSelectedListener(this)


        btnSend = findViewById(R.id.btn_send_button)

    }
}

private fun Spinner.setOnItemSelectedListener(supportActivity: SupportActivity) {

}



