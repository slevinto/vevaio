package com.slevinto.vevaio

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
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
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.slevinto.vevaio.SetDeviceActivity.Companion.showToast

class LoginActivity : AppCompatActivity() {

    private lateinit var registerLink: TextView
    private lateinit var llText: LinearLayout
    private lateinit var etEmail: EditText
    private lateinit var etPassword: EditText
    private lateinit var btnLogin: Button

    private val sharedPrefFile = "kotlinsharedpreference"
    private var mAuth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        registerLink = findViewById(R.id.tv_register_click)
        llText = findViewById(R.id.ll_text)
        etEmail = findViewById(R.id.et_email)
        etPassword = findViewById(R.id.et_password)
        btnLogin = findViewById(R.id.btn_send)

        val originalLayoutParams = btnLogin.layoutParams
        val originalGravity = btnLogin.gravity
        this.window.decorView.rootView.viewTreeObserver.addOnGlobalLayoutListener {
            val r = Rect()
            this.window.decorView.rootView.getWindowVisibleDisplayFrame(r)
            if (this.window.decorView.rootView.height - (r.bottom - r.top) > 500) { // if more than 100 pixels, its probably a keyboard...
                //onKeyboardShow()
                llText.visibility = LinearLayout.GONE
                btnLogin.layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                )
                btnLogin.background = ContextCompat.getDrawable(this, R.drawable.button_rectangle_up)
                btnLogin.gravity = Gravity.END or Gravity.CENTER_VERTICAL
                btnLogin.setPadding(0, 0, 60, 0)
            } else {
                llText.visibility = LinearLayout.VISIBLE
                btnLogin.layoutParams = originalLayoutParams
                btnLogin.background = ContextCompat.getDrawable(this, R.drawable.button_rectangle)
                btnLogin.gravity = originalGravity
                btnLogin.setPadding(0, 0, 0, 0)
            }
        }

        val string = SpannableString(registerLink.text)
        val clickableSpan: ClickableSpan = object : ClickableSpan() {
            override fun onClick(widget: View) {
                val i = Intent(this@LoginActivity, RegisterEmailActivity::class.java)
                startActivity(i)
            }
        }
        string.setSpan(clickableSpan, 0, string.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        string.setSpan(StyleSpan(Typeface.BOLD), 0, registerLink.text.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        string.setSpan(ForegroundColorSpan(getColor(R.color.white)), 0, registerLink.text.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        registerLink.text = string
        registerLink.movementMethod = LinkMovementMethod.getInstance()

        val sharedPreferences: SharedPreferences = this.getSharedPreferences(sharedPrefFile, Context.MODE_PRIVATE)
        etEmail.setText(sharedPreferences.getString("email", ""))
        etPassword.setText(sharedPreferences.getString("password", ""))

        btnLogin.setOnClickListener {
            mAuth = FirebaseAuth.getInstance()

            var partnerId = etEmail.text.toString()
            if (!TextUtils.isEmpty(partnerId) && android.util.Patterns.EMAIL_ADDRESS.matcher(partnerId).matches())
            {
                val re = Regex("[^A-Za-z0-9 ]")
                partnerId = re.replace(partnerId, "")

                val database = FirebaseDatabase.getInstance()
                val ref = database.getReference("users").child(partnerId).child("info")
                ref.addValueEventListener(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        if (snapshot.value != null)
                        {
                            val dataElem = snapshot.value as HashMap<*, *>
                            if (dataElem["firstname"] != null)
                                loginWithEmail(etEmail.text.toString(), etPassword.text.toString(), dataElem["firstname"].toString())
                            else
                            {
                                showToast("Sign in failed. No such patient.") {}
                                etEmail.setText("")
                                etPassword.setText("")
                            }
                        }
                        else
                        {
                            showToast("Sign in failed. No such patient.") {}
                            etEmail.setText("")
                            etPassword.setText("")
                        }
                    }

                    override fun onCancelled(error: DatabaseError) {
                        TODO("Not yet implemented")
                    }
                })
            }
            else
            {
                showToast("Email is not valid.") {}
                etEmail.setText("")
                etPassword.setText("")
                etEmail.requestFocus()
            }
        }
    }

    private fun loginWithEmail(email: String, password: String, displayname: String)
    {
        val sharedPreferences: SharedPreferences = this.getSharedPreferences(sharedPrefFile,
            Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putString("email", email)
        editor.putString("password", password)
        editor.apply()

        mAuth!!.signInWithEmailAndPassword(email, password).addOnCompleteListener(this)
        { task ->
            if (task.isSuccessful) {
                // Sign in success, update UI with the signed-in user's information
                var partnerId = email
                val re = Regex("[^A-Za-z0-9 ]")
                partnerId = re.replace(partnerId, "")

                val i = Intent(this, OverviewActivity::class.java)
                val bndl = Bundle()
                bndl.putString("name", partnerId)
                bndl.putString("displayname", displayname)
                i.putExtras(bndl)
                startActivity(i)
            } else {
                // If sign in fails, display a message to the user.
                showToast("Sign in failed. " + task.exception?.message) {}
                etEmail.setText("")
                etPassword.setText("")
            }
        }
    }
}