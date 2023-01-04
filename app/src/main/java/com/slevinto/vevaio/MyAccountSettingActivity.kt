package com.slevinto.vevaio

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.*
import androidx.appcompat.app.AppCompatActivity



class MyAccountSettingActivity : AppCompatActivity() {
    private lateinit var btnReturn: ImageButton
    private lateinit var btnPerson: ImageButton
    private lateinit var imageView: CircleImageView
    private lateinit var changePhoto: TextView
    private lateinit var firstName: EditText
    private lateinit var lastName: EditText
    private lateinit var birthday: Spinner
    private lateinit var yourWeight: EditText
    private lateinit var yourHeight: EditText
    private lateinit var email: EditText
    private lateinit var phone: EditText
    private lateinit var btnSave: Button
    private val pickImage = 100
    private var imageUri: Uri? = null


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_account_setting)

        btnReturn = findViewById(R.id.btn_return)
        btnReturn.setOnClickListener{
            val i = Intent(this, SettingsFirstActivity::class.java)
            startActivity(i)
        }

        btnPerson = findViewById(R.id.btn_photo_my_account)
        btnPerson.setOnClickListener {
            val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
            startActivityForResult(gallery, pickImage)
        }
        changePhoto = findViewById(R.id.tv_change_photo)

        imageView = findViewById(R.id.imageView)
        firstName = findViewById(R.id.et_firstname)
        lastName = findViewById(R.id.et_lastname)
        birthday = findViewById(R.id.birthday)

        val objects = arrayOf<String?>(
            "January", "February", "March", "April", "May",
            "June", "July", "August", "September", "October", "November", "December"
        )

        val adapter: ArrayAdapter<*> = ArrayAdapter<Any?>(
            applicationContext, android.R.layout.simple_list_item_1, objects
        )

        birthday.setAdapter(adapter)

        birthday.setOnItemSelectedListener(this)

        yourWeight = findViewById(R.id.et_your_weight)
        yourHeight = findViewById(R.id.et_your_height)
        email = findViewById(R.id.et_email)
        phone = findViewById(R.id.et_phone)
        btnSave = findViewById(R.id.btn_save)

    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && requestCode == pickImage) {
            imageUri = data?.data
            imageView.setImageURI(imageUri)
            btnPerson.background = getDrawable(android.R.color.transparent)
            btnPerson.setImageResource(android.R.color.transparent)
            changePhoto.text = "Change photo"
        }
    }
}

private fun Spinner.setOnItemSelectedListener(myAccountSetting: MyAccountSettingActivity) {

}
