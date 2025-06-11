package app.santanu.love.munu.data

import android.content.Context
import app.santanu.love.munu.data.model.Quotes
import app.santanu.love.munu.util.ext.loadJsonFromAsset
import java.lang.reflect.Type
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AssetsData(private val context: Context) {

    companion object {
        const val QUOTES_FILE = "data/quotes.json"

        val quotesType: Type by lazy { object : TypeToken<List<Quotes>>() {}.type }
    }

    suspend fun loadQuotes(): List<Quotes> = withContext(Dispatchers.IO) {
        val jsonString = context.loadJsonFromAsset(QUOTES_FILE)
        Gson().fromJson(jsonString, quotesType)
    }
}