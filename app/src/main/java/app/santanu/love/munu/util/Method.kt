package app.santanu.love.munu.util

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri

class Method(private val ctx: Context) {

    fun shareMore(shareContent: String) {
        val shareIntent = createShareIntent(shareContent)
        launchShareIntent(shareIntent)
    }

    fun openAppInPlayStore(packageName: String) {
        try {
            val uri = Uri.Builder()
                .scheme("market")
                .authority("details")
                .appendQueryParameter("id", packageName)
                .build()

            val marketIntent = Intent(Intent.ACTION_VIEW, uri)
            marketIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)

            if (ctx.packageManager.resolveActivity(marketIntent, 0) != null) {
                ctx.startActivity(marketIntent)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun createShareIntent(shareContent: String): Intent {
        return Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_TEXT, shareContent)
        }
    }

    private fun launchShareIntent(intent: Intent) {
        try {
            val finalIntent = "Share via".let {
                Intent.createChooser(intent, it).apply {
                    flags = Intent.FLAG_ACTIVITY_NEW_TASK
                }
            } ?: intent.apply {
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }
            ctx.startActivity(finalIntent)
        } catch (e: ActivityNotFoundException) {
            e.printStackTrace()
        }
    }
}