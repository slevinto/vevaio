package com.slevinto.vevaio

import android.content.Intent
import android.graphics.Rect
import android.graphics.Typeface
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.TextUtils
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.slevinto.vevaio.SetDeviceActivity.Companion.hideKeyboard
import com.slevinto.vevaio.SetDeviceActivity.Companion.showToast

open class RegisterEmailActivity : AppCompatActivity() {

    private lateinit var loginLink: TextView
    private lateinit var llText: LinearLayout
    private lateinit var etEmail: EditText
    private lateinit var etPassword: EditText
    private lateinit var etRepeatPassword: EditText
    private lateinit var btnContinue: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_email)

        loginLink = findViewById(R.id.tv_register_click)
        llText = findViewById(R.id.ll_text)
        etEmail = findViewById(R.id.et_email)
        etPassword = findViewById(R.id.et_password)
        etRepeatPassword = findViewById(R.id.et_repeat_password)
        btnContinue = findViewById(R.id.btn_continue)

        val originalLayoutParams = btnContinue.layoutParams
        val originalGravity = btnContinue.gravity
        this.window.decorView.rootView.viewTreeObserver.addOnGlobalLayoutListener {
            val r = Rect()
            this.window.decorView.rootView.getWindowVisibleDisplayFrame(r)
            if (this.window.decorView.rootView.height - (r.bottom - r.top) > 500) { // if more than 100 pixels, its probably a keyboard...
                //onKeyboardShow()
                llText.visibility = LinearLayout.GONE
                btnContinue.layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                )
                btnContinue.background = ContextCompat.getDrawable(this, R.drawable.button_rectangle_up)
                btnContinue.gravity = Gravity.END or Gravity.CENTER_VERTICAL
                btnContinue.setPadding(0, 0, 60, 0)
            } else {
                llText.visibility = LinearLayout.VISIBLE
                btnContinue.layoutParams = originalLayoutParams
                btnContinue.background = ContextCompat.getDrawable(this, R.drawable.button_rectangle)
                btnContinue.gravity = originalGravity
                btnContinue.setPadding(0, 0, 0, 0)
            }
        }

        val string = SpannableString(loginLink.text)
        val clickableSpan: ClickableSpan = object : ClickableSpan() {
            override fun onClick(widget: View) {
                val i = Intent(this@RegisterEmailActivity, LoginActivity::class.java)
                startActivity(i)
            }
        }
        string.setSpan(clickableSpan, 0, string.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        string.setSpan(StyleSpan(Typeface.BOLD), 0, loginLink.text.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        string.setSpan(ForegroundColorSpan(getColor(R.color.white)), 0, loginLink.text.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        loginLink.text = string
        loginLink.movementMethod = LinkMovementMethod.getInstance()

        btnContinue.setOnClickListener {
            if (etPassword.text.toString() != etRepeatPassword.text.toString())
            {
                hideKeyboard(this@RegisterEmailActivity)
                showToast("Password should be the same in both fields") {}
            }
            else if (etPassword.text.toString().length < 6)
            {
                hideKeyboard(this@RegisterEmailActivity)
                showToast("Password should be at least 6 symbols") {}
            }
            else if (TextUtils.isEmpty(etEmail.text.toString()) || !android.util.Patterns.EMAIL_ADDRESS.matcher(etEmail.text.toString()).matches())
            {
                hideKeyboard(this@RegisterEmailActivity)
                showToast("Email is not valid") {}
            }
            else {
                hideKeyboard(this@RegisterEmailActivity)
                val i = Intent(this, PersonalInfoActivity::class.java)
                val bndl = Bundle()
                bndl.putString("email", etEmail.text.toString())
                bndl.putString("password", etPassword.text.toString())
                i.putExtras(bndl)
                startActivity(i)
            }
        }
    }
}