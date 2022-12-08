package com.slevinto.vevaio

import android.content.Intent
import android.os.Bundle
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.widget.Button
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import com.thryve.connector.sdk.CoreConnector
import com.thryve.connector.sdk.logger.Logger
import com.thryve.connector.sdk.rx.doInBackground
import java.io.IOException

class SetBrandActivity : AppCompatActivity() {

    @JvmField
    var webView: WebView? = null
    private lateinit var btnContinue: Button
    private lateinit var btn_skip: Button
    private lateinit var progressBar: ProgressBar
    private var connector: CoreConnector? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_set_brand)

        progressBar = findViewById((R.id.progressBar))
        progressBar.visibility = ProgressBar.GONE

        webView = findViewById(R.id.web_view)
        webView?.settings?.allowContentAccess = true
        webView?.settings?.domStorageEnabled = true
        webView?.settings?.useWideViewPort = true

        btnContinue = findViewById(R.id.btn_send)
        btn_skip = findViewById(R.id.btn_skip)
        btnContinue.visibility = Button.INVISIBLE
        btn_skip.visibility = Button.INVISIBLE
        btnContinue.setOnClickListener {
            doInBackground({
                connector?.userInformation
            }) { userInformationMap ->
                userInformationMap?.let {
                    val userInformation = userInformationMap[userInformationMap.size - 1]
                    val i = Intent(this, OverviewActivity::class.java)
                    val b = Bundle()
                    val brandNum = userInformation.connectedSources[userInformation.connectedSources.size - 1].dataSource
                    b.putInt("brandNum", brandNum)
                    b.putString("email", intent.extras?.getString("email"))
                    b.putString("password", intent.extras?.getString("password"))
                    b.putString("displayname", intent.extras?.getString("displayname"))
                    b.putString("phone", intent.extras?.getString("phone"))
                    b.putString("code", intent.extras?.getString("code"))
                    i.putExtras(b)
                    startActivity(i)
                    finish()
                }
            }
        }

        btn_skip.setOnClickListener {
            val i = Intent(this, OverviewActivity::class.java)
            val b = Bundle()
            b.putString("email", intent.extras?.getString("email"))
            b.putString("password", intent.extras?.getString("password"))
            b.putString("displayname", intent.extras?.getString("displayname"))
            b.putString("phone", intent.extras?.getString("phone"))
            b.putString("code", intent.extras?.getString("code"))
            i.putExtras(b)
            startActivity(i)
        }

        val b = intent.extras
        var value = ""
        if (b != null)
            value = b.getString("name").toString()

        connector = CoreConnector(applicationContext,
            APP_ID,
            APP_SECRET,
            value,
            language
        )

        generateAccessToken(value)

        webView?.webChromeClient = object : WebChromeClient() {

            override fun onProgressChanged(
                view: WebView,
                newProgress: Int
            ) {
                super.onProgressChanged(view, newProgress)
                progressBar.progress = newProgress
                if (newProgress < 100 && progressBar.visibility == ProgressBar.GONE) {
                    progressBar.visibility = ProgressBar.VISIBLE
                }
                if (newProgress == 100) {
                    progressBar.visibility = ProgressBar.GONE

                    doInBackground({
                        connector?.userInformation
                    }) { userInformationMap ->
                        userInformationMap?.let {
                            val userInformation = userInformationMap[userInformationMap.size - 1]
                            if (userInformation.connectedSources.size == intent.extras?.getInt("numdevices"))
                            {
                                btn_skip.visibility = Button.VISIBLE
                            }
                            else
                            {
                                btn_skip.visibility = Button.VISIBLE
                                btnContinue.visibility = Button.VISIBLE
                            }
                        }
                    }
                }
            }
        }
    }

    private fun loadWebView(name: String) {
        CoreConnector(this, APP_ID, name).handleDataSourceConnection(webView!!)
    }

    private fun generateAccessToken(name: String) {
        doInBackground ({
            try {
                connector?.getAccessToken()
                loadWebView(name)
            } catch (e: IOException) {
                Logger.e(SetBrandActivity::class.java.simpleName, e)
                null
            }
        })
        {

        }
    }

    companion object {
        const val APP_ID: String = "gH8y9ZKUE2fktkZW" //"xHbDT27Xfswn6Y4J"
        const val APP_SECRET: String = "9zLYhP27qkeVRbF7k95TzDCZLAzKLjRXT5NfgA9MuKcR2uFYqNr2DsAnf9xbHVf9" //"jtgFwacC9DFXJwGaBZnX3KNgbYg5JSYfZ5vf7ZwxDjDGkgEGp7RZ7W4HX32Rp4af"
        var language = "en"
    }
}