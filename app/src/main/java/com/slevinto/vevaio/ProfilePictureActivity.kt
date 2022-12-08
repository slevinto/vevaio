package com.slevinto.vevaio

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ProfilePictureActivity : AppCompatActivity() {

    private lateinit var imageView: CircleImageView
    private lateinit var button: ImageButton
    private lateinit var word: TextView
    private lateinit var btnContinue: Button
    private lateinit var btnSkip: Button
    private val pickImage = 100
    private var imageUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_picture)

        imageView = findViewById(R.id.imageView)
        word = findViewById(R.id.tv_photo)
        button = findViewById(R.id.buttonLoadPicture)
        btnContinue = findViewById(R.id.btn_continue)
        btnSkip = findViewById(R.id.btn_skip)

        button.setOnClickListener {
            val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
            startActivityForResult(gallery, pickImage)
        }

        btnContinue.setOnClickListener {
            val i = Intent(this, SetDeviceActivity::class.java)
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
            val i = Intent(this, SetDeviceActivity::class.java)
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

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && requestCode == pickImage) {
            imageUri = data?.data
            imageView.setImageURI(imageUri)
            button.background = getDrawable(android.R.color.transparent)
            button.setImageResource(android.R.color.transparent)
            word.text = "Change photo"
        }
    }
}