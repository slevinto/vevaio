package com.slevinto.vevaio

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.slevinto.vevaio.SetDeviceActivity.Companion.hideKeyboard
import com.slevinto.vevaio.SetDeviceActivity.Companion.showToast

class VerifyPhoneActivity : AppCompatActivity() {
    lateinit var code_1: EditText
    lateinit var code_2: EditText
    lateinit var code_3: EditText
    lateinit var code_4: EditText
    lateinit var code_5: EditText
    lateinit var code_6: EditText
    lateinit var phone: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_verify_phone)

        code_1 = findViewById(R.id.et_rectangle_1)
        code_2 = findViewById(R.id.et_rectangle_2)
        code_3 = findViewById(R.id.et_rectangle_3)
        code_4 = findViewById(R.id.et_rectangle_4)
        code_5 = findViewById(R.id.et_rectangle_5)
        code_6 = findViewById(R.id.et_rectangle_6)

        phone = findViewById(R.id.tv_phone_second)
        val bndl = intent.extras
        var num = ""
        if (bndl != null) {
            num = bndl.getString("phone", "")
        }
        phone.text = phone.text.toString() + num

        code_1.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {}

            override fun beforeTextChanged(s: CharSequence, start: Int,
                                           count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int,
                                       before: Int, count: Int) {
                if (s.isNotEmpty())
                {
                   code_2.requestFocus();
                }
            }
        })

        code_2.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {}
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (s.isNotEmpty())
                {
                    code_3.requestFocus();
                }
            }
        })

        code_3.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {}
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int,
                                       before: Int, count: Int) {
                if (s.isNotEmpty())
                {
                    code_4.requestFocus();
                }
            }
        })

        code_4.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {}
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (s.isNotEmpty())
                {
                    code_5.requestFocus();
                }
            }
        })

        code_5.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {}

            override fun beforeTextChanged(s: CharSequence, start: Int,
                                           count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int,
                                       before: Int, count: Int) {
                if (s.isNotEmpty())
                {
                    code_6.requestFocus();
                }
            }
        })

        code_6.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {
                if (s.isNotEmpty() &&
                    code_1.text.toString().isNotEmpty() &&
                    code_2.text.toString().isNotEmpty() &&
                    code_3.text.toString().isNotEmpty() &&
                    code_4.text.toString().isNotEmpty() &&
                    code_5.text.toString().isNotEmpty() ) {

                    var userInput = code_1.text.toString() + code_2.text.toString() + code_3.text.toString() + code_4.text.toString() + code_5.text.toString() + s.toString()
                    val bndl = intent.extras
                    var verificationId = ""
                    if (bndl != null) {
                        verificationId = bndl.getString("code", "")
                    }
                    var credential = PhoneAuthProvider.getCredential(verificationId, userInput)
                    signInWithPhoneAuthCredential(credential)
                }
            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
        })
    }

    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        Firebase.auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    hideKeyboard(this@VerifyPhoneActivity)
                    val user = task.result?.user
                    showToast("Register succeeded for phone number: ${user?.phoneNumber.toString()}") {}

                    val current_user = Firebase.auth.currentUser!!
                    current_user.delete()
                        .addOnCompleteListener { task_current ->
                            if (task_current.isSuccessful) {
                                val i = Intent(this, PersonalInfoActivity::class.java)
                                val b = Bundle()
                                b.putString("phone", intent.extras?.getString("phone"))
                                i.putExtras(b)
                                startActivity(i)
                                finish()
                            }
                        }
                } else {
                    // If sign in fails, display a message to the user.
                    hideKeyboard(this@VerifyPhoneActivity)
                    showToast("Register failed: ${task.exception?.localizedMessage.toString()}") {}
                    val i = Intent(this, CommonRegisterActivity::class.java)
                    startActivity(i)
                    finish()
                }
            }
    }
}