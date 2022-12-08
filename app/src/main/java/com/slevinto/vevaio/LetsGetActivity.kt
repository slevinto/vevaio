package com.slevinto.vevaio

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Rect
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.database.FirebaseDatabase
import com.slevinto.vevaio.SetDeviceActivity.Companion.hideKeyboard
import com.slevinto.vevaio.SetDeviceActivity.Companion.showToast

class LetsGetActivity : AppCompatActivity() {
    var familystatuses = arrayOf("Married", "Single", "Other")
    var childrens = arrayOf("0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "More" )
    var workstatuses = arrayOf("Self employed", "Other")
    var stresslevels = arrayOf("0", "1", "2", "3", "4", "5", "6", "7")

    private lateinit var familystatus: Spinner
    private lateinit var children: Spinner
    private lateinit var work: Spinner
    private lateinit var stresslevel: Spinner
    private lateinit var btnContinue: Button
    private lateinit var btnSkip: Button
    private lateinit var llBtns: RelativeLayout

    private val sharedPrefFile = "kotlinsharedpreference"
    private var mAuth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lets_get)

        familystatus = findViewById(R.id.spinner_family)
        children = findViewById(R.id.spinner_children)
        work = findViewById(R.id.spinner_work)
        stresslevel = findViewById(R.id.spinner_stress)
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

        mAuth = FirebaseAuth.getInstance()

        val adapterFamilystatus: ArrayAdapter<String> = ArrayAdapter<String>(
            this,
            android.R.layout.simple_spinner_item, familystatuses
        )
        adapterFamilystatus.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        familystatus.adapter = adapterFamilystatus
        familystatus.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>,
                                        view: View, position: Int, id: Long) {
            }
            override fun onNothingSelected(parent: AdapterView<*>) {
            }
        }

        val adapterChildren: ArrayAdapter<String> = ArrayAdapter<String>(
            this,
            android.R.layout.simple_spinner_item, childrens
        )
        adapterChildren.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        children.adapter = adapterChildren
        children.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>,
                                        view: View, position: Int, id: Long) {
            }
            override fun onNothingSelected(parent: AdapterView<*>) {
            }
        }

        val adapterWork: ArrayAdapter<String> = ArrayAdapter<String>(
            this,
            android.R.layout.simple_spinner_item, workstatuses
        )
        adapterWork.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        work.adapter = adapterWork
        work.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>,
                                        view: View, position: Int, id: Long) {
            }
            override fun onNothingSelected(parent: AdapterView<*>) {
            }
        }

        val adapterStressLevel: ArrayAdapter<String> = ArrayAdapter<String>(
            this,
            android.R.layout.simple_spinner_item, stresslevels
        )
        adapterStressLevel.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        stresslevel.adapter = adapterStressLevel
        stresslevel.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>,
                                        view: View, position: Int, id: Long) {
            }
            override fun onNothingSelected(parent: AdapterView<*>) {
            }
        }

        btnContinue.setOnClickListener {

            //sign in in firebase
            mAuth!!.createUserWithEmailAndPassword(intent.extras?.getString("email").toString(), intent.extras?.getString("password").toString()).addOnCompleteListener(this)
            { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information

                    val sharedPreferences: SharedPreferences =
                        this.getSharedPreferences(sharedPrefFile, Context.MODE_PRIVATE)
                    val editor: SharedPreferences.Editor = sharedPreferences.edit()
                    editor.putString("email", intent.extras?.getString("email"))
                    editor.putString("password", intent.extras?.getString("password"))
                    editor.apply()

                    var partnerId = intent.extras?.getString("email").toString()
                    val re = Regex("[^A-Za-z0-9]")
                    partnerId = re.replace(partnerId, "")

                    val database = FirebaseDatabase.getInstance()
                    val ref = database.getReference("users")
                    ref.child(partnerId).child("info").child("phone").setValue(intent.extras?.getString("phone"))
                    ref.child(partnerId).child("info").child("email").setValue(intent.extras?.getString("email"))
                    ref.child(partnerId).child("info").child("password").setValue(intent.extras?.getString("password"))
                    ref.child(partnerId).child("info").child("firstname").setValue(intent.extras?.getString("displayname"))

                    val i = Intent(this, SetBrandActivity::class.java)
                    val b = Bundle()
                    b.putString("email", intent.extras?.getString("email"))
                    b.putString("password", intent.extras?.getString("password"))
                    b.putString("displayname", intent.extras?.getString("displayname"))
                    b.putString("phone", intent.extras?.getString("phone"))
                    b.putString("code", intent.extras?.getString("code"))
                    b.putString("name", partnerId)
                    i.putExtras(b)
                    startActivity(i)
                } else {
                    try {
                        throw task.exception!!
                    } catch(e: FirebaseAuthUserCollisionException) {
                        val sharedPreferences: SharedPreferences = this.getSharedPreferences(sharedPrefFile,
                            Context.MODE_PRIVATE)
                        val editor: SharedPreferences.Editor = sharedPreferences.edit()
                        editor.putString("email", intent.extras?.getString("email"))
                        editor.putString("password", intent.extras?.getString("password"))
                        editor.apply()

                        mAuth!!.signInWithEmailAndPassword(intent.extras?.getString("email").toString(), intent.extras?.getString("password").toString()).addOnCompleteListener(this)
                        { task ->
                            if (task.isSuccessful) {
                                // Sign in success, update UI with the signed-in user's information
                                var partnerId = intent.extras?.getString("email").toString()
                                val re = Regex("[^A-Za-z0-9 ]")
                                partnerId = re.replace(partnerId, "")

                                val database = FirebaseDatabase.getInstance()
                                val ref = database.getReference("users")
                                ref.child(partnerId).child("info").child("phone").setValue(intent.extras?.getString("phone"))
                                ref.child(partnerId).child("info").child("email").setValue(intent.extras?.getString("email"))
                                ref.child(partnerId).child("info").child("password").setValue(intent.extras?.getString("password"))
                                ref.child(partnerId).child("info").child("firstname").setValue(intent.extras?.getString("displayname"))

                                val i = Intent(this, SetBrandActivity::class.java)
                                val b = Bundle()
                                b.putString("email", intent.extras?.getString("email"))
                                b.putString("password", intent.extras?.getString("password"))
                                b.putString("displayname", intent.extras?.getString("displayname"))
                                b.putString("phone", intent.extras?.getString("phone"))
                                b.putString("code", intent.extras?.getString("code"))
                                b.putString("name", partnerId)
                                i.putExtras(b)
                                startActivity(i)
                            } else {
                                // If sign in fails, display a message to the user.
                            }
                            // ...
                        }
                    } catch (e: Exception) {

                        // If sign in fails, display a message to the user.
                        hideKeyboard(this@LetsGetActivity)
                        showToast("Register failed: ${task.exception?.localizedMessage.toString()}") {}

                        val i = Intent(this, CommonRegisterActivity::class.java)
                        startActivity(i)
                        finish()
                    }
                }
            }
        }

        btnSkip.setOnClickListener {
            mAuth!!.createUserWithEmailAndPassword(intent.extras?.getString("email").toString(), intent.extras?.getString("password").toString()).addOnCompleteListener(this)
            { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information

                    val sharedPreferences: SharedPreferences =
                        this.getSharedPreferences(sharedPrefFile, Context.MODE_PRIVATE)
                    val editor: SharedPreferences.Editor = sharedPreferences.edit()
                    editor.putString("email", intent.extras?.getString("email"))
                    editor.putString("password", intent.extras?.getString("password"))
                    editor.apply()

                    var partnerId = intent.extras?.getString("email").toString()
                    val re = Regex("[^A-Za-z0-9]")
                    partnerId = re.replace(partnerId, "")

                    val database = FirebaseDatabase.getInstance()
                    val ref = database.getReference("users")
                    ref.child(partnerId).child("info").child("phone").setValue(intent.extras?.getString("phone"))
                    ref.child(partnerId).child("info").child("email").setValue(intent.extras?.getString("email"))
                    ref.child(partnerId).child("info").child("password").setValue(intent.extras?.getString("password"))
                    ref.child(partnerId).child("info").child("firstname").setValue(intent.extras?.getString("displayname"))

                    val i = Intent(this, SetBrandActivity::class.java)
                    val b = Bundle()
                    b.putString("email", intent.extras?.getString("email"))
                    b.putString("password", intent.extras?.getString("password"))
                    b.putString("displayname", intent.extras?.getString("displayname"))
                    b.putString("phone", intent.extras?.getString("phone"))
                    b.putString("code", intent.extras?.getString("code"))
                    b.putString("name", partnerId)
                    i.putExtras(b)
                    startActivity(i)
                } else {
                    try {
                        throw task.exception!!
                    } catch(e: FirebaseAuthUserCollisionException) {
                        val sharedPreferences: SharedPreferences = this.getSharedPreferences(sharedPrefFile,
                            Context.MODE_PRIVATE)
                        val editor: SharedPreferences.Editor = sharedPreferences.edit()
                        editor.putString("email", intent.extras?.getString("email"))
                        editor.putString("password", intent.extras?.getString("password"))
                        editor.apply()

                        mAuth!!.signInWithEmailAndPassword(intent.extras?.getString("email").toString(), intent.extras?.getString("password").toString()).addOnCompleteListener(this)
                        { task ->
                            if (task.isSuccessful) {
                                // Sign in success, update UI with the signed-in user's information
                                var partnerId = intent.extras?.getString("email").toString()
                                val re = Regex("[^A-Za-z0-9 ]")
                                partnerId = re.replace(partnerId, "")

                                val database = FirebaseDatabase.getInstance()
                                val ref = database.getReference("users")
                                ref.child(partnerId).child("info").child("phone").setValue(intent.extras?.getString("phone"))
                                ref.child(partnerId).child("info").child("email").setValue(intent.extras?.getString("email"))
                                ref.child(partnerId).child("info").child("password").setValue(intent.extras?.getString("password"))
                                ref.child(partnerId).child("info").child("firstname").setValue(intent.extras?.getString("displayname"))

                                val i = Intent(this, SetBrandActivity::class.java)
                                val b = Bundle()
                                b.putString("email", intent.extras?.getString("email"))
                                b.putString("password", intent.extras?.getString("password"))
                                b.putString("displayname", intent.extras?.getString("displayname"))
                                b.putString("phone", intent.extras?.getString("phone"))
                                b.putString("code", intent.extras?.getString("code"))
                                b.putString("name", partnerId)
                                i.putExtras(b)
                                startActivity(i)
                            } else {
                                // If sign in fails, display a message to the user.
                            }
                            // ...
                        }
                    } catch (e: Exception) {

                        // If sign in fails, display a message to the user.
                        hideKeyboard(this@LetsGetActivity)
                        showToast("Register failed: ${task.exception?.localizedMessage.toString()}") {}

                        val i = Intent(this, CommonRegisterActivity::class.java)
                        startActivity(i)
                        finish()
                    }
                }
            }
        }
    }
}