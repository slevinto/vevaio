package com.slevinto.vevaio

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.NumberPicker
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class HeightActivity : AppCompatActivity() {

    private lateinit var height: TextView
    private lateinit var word: TextView
    private lateinit var btnContinue: Button
    var cmChoose: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_height)

        height = findViewById(R.id.tv_height)
        btnContinue = findViewById(R.id.btn_continue)
        word = findViewById(R.id.tv_wordcm)

        val dpd = Dialog(this, android.R.style.Theme_Holo_Light_Dialog_MinWidth)
        dpd.setContentView(R.layout.cm_dialog)
        val buttonOK: Button = dpd.findViewById(R.id.buttonOK)
        val buttonCancel: Button = dpd.findViewById(R.id.buttonCancel)
        val feet: NumberPicker = dpd.findViewById(R.id.numberPickerFeet)
        val inch: NumberPicker = dpd.findViewById(R.id.numberPickerInch)
        val cm: NumberPicker = dpd.findViewById(R.id.numberPickerCm)
        val measure: NumberPicker = dpd.findViewById(R.id.numberPickerMeasure)

        feet.minValue = 0
        feet.maxValue = 20

        inch.minValue = 0
        inch.maxValue = 20

        cm.minValue = 0
        cm.maxValue = 250

        val values = arrayOf("cm", "feet")
        measure.minValue = 0
        measure.maxValue = values.size - 1
        measure.displayedValues = values
        measure.wrapSelectorWheel = true

        feet.setFormatter { value -> "$value'" }
        inch.setFormatter { value -> "$value''" }

        height.setOnClickListener {
            dpd.show()
            if (cmChoose) {
                cm.visibility = NumberPicker.VISIBLE
                feet.visibility = NumberPicker.INVISIBLE
                inch.visibility = NumberPicker.INVISIBLE
            }
            else
            {
                cm.visibility = NumberPicker.INVISIBLE
                feet.visibility = NumberPicker.VISIBLE
                inch.visibility = NumberPicker.VISIBLE
            }
        }

        buttonOK.setOnClickListener {
            if (cmChoose) {
                height.text = cm.value.toString()
                word.text="CM"
            }
            else {
                height.text = feet.value.toString() + "." + inch.value.toString()
                word.text="FT"
            }

            dpd.dismiss()
        }

        buttonCancel.setOnClickListener {
            dpd.dismiss()
        }

        measure.setOnValueChangedListener { picker, oldVal, newVal ->
            cmChoose = newVal == 0
            if (cmChoose) {
                cm.visibility = NumberPicker.VISIBLE
                feet.visibility = NumberPicker.INVISIBLE
                inch.visibility = NumberPicker.INVISIBLE
            }
            else
            {
                cm.visibility = NumberPicker.INVISIBLE
                feet.visibility = NumberPicker.VISIBLE
                inch.visibility = NumberPicker.VISIBLE
            }
        }

        btnContinue.setOnClickListener {
            val i = Intent(this, ProfilePictureActivity::class.java)
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