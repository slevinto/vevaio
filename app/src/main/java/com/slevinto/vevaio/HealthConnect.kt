package com.slevinto.vevaio

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.annotation.ChecksSdkIntAtLeast
import androidx.annotation.RestrictTo
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.health.connect.client.HealthConnectClient
import androidx.health.connect.client.permission.HealthDataRequestPermissions
import androidx.health.connect.client.permission.Permission
import androidx.health.connect.client.records.*
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch


class HealthConnect : AppCompatActivity() {
    // Create the permissions launcher.
    private val requestPermissions =
        registerForActivityResult(HealthDataRequestPermissions()) { granted ->
            if (granted.containsAll(permissions)) {
                // Permissions successfully granted
            } else {
                // Lack of required permissions
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        checkPermissionsAndRun()
    }

    override fun onRestart() {
        super.onRestart()
        checkPermissionsAndRun()
    }

    @Composable
    fun WelcomeMessage() {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(32.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                modifier = Modifier.fillMaxWidth(0.5f),
                painter = painterResource(id = R.drawable.ic_person),
                contentDescription = ""
            )
            Spacer(modifier = Modifier.height(32.dp))
            Text(
                text = "Welcome to Vevaio!",
                color = MaterialTheme.colors.onBackground,
                fontSize = 22.sp
            )
            Spacer(modifier = Modifier.height(32.dp))

            healthConnectAvailability = if (Build.VERSION.SDK_INT < MIN_SUPPORTED_SDK)
                "not supported"
            else
                if (isAvailable(LocalContext.current)) {

                    "installed"
                }
                else
                    "not installed"

            when (healthConnectAvailability) {
                "installed" -> InstalledMessage()
                "not installed" -> NotInstalledMessage()
                "not supported" -> NotSupportedMessage()
            }
        }
    }

    @Composable
    fun NotSupportedMessage() {
        val tag = ""
        val url = stringResource(R.string.not_supported_url)
        val handler = LocalUriHandler.current

        val notSupportedText = stringResource(
            id = R.string.not_supported_description,
            MIN_SUPPORTED_SDK
        )
        val notSupportedLinkText = stringResource(R.string.not_supported_link_text)

        val unavailableText = buildAnnotatedString {
            withStyle(style = SpanStyle(color = MaterialTheme.colors.onBackground)) {
                append(notSupportedText)
                append("\n\n")
            }
            pushStringAnnotation(tag = tag, annotation = url)
            withStyle(style = SpanStyle(color = MaterialTheme.colors.primary)) {
                append(notSupportedLinkText)
            }
        }
        ClickableText(
            text = unavailableText,
            style = TextStyle(textAlign = TextAlign.Justify)
        ) { offset ->
            unavailableText.getStringAnnotations(tag = tag, start = offset, end = offset)
                .firstOrNull()?.let {
                    handler.openUri(it.item)
                }
        }
    }

    @Composable
    fun InstalledMessage() {
        val onPermissionsResult: () -> Unit = {}
        val launcher = rememberLauncherForActivityResult(HealthDataRequestPermissions()) {
            onPermissionsResult()
        }
        Text(
            text = stringResource(id = R.string.installed_welcome_message),
            textAlign = TextAlign.Justify,
            fontSize = 22.sp
        )

        Button(
            onClick = {
                launcher.launch(permissions)
            }
        ) {
            Text(text = stringResource(R.string.permissions_button_label))
        }
    }

    @Composable
    fun NotInstalledMessage() {
        val url = stringResource(id = R.string.not_installed_url)
        val context = LocalContext.current
        val notInstalledText = stringResource(id = R.string.not_installed_description)
        val notInstalledLinkText = stringResource(id = R.string.not_installed_link_text)

        val unavailableText = buildAnnotatedString {
            withStyle(style = SpanStyle(color = MaterialTheme.colors.onBackground, fontSize = 22.sp)) {
                append(notInstalledText)
                append("\n\n")
            }
            pushStringAnnotation(tag = "", annotation = url)
            withStyle(style = SpanStyle(color = MaterialTheme.colors.primary, fontSize = 22.sp)) {
                append(notInstalledLinkText)
            }
        }
        ClickableText(
            text = unavailableText,
            style = TextStyle(textAlign = TextAlign.Justify),
        ) { offset ->
            unavailableText.getStringAnnotations(tag = "", start = offset, end = offset)
                .firstOrNull()?.let {
                    context.startActivity(
                        Intent(Intent.ACTION_VIEW, Uri.parse(it.item))
                    )
                }
        }
    }

    private fun checkPermissionsAndRun() {
        if (isAvailable(applicationContext)) {
            val healthConnectClient by lazy { HealthConnectClient.getOrCreate(applicationContext) }
            lifecycleScope.launch {
                val granted =
                    healthConnectClient.permissionController.getGrantedPermissions(permissions)
                healthConnectAvailability = if (granted.containsAll(permissions)) {
                    "granted"
                } else {
                    "not granted"
                }

                if (healthConnectAvailability == "granted") {
                    val i = Intent(applicationContext, LoginActivity::class.java)
                    startActivity(i)
                } else {

                    requestPermissions.launch(permissions)
                }
            }
        }
        else
        {
            setContent {
                WelcomeMessage()
            }
        }
    }

    companion object {
        // The minimum android level that can use Health Connect
        const val MIN_SUPPORTED_SDK = Build.VERSION_CODES.O_MR1
        @ChecksSdkIntAtLeast(api = Build.VERSION_CODES.P)
        internal fun isSdkVersionSufficient() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.P

        @RestrictTo(RestrictTo.Scope.LIBRARY)
        internal const val DEFAULT_PROVIDER_PACKAGE_NAME = "com.google.android.apps.healthdata"

        var healthConnectAvailability = ""

        val permissions: Set<Permission> = setOf(
            Permission.createReadPermission(Steps::class),
            Permission.createReadPermission(Distance::class),
            Permission.createReadPermission(SpeedSeries::class),
            Permission.createReadPermission(TotalCaloriesBurned::class),
            Permission.createReadPermission(HeartRateSeries::class)
        )

        private fun isPackageInstalled(
            packageManager: PackageManager,
            packageName: String,
        ): Boolean {
            return try {
                @Suppress("Deprecation") // getApplicationInfo deprecated in T
                return packageManager.getApplicationInfo(packageName, /* flags= */ 0).enabled
            } catch (e: PackageManager.NameNotFoundException) {
                false
            }
        }

        @JvmOverloads
        @JvmStatic
        fun isAvailable(
            context: Context,
            packageNames: List<String> = listOf(DEFAULT_PROVIDER_PACKAGE_NAME),
        ): Boolean {
            if (!isSdkVersionSufficient()) {
                return false
            }
            return packageNames.any {
                isPackageInstalled(context.packageManager, it)
            }
        }
    }
}