package app.santanu.love.munu.ui.quotes

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import app.santanu.love.munu.R
import app.santanu.love.munu.data.AssetsData
import app.santanu.love.munu.util.Method
import app.santanu.love.munu.util.ext.copy
import kotlinx.coroutines.launch
import java.io.IOException

class QuotesActivity : AppCompatActivity() {

    private lateinit var backImageView: AppCompatImageView
    private lateinit var quotesRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quotes)

        initView()
        setQuotesRecyclerView()
        getQuotes()
        backQuotes()
    }

    private fun initView() {
        backImageView = findViewById(R.id.aciv_back_quotes)
        quotesRecyclerView = findViewById(R.id.rv_quotes)
    }

    private fun setQuotesRecyclerView() {
        quotesRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@QuotesActivity)
        }
    }

    private fun getQuotes() {
        val assetsData = AssetsData(this@QuotesActivity)
        lifecycleScope.launch {
            try {
                val quotesList = assetsData.loadQuotes()
                val quotesAdapter =
                    QuotesAdapter(quotesList, ::onCopyClick, ::onWhatsappShareClick, ::onShareClick)

                quotesRecyclerView.adapter = quotesAdapter
            } catch (e: IOException) {
                e.printStackTrace()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun backQuotes() {
        backImageView.setOnClickListener {
            this@QuotesActivity.finish()
        }
    }

    private fun onCopyClick(quote: String) {
        this@QuotesActivity.copy(quote)
        Toast.makeText(this@QuotesActivity, getString(R.string.quote_copied), Toast.LENGTH_SHORT)
            .show()
    }

    private fun onWhatsappShareClick(quote: String) {
        Method(this@QuotesActivity).shareViaWhatsapp(quote)
    }

    private fun onShareClick(quote: String) {
        Method(this@QuotesActivity).shareMore(quote)
    }
}