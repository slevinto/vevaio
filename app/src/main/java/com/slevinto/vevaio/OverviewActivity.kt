package com.slevinto.vevaio

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.ValueFormatter
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.thryve.connector.sdk.CoreConnector
import com.thryve.connector.sdk.rx.doInBackground
import java.lang.Float.parseFloat
import java.text.SimpleDateFormat
import java.util.*


class FormatTimestamp : ValueFormatter() {

    override fun getFormattedValue( value: Float ): String
    {
        return SimpleDateFormat("dd/MM.HH:mm").format(Date(value.toLong()))
    }
}

class OverviewActivity : AppCompatActivity() {

    private lateinit var btnSettings: ImageButton
    private lateinit var tvHello: TextView
    private lateinit var graphHeartRate: LineChart
    var heartRateList: ArrayList<Entry> = ArrayList()
    private lateinit var graphSteps: LineChart
    var stepsList: ArrayList<Entry> = ArrayList()
    private lateinit var graphActiveBurnedCalories: LineChart
    var activeBurnedCaloriesList: ArrayList<Entry> = ArrayList()
    private lateinit var graphActivityDuration: LineChart
    var activityDurationList: ArrayList<Entry> = ArrayList()

    private val database = FirebaseDatabase.getInstance()
    private var ref = database.getReference("users")
    var name = ""
    var displayname = ""

    private var connector: CoreConnector? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_overview)
        btnSettings = findViewById(R.id.btn_settings)

        val b = intent.extras

        if (b != null)
        {
            name = b.getString("name").toString()
            displayname = b.getString("displayname").toString()
        }

        connector = CoreConnector(applicationContext,
            SetBrandActivity.APP_ID,
            SetBrandActivity.APP_SECRET,
            name,
            SetBrandActivity.language
        )

        btnSettings.setOnClickListener {
            val i = Intent(this, SettingsFirstActivity::class.java)
            val bndl = Bundle()
            bndl.putString("displayname", displayname)
            i.putExtras(bndl)
            startActivity(i)
        }


        tvHello = findViewById(R.id.tv_hello)
        tvHello.text = "${tvHello.text} ${displayname}"

        graphHeartRate = findViewById(R.id.graphHeartRate)
        graphSteps = findViewById(R.id.graphSteps)
        graphActiveBurnedCalories = findViewById(R.id.graphActiveBurnedCalories)
        graphActivityDuration = findViewById(R.id.graphActivityDuration)

        val userRefHeartRate = ref.child(name).child("Vitals").child("Resting Heart Rate")
        val userRefSteps = ref.child(name).child("Activity").child("Steps")
        val userRefBurnedCalories = ref.child(name).child("Activity").child("Burned Calories")
        val userRefActivityDuration = ref.child(name).child("Activity").child("Activity Duration")
        userRefHeartRate.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                var minHeartRateX = Float.MAX_VALUE
                var maxHeartRateX = Float.MIN_VALUE
                var minHeartRateY = Float.MAX_VALUE
                var maxHeartRateY = Float.MIN_VALUE

                for (elem in snapshot.children){
                    val dataElem = elem.value as HashMap<String, Any>
                    val netDate = parseFloat(dataElem["createdAtUnix"].toString())
                    val netValue = parseFloat(dataElem["value"].toString())
                    val pElem = Entry(netDate, netValue)
                    heartRateList.add(pElem)
                    if (minHeartRateX > netDate) minHeartRateX = netDate
                    if (maxHeartRateX < netDate) maxHeartRateX = netDate
                    if (minHeartRateY > netValue) minHeartRateY = netValue
                    if (maxHeartRateY < netValue) maxHeartRateY = netValue
                }

                heartRateList = heartRateList.distinctBy { it.x } as ArrayList<Entry>
                heartRateList.sortedBy { it.x }

                val heartRateSet = LineDataSet(heartRateList, null)
                heartRateSet.lineWidth = 0f
                heartRateSet.mode = LineDataSet.Mode.HORIZONTAL_BEZIER
                heartRateSet.setDrawValues(false)
                heartRateSet.setDrawCircles(false)
                heartRateSet.setDrawFilled(true)
                heartRateSet.fillDrawable = getDrawable(R.drawable.graph_gradient)
                val heartRateDataSet = ArrayList<ILineDataSet>()
                heartRateDataSet.add(heartRateSet)
                val heartRateLineData = LineData(heartRateDataSet)

                graphHeartRate.data = heartRateLineData
                graphHeartRate.data.isHighlightEnabled = false
                graphHeartRate.legend.isEnabled = false
                graphHeartRate.description.isEnabled = false
                graphHeartRate.xAxis.position = XAxis.XAxisPosition.BOTTOM
                graphHeartRate.axisRight.isEnabled = false
                graphHeartRate.axisLeft.setDrawGridLines(false)
                graphHeartRate.xAxis.setDrawGridLines(false)
                graphHeartRate.xAxis.setDrawAxisLine(false)
                graphHeartRate.axisLeft.setDrawAxisLine(false)
                graphHeartRate.xAxis.valueFormatter = FormatTimestamp()
                graphHeartRate.xAxis.textColor = getColor(R.color.teal_200)
                graphHeartRate.axisLeft.textColor = getColor(R.color.teal_200)
                graphHeartRate.setDrawBorders(false)
                graphHeartRate.setVisibleXRangeMaximum((maxHeartRateX-minHeartRateX)/6)
                graphHeartRate.invalidate()
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
        userRefSteps.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                var minStepsX = Float.MAX_VALUE
                var maxStepsX = Float.MIN_VALUE
                var minStepsY = Float.MAX_VALUE
                var maxStepsY = Float.MIN_VALUE

                for (elem in snapshot.children){
                    val dataElem = elem.value as HashMap<String, Any>
                    val netDate = parseFloat(dataElem["createdAtUnix"].toString())
                    val netValue = parseFloat(dataElem["value"].toString())
                    val pElem = Entry(netDate, netValue)
                    stepsList.add(pElem)
                    if (minStepsX > netDate) minStepsX = netDate
                    if (maxStepsX < netDate) maxStepsX = netDate
                    if (minStepsY > netValue) minStepsY = netValue
                    if (maxStepsY < netValue) maxStepsY = netValue
                }

                stepsList = stepsList.distinctBy { it.x } as ArrayList<Entry>
                stepsList.sortedBy { it.x }

                val stepsSet = LineDataSet(stepsList, null)
                stepsSet.lineWidth = 0f
                stepsSet.mode = LineDataSet.Mode.HORIZONTAL_BEZIER
                stepsSet.setDrawValues(false)
                stepsSet.setDrawCircles(false)
                stepsSet.setDrawFilled(true)
                stepsSet.fillDrawable = getDrawable(R.drawable.graph_gradient)
                val stepsDataSet = ArrayList<ILineDataSet>()
                stepsDataSet.add(stepsSet)
                val stepsLineData = LineData(stepsDataSet)

                graphSteps.data = stepsLineData
                graphSteps.data.isHighlightEnabled = false
                graphSteps.legend.isEnabled = false
                graphSteps.description.isEnabled = false
                graphSteps.xAxis.position = XAxis.XAxisPosition.BOTTOM
                graphSteps.axisRight.isEnabled = false
                graphSteps.axisLeft.setDrawGridLines(false)
                graphSteps.xAxis.setDrawGridLines(false)
                graphSteps.xAxis.setDrawAxisLine(false)
                graphSteps.axisLeft.setDrawAxisLine(false)
                graphSteps.xAxis.valueFormatter = FormatTimestamp()
                graphSteps.xAxis.textColor = getColor(R.color.teal_200)
                graphSteps.axisLeft.textColor = getColor(R.color.teal_200)
                graphSteps.setDrawBorders(false)
                graphSteps.setVisibleXRangeMaximum((maxStepsX-minStepsX)/4)
                graphSteps.invalidate()
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
        userRefBurnedCalories.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                var minActiveBurnedCaloriesX = Float.MAX_VALUE
                var maxActiveBurnedCaloriesX = Float.MIN_VALUE
                var minActiveBurnedCaloriesY = Float.MAX_VALUE
                var maxActiveBurnedCaloriesY = Float.MIN_VALUE

                for (elem in snapshot.children){
                    val dataElem = elem.value as HashMap<String, Any>
                    val netDate = parseFloat(dataElem["createdAtUnix"].toString())
                    val netValue = parseFloat(dataElem["value"].toString())
                    val pElem = Entry(netDate, netValue)

                    activeBurnedCaloriesList.add(pElem)
                    if (minActiveBurnedCaloriesX > netDate) minActiveBurnedCaloriesX = netDate
                    if (maxActiveBurnedCaloriesX < netDate) maxActiveBurnedCaloriesX = netDate
                    if (minActiveBurnedCaloriesY > netValue) minActiveBurnedCaloriesY = netValue
                    if (maxActiveBurnedCaloriesY < netValue) maxActiveBurnedCaloriesY = netValue
                }

                activeBurnedCaloriesList = activeBurnedCaloriesList.distinctBy { it.x } as ArrayList<Entry>
                activeBurnedCaloriesList.sortedBy { it.x }
                val activeBurnedCaloriesSet = LineDataSet(activeBurnedCaloriesList, null)
                activeBurnedCaloriesSet.lineWidth = 0f
                activeBurnedCaloriesSet.mode = LineDataSet.Mode.HORIZONTAL_BEZIER
                activeBurnedCaloriesSet.setDrawValues(false)
                activeBurnedCaloriesSet.setDrawCircles(false)
                activeBurnedCaloriesSet.setDrawFilled(true)
                activeBurnedCaloriesSet.fillDrawable = getDrawable(R.drawable.graph_gradient)
                val activeBurnedCaloriesDataSet = ArrayList<ILineDataSet>()
                activeBurnedCaloriesDataSet.add(activeBurnedCaloriesSet)
                val activeBurnedCaloriesLineData = LineData(activeBurnedCaloriesDataSet)

                graphActiveBurnedCalories.data = activeBurnedCaloriesLineData
                graphActiveBurnedCalories.data.isHighlightEnabled = false
                graphActiveBurnedCalories.legend.isEnabled = false
                graphActiveBurnedCalories.description.isEnabled = false
                graphActiveBurnedCalories.xAxis.position = XAxis.XAxisPosition.BOTTOM
                graphActiveBurnedCalories.axisRight.isEnabled = false
                graphActiveBurnedCalories.axisLeft.setDrawGridLines(false)
                graphActiveBurnedCalories.xAxis.setDrawGridLines(false)
                graphActiveBurnedCalories.xAxis.setDrawAxisLine(false)
                graphActiveBurnedCalories.axisLeft.setDrawAxisLine(false)
                graphActiveBurnedCalories.xAxis.valueFormatter = FormatTimestamp()
                graphActiveBurnedCalories.xAxis.textColor = getColor(R.color.teal_200)
                graphActiveBurnedCalories.axisLeft.textColor = getColor(R.color.teal_200)
                graphActiveBurnedCalories.setDrawBorders(false)
                graphActiveBurnedCalories.setVisibleXRangeMaximum((maxActiveBurnedCaloriesX-minActiveBurnedCaloriesX)/4)
                graphActiveBurnedCalories.invalidate()
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
        userRefActivityDuration.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                var minActivityDurationX = Float.MAX_VALUE
                var maxActivityDurationX = Float.MIN_VALUE
                var minActivityDurationY = Float.MAX_VALUE
                var maxActivityDurationY = Float.MIN_VALUE

                for (elem in snapshot.children){
                    val dataElem = elem.value as HashMap<String, Any>
                    val netDate = parseFloat(dataElem["createdAtUnix"].toString())
                    val netValue = parseFloat(dataElem["value"].toString())
                    val pElem = Entry(netDate, netValue)
                    activityDurationList.add(pElem)
                    if (minActivityDurationX > netDate) minActivityDurationX = netDate
                    if (maxActivityDurationX < netDate) maxActivityDurationX = netDate
                    if (minActivityDurationY > netValue) minActivityDurationY = netValue
                    if (maxActivityDurationY < netValue) maxActivityDurationY = netValue
                }

                activityDurationList = activityDurationList.distinctBy { it.x } as ArrayList<Entry>
                activityDurationList.sortedBy { it.x }

                val activityDurationSet = LineDataSet(activityDurationList, null)
                activityDurationSet.lineWidth = 0f
                activityDurationSet.mode = LineDataSet.Mode.HORIZONTAL_BEZIER
                activityDurationSet.setDrawValues(false)
                activityDurationSet.setDrawCircles(false)
                activityDurationSet.setDrawFilled(true)
                activityDurationSet.fillDrawable = getDrawable(R.drawable.graph_gradient)
                val activityDurationDataSet = ArrayList<ILineDataSet>()
                activityDurationDataSet.add(activityDurationSet)
                val activityDurationLineData = LineData(activityDurationDataSet)

                graphActivityDuration.data = activityDurationLineData
                graphActivityDuration.data.isHighlightEnabled = false
                graphActivityDuration.legend.isEnabled = false
                graphActivityDuration.description.isEnabled = false
                graphActivityDuration.xAxis.position = XAxis.XAxisPosition.BOTTOM
                graphActivityDuration.axisRight.isEnabled = false
                graphActivityDuration.axisLeft.setDrawGridLines(false)
                graphActivityDuration.xAxis.setDrawGridLines(false)
                graphActivityDuration.xAxis.setDrawAxisLine(false)
                graphActivityDuration.axisLeft.setDrawAxisLine(false)
                graphActivityDuration.xAxis.valueFormatter = FormatTimestamp()
                graphActivityDuration.xAxis.textColor = getColor(R.color.teal_200)
                graphActivityDuration.axisLeft.textColor = getColor(R.color.teal_200)
                graphActivityDuration.setDrawBorders(false)
                graphActivityDuration.setVisibleXRangeMaximum((maxActivityDurationX-minActivityDurationX)/4)
                graphActivityDuration.invalidate()
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }



    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.profile -> Toast.makeText(this,"Profile Selected",Toast.LENGTH_SHORT).show()
            R.id.add_device -> {
                doInBackground({
                    connector?.userInformation
                }) { userInformationMap ->
                    userInformationMap?.let {
                        val userInformation = userInformationMap[userInformationMap.size - 1]
                        val i = Intent(this, SetBrandActivity::class.java)
                        val b = Bundle()
                        b.putString("email", intent.extras?.getString("email"))
                        b.putString("password", intent.extras?.getString("password"))
                        b.putString("displayname", intent.extras?.getString("displayname"))
                        b.putString("phone", intent.extras?.getString("phone"))
                        b.putString("code", intent.extras?.getString("code"))
                        b.putInt("numdevices", userInformation.connectedSources.size)
                        i.putExtras(b)
                        startActivity(i)
                        finish()
                    }
                }
            }
            R.id.sign_out -> {
                val sharedPrefFile = "kotlinsharedpreference"
                val sharedPreferences: SharedPreferences = this.getSharedPreferences(sharedPrefFile,
                    Context.MODE_PRIVATE)
                val editor: SharedPreferences.Editor = sharedPreferences.edit()
                editor.putString("email", null)
                editor.putString("password", null)
                editor.apply()
                val i = Intent(this, LoginActivity::class.java)
                startActivity(i)
                finish()
            }
            R.id.other -> Toast.makeText(this,"Other Selected",Toast.LENGTH_SHORT).show()
        }
        return super.onOptionsItemSelected(item)
    }
}

