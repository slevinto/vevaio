package com.slevinto.vevaio

import android.content.Intent
import android.graphics.Rect
import android.os.Bundle
import android.view.Gravity
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.hbb20.CountryCodePicker

class RegisterMobileActivity : AppCompatActivity() {

    private lateinit var country: CountryCodePicker
    private lateinit var phoneNumber: EditText
    private lateinit var btnContinue: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_mobile)

        country = findViewById(R.id.country_picker)
        btnContinue = findViewById(R.id.btn_continue)
        phoneNumber = findViewById(R.id.et_phone)

        val originalLayoutParams = btnContinue.layoutParams
        val originalGravity = btnContinue.gravity
        this.window.decorView.rootView.viewTreeObserver.addOnGlobalLayoutListener {
            val r = Rect()
            this.window.decorView.rootView.getWindowVisibleDisplayFrame(r)
            if (this.window.decorView.rootView.height - (r.bottom - r.top) > 500) { // if more than 100 pixels, its probably a keyboard...
                //onKeyboardShow()
                btnContinue.layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                )
                btnContinue.background =
                    ContextCompat.getDrawable(this, R.drawable.button_rectangle_up)
                btnContinue.gravity = Gravity.END or Gravity.CENTER_VERTICAL
                btnContinue.setPadding(0, 0, 60, 0)
            } else {
                btnContinue.layoutParams = originalLayoutParams
                btnContinue.background =
                    ContextCompat.getDrawable(this, R.drawable.button_rectangle)
                btnContinue.gravity = originalGravity
                btnContinue.setPadding(0, 0, 0, 0)
            }
        }

        btnContinue.setOnClickListener {
            val i = Intent(this, EmergencyActivity::class.java)
            val b = Bundle()
            b.putString("email", intent.extras?.getString("email"))
            b.putString("password", intent.extras?.getString("password"))
            b.putString("displayname", intent.extras?.getString("displayname"))
            b.putString("phone", phoneNumber.text.toString())
            b.putString("code", country.selectedCountryCode)
            i.putExtras(b)
            startActivity(i)
        }
    }
}