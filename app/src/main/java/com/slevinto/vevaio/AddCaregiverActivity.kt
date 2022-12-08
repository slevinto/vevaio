package com.slevinto.vevaio

import android.content.Intent
import android.graphics.Rect
import android.os.Bundle
import android.text.TextUtils
import android.view.Gravity
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.RelativeLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.slevinto.vevaio.SetDeviceActivity.Companion.hideKeyboard
import com.slevinto.vevaio.SetDeviceActivity.Companion.showToast
import com.thryve.connector.sdk.rx.doInBackground
import java.util.*
import javax.mail.*
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeMessage

class DoctorInfo(var name: String, var email: String)

class AddCaregiverActivity : AppCompatActivity() {
    private lateinit var caregiver: EditText
    private lateinit var btnContinue: Button
    private lateinit var btnSkip: Button
    private lateinit var llBtns: RelativeLayout
    private var doctorsList = arrayListOf<DoctorInfo>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_caregiver)

        caregiver = findViewById(R.id.et_email)
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

        val database = FirebaseDatabase.getInstance()
        val ref = database.getReference("doctors")
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (elem in snapshot.children) {
                    doctorsList.add(DoctorInfo(elem.child("info").child("fullname").value.toString(), elem.child("info").child("email").value.toString()))
                }
            }
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })

        btnContinue.setOnClickListener {
            if (!TextUtils.isEmpty(caregiver.text.toString()) && android.util.Patterns.EMAIL_ADDRESS.matcher(caregiver.text.toString()).matches())
            {
                var toastMessage = ""
                var emailMessage = ""
                if (caregiver.text.toString() in doctorsList.map { it.email }) // doctor is signed
                {
                    val doctorName = doctorsList.filter { it.email == caregiver.text.toString() }[0].name
                    toastMessage = "A message has been sent to Dr. $doctorName"
                    emailMessage = "<h1>Dear Dr. " + doctorName + ",</h1>" +
                                   "<br/>" +
                                   "<p>" + intent.extras?.getString("displayname") + " has added you as his caregiver</p>" +
                                   "<p>and is sharing with you his <strong>VeVaio Connect</strong> personal profile and info.</p>" +
                                   "<br/>" +
                                   "<h2>To accept the invite please click here</h2>" +
                                   "<a style='background-color: green; text-decoration: none; vertical-align: middle; text-align: center; color: white; font-size: 20px; font-weight: bold; line-height: 100px; padding: 30px' href=" +
                                   "https://limitless-shelf-22583.herokuapp.com/addUserToDoctor/" +  intent.extras?.getString("email") + '/' + doctorName.replace(" ", "%20") +
                                   ">ACCEPT SHARING INVITE</a>" +
                                   "<br/>" +
                                   "<h2>To Reject the invite please click here</h2>" +
                                   "<a style='background-color:grey; text-decoration: none; vertical-align: middle; text-align:center; color:black; font-size:20px; font-weight: bold; line-height: 100px; padding: 30px' href=" +
                                   "https://limitless-shelf-22583.herokuapp.com/addUserToDoctor/No/" + doctorName.replace(" ", "%20") + ">REJECT SHARING INVITE</a>"
                }
                else // doctor is not signed
                {
                    toastMessage = "This email is not registered"
                    emailMessage = "<h1>Dear Dr.</h1>" +
                                   "<br/>" +
                                   "<p>" + intent.extras?.getString("displayname") + " has added you as his caregiver</p>" +
                                   "<br/>" +
                                   "and is trying to share with you his <strong>VeVaio Connect</strong> personal profile and info,</p>" +
                                   "<br/>" +
                                   "<p>however our system doesn't recognize you.</p>" +
                                   "<br/>" +
                                   "<p>To learn more about Vevaio Connect and register please visit our website:</p><a href='www.vevaio.com'>www.vevaio.com</a>"
                }

                doInBackground {
                    sendEmail(caregiver.text.toString(), emailMessage)
                }
                hideKeyboard(this@AddCaregiverActivity)
                showToast(toastMessage) {}

            }
            else {
                SetDeviceActivity.hideKeyboard(this@AddCaregiverActivity)
                showToast("Email is not valid") {}
            }
            val i = Intent(this, LetsGetActivity::class.java)
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
            val i = Intent(this, LetsGetActivity::class.java)
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

    fun sendEmail(doctorEmail: String, content: String) {
        // Sender's email ID needs to be mentioned
        val from = "slevinto@gmail.com"
        val pass = "onkxfaecwcuwtbdb"

        // Get system properties
        val properties: Properties = System.getProperties()
        properties["mail.smtp.host"] = "smtp.gmail.com"
        properties["mail.smtp.port"] = "587"
        properties["mail.smtp.starttls.enable"] = "true"
        properties["mail.smtp.auth"] = "true"

        val session = Session.getInstance(properties, object : Authenticator() {
            override fun getPasswordAuthentication(): PasswordAuthentication {
                return PasswordAuthentication("slevinto@gmail.com", pass)
            }
        })

        try {
            val mimeMessage = MimeMessage(session)
            mimeMessage.setFrom(InternetAddress(from))
            mimeMessage.setRecipients(Message.RecipientType.TO, InternetAddress.parse(doctorEmail, false))
            mimeMessage.setContent(content, "text/html; charset=utf-8")
            mimeMessage.subject = "subject"
            mimeMessage.saveChanges()
            val smtpTransport = session.getTransport("smtp")
            smtpTransport.connect()
            smtpTransport.sendMessage(mimeMessage, mimeMessage.allRecipients)
            smtpTransport.close()
        } catch (mex: MessagingException) {
            mex.printStackTrace()
        }
    }
}