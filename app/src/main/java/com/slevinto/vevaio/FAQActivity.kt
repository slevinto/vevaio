package com.slevinto.vevaio

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class FAQActivity : AppCompatActivity() {
    private lateinit var btnReturn: ImageButton
    private lateinit var question1: TextView
    private lateinit var answer1: TextView
    private lateinit var question2: TextView
    private lateinit var answer2: TextView
    private lateinit var question3: TextView
    private lateinit var answer3: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_faqactivity)
        question1 = findViewById(R.id.tv_question_1)
        answer1 = findViewById(R.id.tv_answer_1)
        question2 = findViewById(R.id.tv_question_2)
        answer2 = findViewById(R.id.tv_answer_2)
        question3 = findViewById(R.id.tv_question_3)
        answer3 = findViewById(R.id.tv_answer_3)


        btnReturn = findViewById(R.id.btn_return)
        btnReturn.setOnClickListener{
            val i = Intent(this, SettingsFirstActivity::class.java)
            startActivity(i)
        }
    }
}