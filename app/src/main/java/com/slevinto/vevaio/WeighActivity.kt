package com.slevinto.vevaio

import android.content.Intent
import android.graphics.Rect
import android.os.Bundle
import android.view.Gravity
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class WeighActivity : AppCompatActivity() {

    private lateinit var weigh: TextView
    private lateinit var kg: TextView
    private lateinit var lb: TextView
    private lateinit var btnContinue: Button
    private var kgChoose: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weigh)

        weigh = findViewById(R.id.tv_weight)
        kg = findViewById(R.id.tv_kg)
        lb = findViewById(R.id.tv_lb)
        btnContinue = findViewById(R.id.btn_continue)

        weigh.setTextColor(resources.getColor(R.color.white))
        lb.setTextColor(resources.getColor(R.color.border))

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
                btnContinue.background = ContextCompat.getDrawable(this, R.drawable.button_rectangle_up)
                btnContinue.gravity = Gravity.END or Gravity.CENTER_VERTICAL
                btnContinue.setPadding(0, 0, 60, 0)
            } else {
                btnContinue.layoutParams = originalLayoutParams
                btnContinue.background = ContextCompat.getDrawable(this, R.drawable.button_rectangle)
                btnContinue.gravity = originalGravity
                btnContinue.setPadding(0, 0, 0, 0)
            }
        }

        kg.setOnClickListener {
            if (!kgChoose)
            {
                kgChoose = true
                if (weigh.text.toString().isNotEmpty())
                {
                    val converted = (weigh.text.toString().toDouble() / 2.2)
                    weigh.text = String.format("%.1f", converted)
                }
            }
            lb.setTextColor(resources.getColor(R.color.border))
            kg.setTextColor(resources.getColor(R.color.white))
        }

        lb.setOnClickListener {
            if (kgChoose)
            {
                kgChoose = false
                if (weigh.text.toString().isNotEmpty())
                {
                    val converted = (weigh.text.toString().toDouble() * 2.2)
                    weigh.setText(String.format("%.1f", converted))
                }
            }
            lb.setTextColor(resources.getColor(R.color.white))
            kg.setTextColor(resources.getColor(R.color.border))
        }

        btnContinue.setOnClickListener {
            val i = Intent(this, HeightActivity::class.java)
            val b = Bundle()
            b.putString("email", intent.extras?.getString("email"))
            b.putString("password", intent.extras?.getString("password"))
            b.putString("displayname", intent.extras?.getString("displayname"))
            b.putString("phone", intent.extras?.getString("phone"))
            b.putString("code", intent.extras?.getString("code"))
            i.putExtras(b)
            startActivity(i)
        }
    }
}