package app.santanu.love.munu.util

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.annotation.ColorInt
import androidx.browser.customtabs.CustomTabColorSchemeParams
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.content.ContextCompat
import app.santanu.love.munu.R
import androidx.core.net.toUri

class ChromeTabUtil(private val context: Context) {

    fun openCustomTab(
        link: String,
        @ColorInt toolbarColor: Int? = null,
        @ColorInt darkToolbarColor: Int? = null
    ) {
        val formattedUrl = formatUrl(link)
        val customTabsIntent = buildCustomTabIntent(toolbarColor, darkToolbarColor)
        launchUrlWithContext(customTabsIntent, formattedUrl)
    }

    private fun formatUrl(link: String): String {
        return if (link.startsWith("http://") || link.startsWith("https://")) {
            link
        } else {
            "https://$link"
        }
    }

    private fun buildCustomTabIntent(
        @ColorInt toolbarColor: Int?,
        @ColorInt darkToolbarColor: Int?
    ): CustomTabsIntent {
        val defaultToolbarColor = ContextCompat.getColor(context, R.color.white)
        val defaultDarkToolbarColor = ContextCompat.getColor(context, R.color.black)

        val defaultColorScheme = CustomTabColorSchemeParams.Builder()
            .setToolbarColor(toolbarColor ?: defaultToolbarColor)
            .build()

        val darkColorScheme = CustomTabColorSchemeParams.Builder()
            .setToolbarColor(darkToolbarColor ?: defaultDarkToolbarColor)
            .build()

        return CustomTabsIntent.Builder()
            .setDefaultColorSchemeParams(defaultColorScheme)
            .setColorSchemeParams(CustomTabsIntent.COLOR_SCHEME_DARK, darkColorScheme)
            .build()
    }

    private fun launchUrlWithContext(customTabsIntent: CustomTabsIntent, url: String) {
        try {
            val uri = url.toUri()
            if (context is Activity) {
                customTabsIntent.launchUrl(context, uri)
            } else {
                customTabsIntent.intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                customTabsIntent.launchUrl(context, uri)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}