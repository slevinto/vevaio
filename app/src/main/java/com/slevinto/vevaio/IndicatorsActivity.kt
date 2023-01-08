package com.slevinto.vevaio

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity

class IndicatorsActivity : AppCompatActivity() {

    private lateinit var lStress: LinearLayout
    private lateinit var lBma: LinearLayout
    private lateinit var lPa: LinearLayout
    private lateinit var lBonemass: LinearLayout
    private lateinit var lProtein: LinearLayout
    private lateinit var lFluids: LinearLayout
    private lateinit var lWater: LinearLayout
    private lateinit var lBodyfat: LinearLayout
    private lateinit var lHeartbit: LinearLayout
    private lateinit var lSleep: LinearLayout

    private lateinit var btnReturn: ImageButton
    var displayname = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_indicators)

        val b = intent.extras

        if (b != null)
        {
            displayname = b.getString("displayname").toString()
        }

        lStress = findViewById(R.id.l_stress)
        lBma = findViewById(R.id.l_bma)
        lPa = findViewById(R.id.l_pa)
        lBonemass = findViewById(R.id.l_bonemass)
        lProtein = findViewById(R.id.l_protein)
        lFluids = findViewById(R.id.l_fluids)
        lWater = findViewById(R.id.l_water)
        lBodyfat = findViewById(R.id.l_bodyfat)
        lHeartbit = findViewById(R.id.l_heartbit)
        lSleep = findViewById(R.id.l_sleep)

        btnReturn = findViewById(R.id.btn_return)

        lStress.setOnClickListener{
            val i = Intent(this, IndicatorActivity::class.java)
            val bndl = Bundle()
            bndl.putString("displayname", displayname)
            bndl.putString("title", "Stress")
            bndl.putString("rectangle_color", "red")
            bndl.putString("rectangle_value", "-3")
            bndl.putString("arrow", "down")
            bndl.putString("value", "39")
            i.putExtras(bndl)
            startActivity(i)
        }
        lBma.setOnClickListener{
            val i = Intent(this, IndicatorActivity::class.java)
            val bndl = Bundle()
            bndl.putString("displayname", displayname)
            bndl.putString("title", "BMA")
            bndl.putString("rectangle_color", "green")
            bndl.putString("rectangle_value", "4")
            bndl.putString("arrow", "up")
            bndl.putString("value", "39")
            i.putExtras(bndl)
            startActivity(i)
        }
        lPa.setOnClickListener{
            val i = Intent(this, IndicatorActivity::class.java)
            val bndl = Bundle()
            bndl.putString("displayname", displayname)
            bndl.putString("title", "PA")
            bndl.putString("rectangle_color", "red")
            bndl.putString("rectangle_value", "-3")
            bndl.putString("arrow", "down")
            bndl.putString("value", "39")
            i.putExtras(bndl)
            startActivity(i)
        }
        lBonemass.setOnClickListener{
            val i = Intent(this, IndicatorActivity::class.java)
            val bndl = Bundle()
            bndl.putString("displayname", displayname)
            bndl.putString("title", "Bone mass")
            bndl.putString("rectangle_color", "green")
            bndl.putString("rectangle_value", "4")
            bndl.putString("arrow", "up")
            bndl.putString("value", "39")
            i.putExtras(bndl)
            startActivity(i)
        }
        lProtein.setOnClickListener{
            val i = Intent(this, IndicatorActivity::class.java)
            val bndl = Bundle()
            bndl.putString("displayname", displayname)
            bndl.putString("title", "Bone mass")
            bndl.putString("rectangle_color", "green")
            bndl.putString("rectangle_value", "4")
            bndl.putString("arrow", "up")
            bndl.putString("value", "39")
            i.putExtras(bndl)
            startActivity(i)
        }
        lFluids.setOnClickListener{
            val i = Intent(this, IndicatorActivity::class.java)
            val bndl = Bundle()
            bndl.putString("displayname", displayname)
            bndl.putString("title", "Fluids")
            bndl.putString("rectangle_color", "red")
            bndl.putString("rectangle_value", "-3")
            bndl.putString("arrow", "down")
            bndl.putString("value", "39")
            i.putExtras(bndl)
            startActivity(i)
        }
        lWater.setOnClickListener{
            val i = Intent(this, IndicatorActivity::class.java)
            val bndl = Bundle()
            bndl.putString("displayname", displayname)
            bndl.putString("title", "Water")
            bndl.putString("rectangle_color", "red")
            bndl.putString("rectangle_value", "-3")
            bndl.putString("arrow", "down")
            bndl.putString("value", "39")
            i.putExtras(bndl)
            startActivity(i)
        }
        lBodyfat.setOnClickListener{
            val i = Intent(this, IndicatorActivity::class.java)
            val bndl = Bundle()
            bndl.putString("displayname", displayname)
            bndl.putString("title", "Body fat")
            bndl.putString("rectangle_color", "red")
            bndl.putString("rectangle_value", "-3")
            bndl.putString("arrow", "down")
            bndl.putString("value", "39")
            i.putExtras(bndl)
            startActivity(i)
        }
        lHeartbit.setOnClickListener{
            val i = Intent(this, IndicatorActivity::class.java)
            val bndl = Bundle()
            bndl.putString("displayname", displayname)
            bndl.putString("title", "Heart bit")
            bndl.putString("rectangle_color", "red")
            bndl.putString("rectangle_value", "-3")
            bndl.putString("arrow", "down")
            bndl.putString("value", "39")
            i.putExtras(bndl)
            startActivity(i)
        }
        lSleep.setOnClickListener{
            val i = Intent(this, IndicatorActivity::class.java)
            val bndl = Bundle()
            bndl.putString("displayname", displayname)
            bndl.putString("title", "Sleep")
            bndl.putString("rectangle_color", "red")
            bndl.putString("rectangle_value", "-3")
            bndl.putString("arrow", "down")
            bndl.putString("value", "39")
            i.putExtras(bndl)
            startActivity(i)
        }

        btnReturn.setOnClickListener{
            val i = Intent(this, IndicatorActivity::class.java)
            val bndl = Bundle()
            bndl.putString("displayname", displayname)
            bndl.putString("title", "Stress")
            bndl.putString("rectangle_color", "red")
            bndl.putString("rectangle_value", "-3")
            bndl.putString("arrow", "down")
            bndl.putString("value", "39")
            i.putExtras(bndl)
            startActivity(i)
        }
    }
}

