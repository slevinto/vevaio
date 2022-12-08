package com.slevinto.vevaio

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.slevinto.vevaio.SetDeviceActivity.Companion.showToast

class CommonRegisterActivity : AppCompatActivity() {
    private val sharedPrefFile = "kotlinsharedpreference"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val sharedPreferences: SharedPreferences = this.getSharedPreferences(
            sharedPrefFile,
            Context.MODE_PRIVATE
        )
        val email = sharedPreferences.getString("email", null)
        val password = sharedPreferences.getString("password", null)

        if (email != null && password != null) // try go to Overview screen
        {
            // try login to firebase
            val mAuth = FirebaseAuth.getInstance()
            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this)
            { task ->
                if (task.isSuccessful) {
                    // Sign in success, get display name
                    var partnerId = email
                    val re = Regex("[^A-Za-z0-9 ]")
                    partnerId = re.replace(partnerId, "")

                    val database = FirebaseDatabase.getInstance()
                    val ref = database.getReference("users").child(partnerId).child("info")
                    ref.addValueEventListener(object : ValueEventListener {
                        override fun onDataChange(snapshot: DataSnapshot) {
                            val dataElem = snapshot.value as HashMap<String, Any>
                            val displayname = dataElem["firstname"].toString()

                            // go to Overview screen
                            val i = Intent(this@CommonRegisterActivity, OverviewActivity::class.java)
                            val bndl = Bundle()
                            bndl.putString("name", partnerId)
                            bndl.putString("displayname", displayname)
                            i.putExtras(bndl)
                            startActivity(i)
                            finish()
                        }

                        override fun onCancelled(error: DatabaseError) {
                            TODO("Not yet implemented")
                        }
                    })
                } else {
                    showToast("Sign in failed. " + task.exception?.message) {}
                    // go to Login screen
                    val editor: SharedPreferences.Editor = sharedPreferences.edit()
                    editor.putString("email", null)
                    editor.putString("password", null)
                    editor.apply()

                    val i = Intent(this@CommonRegisterActivity, LoginActivity::class.java)
                    startActivity(i)
                    finish()
                }
            }
        }
        else // go to Login screen
        {
            val i = Intent(this@CommonRegisterActivity, LoginActivity::class.java)
            startActivity(i)
            finish()
        }
    }
}