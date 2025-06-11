package app.santanu.love.munu.util.ext

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import app.santanu.love.munu.R

fun Context.loadJsonFromAsset(fileName: String) =
    assets.open(fileName).bufferedReader().use { it.readText() }

fun Context.copy(quote: String) {
    (getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager).apply {
        setPrimaryClip(
            ClipData.newPlainText(
                resources.getString(R.string.quote_copied),
                quote
            )
        )
    }
}
