package com.slevinto.vevaio

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.github.mikephil.charting.formatter.ValueFormatter
import java.text.SimpleDateFormat
import java.util.*


class FormatTimestamp : ValueFormatter() {

    override fun getFormattedValue( value: Float ): String
    {
        return SimpleDateFormat("dd").format(Date(value.toLong())) //+ SimpleDateFormat("E").format(Date(value.toLong()))
    }
}

class OverviewActivity : AppCompatActivity() {

    private lateinit var btnSettings: ImageButton
    private lateinit var btnIndicators: Button
    private lateinit var tvHello: TextView
    private lateinit var btnSOS: ImageButton
    private lateinit var btnMedical: Button
    private var mHorizontalScrollView: HorizontalScrollView? = null
    private var mContainer: LinearLayout? = null

    var name = ""
    var displayname = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_overview)
        btnSettings = findViewById(R.id.btn_settings)
        btnIndicators= findViewById(R.id.btn_indicators)
        mHorizontalScrollView = findViewById(R.id.horizontalScrollView);
        mContainer = findViewById(R.id.container);

        val b = intent.extras

        if (b != null)
        {
            name = b.getString("name").toString()
            displayname = b.getString("displayname").toString()
        }

        btnSettings.setOnClickListener {
            val i = Intent(this, SettingsFirstActivity::class.java)
            val bndl = Bundle()
            bndl.putString("displayname", displayname)
            i.putExtras(bndl)
            startActivity(i)
        }
        btnSOS = findViewById(R.id.sos)
        btnSOS.setOnClickListener{
            val ia = Intent(this, EmergencySOSActivity::class.java)
            startActivity(ia)
        }

        btnMedical = findViewById(R.id.medical)
        btnMedical.setOnClickListener{
            val ib = Intent(this, MedicalInfoThirdActivity::class.java)
            startActivity(ib)
        }
	
        btnIndicators.setOnClickListener {
            val i = Intent(this, IndicatorsActivity::class.java)
            val bndl = Bundle()
            bndl.putString("displayname", displayname)
            i.putExtras(bndl)
            startActivity(i)
        }

        tvHello = findViewById(R.id.tv_hello)
        tvHello.text = "${tvHello.text} ${displayname}"
    }
}

