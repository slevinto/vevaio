package com.slevinto.vevaio

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SettingsFirstActivity : AppCompatActivity() {
    private lateinit var btnReturn: ImageButton
    private lateinit var btnPerson: ImageButton
    private lateinit var nameSettings: TextView
    private lateinit var personalInfo: TextView
    private lateinit var btnArrowForwardName: ImageButton
    private lateinit var btnDevices: ImageButton
    private lateinit var btnArrowForwardDevices: ImageButton
    private lateinit var btnCaregivers: ImageButton
    private lateinit var btnArrowForwardCaregiver: ImageButton
    private lateinit var btnSos: ImageButton
    private lateinit var btnArrowForwardSos: ImageButton
    private lateinit var btnLanguage: ImageButton
    private lateinit var btnArrowForwardLanguage: ImageButton
    private lateinit var btnFaq: ImageButton
    private lateinit var btnArrowForwardFaq: ImageButton
    private lateinit var btnSupport: ImageButton
    private lateinit var btnArrowForwardSupport: ImageButton
    var displayname = ""

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings_first)

        val b = intent.extras

        if (b != null)
        {
            displayname = b.getString("displayname").toString()
        }

        btnReturn = findViewById(R.id.btn_return)
        btnReturn.setOnClickListener{
            val i = Intent(this, OverviewActivity::class.java)
            val bndl = Bundle()
            bndl.putString("displayname", displayname)
            i.putExtras(bndl)
            startActivity(i)
        }
        btnPerson = findViewById(R.id.btn_caregiver_b)
        nameSettings = findViewById(R.id.tv_namesettings)
        nameSettings.text = "${displayname}"
        personalInfo = findViewById(R.id.tv_personalinfo)
        btnArrowForwardName = findViewById(R.id.btn_arrow_forward_name)
        btnArrowForwardName.setOnClickListener{
            val ia = Intent(this, MyAccountSettingActivity::class.java)
            startActivity(ia)
        }
        btnDevices = findViewById(R.id.btn_devices)
        btnArrowForwardDevices = findViewById(R.id.btn_cancel_caregiver_b)
        btnArrowForwardDevices.setOnClickListener{
            val ib = Intent(this, DevicesActivity::class.java)
            startActivity(ib)
        }
        btnCaregivers = findViewById(R.id.btn_caregivers)
        btnArrowForwardCaregiver = findViewById(R.id.btn_arrow_forward_caregiver)
        btnArrowForwardCaregiver.setOnClickListener{
            val ic = Intent(this, CaregiversActivity::class.java)
            startActivity(ic)
        }
        btnSos = findViewById(R.id.btn_sos)
        btnArrowForwardSos = findViewById(R.id.btn_arrow_forward_sos)
        btnArrowForwardSos.setOnClickListener{
            val ie = Intent(this, SosActivity::class.java)
            startActivity(ie)
        }
        btnLanguage = findViewById(R.id.btn_languages)
        btnArrowForwardLanguage = findViewById(R.id.btn_arrow_forward_language)
        btnArrowForwardLanguage.setOnClickListener{
            val ig = Intent(this, LanguageActivity::class.java)
            startActivity(ig)
        }
        btnFaq = findViewById(R.id.btn_faq)
        btnArrowForwardFaq = findViewById(R.id.btn_arrow_forward_faq)
        btnArrowForwardFaq.setOnClickListener{
            val ih = Intent(this, FAQActivity::class.java)
            startActivity(ih)
        }

        btnSupport = findViewById(R.id.btn_support)

        btnArrowForwardSupport = findViewById(R.id.btn_arrow_forward_support)
        btnArrowForwardSupport.setOnClickListener{
            val ii = Intent(this, SupportActivity::class.java)
            startActivity(ii)
        }
    }
}