package com.slevinto.vevaio

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import java.lang.Float.parseFloat

class IndicatorActivity : AppCompatActivity() {

    private lateinit var btnReturn: ImageButton
    private lateinit var tvIndicator: TextView
    private lateinit var lRectangle: LinearLayout
    private lateinit var tvText: TextView
    private lateinit var iArrow: ImageView
    private lateinit var tvValue: TextView
    var displayname = ""
    private var title = ""
    var rectangleColor = ""
    var rectangleValue = ""
    var arrow = ""
    var value = ""

    private lateinit var graph1: LineChart
    private lateinit var graph2: LineChart
    private lateinit var graph3: LineChart
    private lateinit var graph4: LineChart
    private lateinit var graph5: LineChart
    private lateinit var graph6: LineChart
    private lateinit var graph7: LineChart

    var graph1List: ArrayList<Entry> = ArrayList()
    var graph2List: ArrayList<Entry> = ArrayList()
    var graph3List: ArrayList<Entry> = ArrayList()
    var graph4List: ArrayList<Entry> = ArrayList()
    var graph5List: ArrayList<Entry> = ArrayList()
    var graph6List: ArrayList<Entry> = ArrayList()
    var graph7List: ArrayList<Entry> = ArrayList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_indicator)

        val b = intent.extras
        if (b != null)
        {
            displayname = b.getString("displayname").toString()
            title = b.getString("title").toString()
            rectangleColor = b.getString("rectangle_color").toString()
            rectangleValue = b.getString("rectangle_value").toString()
            arrow = b.getString("arrow").toString()
            value = b.getString("value").toString()
        }

        btnReturn = findViewById(R.id.btn_return)

        tvIndicator = findViewById(R.id.tv_indicator)
        tvIndicator.text = title

        lRectangle = findViewById(R.id.rectangle)
        if (rectangleColor == "red")
            lRectangle.background = ContextCompat.getDrawable(this, R.drawable.button_rectangle_red)
        if (rectangleColor == "green")
            lRectangle.background = ContextCompat.getDrawable(this, R.drawable.button_rectangle_green)

        tvText = findViewById(R.id.text)
        tvText.text = rectangleValue

        iArrow = findViewById(R.id.arrow)
        if (arrow == "up")
            iArrow.setImageResource(R.drawable.ic_arrow_up_white)
        if (arrow == "down")
            iArrow.setImageResource(R.drawable.ic_arrow_down_white)

        tvValue = findViewById(R.id.value)
        tvValue.text = value

        tvIndicator.text = title
        tvIndicator = findViewById(R.id.tv_indicator)
        tvIndicator.text = title
        tvIndicator = findViewById(R.id.tv_indicator)
        tvIndicator.text = title
        tvIndicator = findViewById(R.id.tv_indicator)
        tvIndicator.text = title

        btnReturn.setOnClickListener{
            val i = Intent(this, IndicatorsActivity::class.java)
            val bndl = Bundle()
            bndl.putString("displayname", displayname)
            i.putExtras(bndl)
            startActivity(i)
        }

        var pElem = Entry(parseFloat("1661071500000"), 14F)
        graph1List.add(pElem)
        pElem = Entry(parseFloat("1661157900000"), 8F)
        graph1List.add(pElem)
        pElem = Entry(parseFloat("1661244300000"), 26F)
        graph1List.add(pElem)
        pElem = Entry(parseFloat("1661330700000"), 11F)
        graph1List.add(pElem)
        pElem = Entry(parseFloat("1661417100000"), 27F)
        graph1List.add(pElem)
        pElem = Entry(parseFloat("1661503500000"), 20F)
        graph1List.add(pElem)
        pElem = Entry(parseFloat("1661589900000"), 9F)
        graph1List.add(pElem)

        val graph1Set = LineDataSet(graph1List, null)
        graph1Set.mode = LineDataSet.Mode.HORIZONTAL_BEZIER
        graph1Set.setDrawValues(false)
        graph1Set.setDrawCircles(false)
        graph1Set.setDrawFilled(false)
        graph1Set.fillColor = R.color.graph
        graph1Set.lineWidth = 24f

        val graph1DataSet = ArrayList<ILineDataSet>()
        graph1DataSet.add(graph1Set)
        val graph1LineData = LineData(graph1DataSet)

        graph1 = findViewById(R.id.graph1)
        graph1.data = graph1LineData
        graph1.data.isHighlightEnabled = false
        graph1.legend.isEnabled = false
        graph1.description.isEnabled = false
        graph1.setGridBackgroundColor(getColor(R.color.white))

        graph1.setDrawGridBackground(false)
        graph1.setDrawBorders(true)

        graph1.axisRight.isEnabled = true
        graph1.axisRight.setDrawGridLines(true)
        graph1.axisRight.textColor = getColor(R.color.white)
        graph1.axisRight.textSize = 12f
        graph1.axisRight.setLabelCount(7, true)
        graph1.axisRight.axisMaximum = 35f
        graph1.axisRight.axisMinimum = 5f

        graph1.axisLeft.isEnabled = false
        graph1.axisLeft.setDrawAxisLine(false)

        graph1.xAxis.position = XAxis.XAxisPosition.BOTTOM
        graph1.xAxis.setDrawGridLines(true)
        graph1.xAxis.setDrawAxisLine(false)
        graph1.xAxis.valueFormatter = FormatTimestamp()
        graph1.xAxis.textColor = getColor(R.color.white)
        graph1.xAxis.setLabelCount(7, true)
        graph1.invalidate()
    }
}

