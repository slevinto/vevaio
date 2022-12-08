package com.slevinto.vevaio

import android.content.Intent
import android.graphics.Rect
import android.os.Bundle
import android.view.Gravity
import android.widget.Button
import android.widget.LinearLayout
import android.widget.RelativeLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class EmergencyActivity : AppCompatActivity() {

    private lateinit var btnContinue: Button
    private lateinit var btnSkip: Button
    private lateinit var llBtns: RelativeLayout

    private fun Button.below() {
        (this.layoutParams as? RelativeLayout.LayoutParams)?.removeRule(RelativeLayout.BELOW)
    }

    private fun Button.alignstart() {
        (this.layoutParams as? RelativeLayout.LayoutParams)?.addRule(RelativeLayout.ALIGN_START, R.id.btn_continue)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_emergency)

        btnContinue = findViewById(R.id.btn_continue)
        btnSkip = findViewById(R.id.btn_skip)
        llBtns = findViewById(R.id.btns)

        val originalContinueLayoutParams = btnContinue.layoutParams
        val originalContinueGravity = btnContinue.gravity
        val originalSkipLayoutParams = btnSkip.layoutParams
        val originalSkipGravity = btnSkip.gravity
        val originalLayoutParams = llBtns.layoutParams
        this.window.decorView.rootView.viewTreeObserver.addOnGlobalLayoutListener {
            val r = Rect()
            this.window.decorView.rootView.getWindowVisibleDisplayFrame(r)
            if (this.window.decorView.rootView.height - (r.bottom - r.top) > 500) { // if more than 100 pixels, its probably a keyboard...

                btnContinue.background = ContextCompat.getDrawable(this, R.drawable.button_rectangle_up)
                btnContinue.gravity = Gravity.END or Gravity.CENTER_VERTICAL
                btnContinue.setPadding(0, 0, 60, 0)

                btnSkip.gravity = Gravity.START or Gravity.CENTER_VERTICAL
                btnSkip.setPadding(60, 0, 0, 0)

                (btnSkip.layoutParams as? RelativeLayout.LayoutParams)?.removeRule(RelativeLayout.BELOW)
                (btnSkip.layoutParams as? RelativeLayout.LayoutParams)?.addRule(RelativeLayout.ALIGN_START, R.id.btn_continue)

                val params = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                )
                params.setMargins(0, 0, 0, 0)
                llBtns.layoutParams = params
            } else {
                btnContinue.layoutParams = originalContinueLayoutParams
                btnContinue.background = ContextCompat.getDrawable(this, R.drawable.button_rectangle)
                btnContinue.gravity = originalContinueGravity
                btnContinue.setPadding(0, 0, 0, 0)

                btnSkip.layoutParams = originalSkipLayoutParams
                btnSkip.background = ContextCompat.getDrawable(this, R.drawable.button_skip)
                btnSkip.gravity = originalSkipGravity
                btnSkip.setPadding(0, 0, 0, 0)

                (btnSkip.layoutParams as? RelativeLayout.LayoutParams)?.removeRule(RelativeLayout.ALIGN_START)
                (btnSkip.layoutParams as? RelativeLayout.LayoutParams)?.addRule(RelativeLayout.BELOW, R.id.btn_continue)

                llBtns.layoutParams = originalLayoutParams
            }
        }

        btnContinue.setOnClickListener {
            val i = Intent(this, GenderActivity::class.java)
            val b = Bundle()
            b.putString("email", intent.extras?.getString("email"))
            b.putString("password", intent.extras?.getString("password"))
            b.putString("displayname", intent.extras?.getString("displayname"))
            b.putString("phone", intent.extras?.getString("phone"))
            b.putString("code", intent.extras?.getString("code"))
            i.putExtras(b)
            startActivity(i)
        }

        btnSkip.setOnClickListener {
            val i = Intent(this, GenderActivity::class.java)
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