package app.santanu.love.munu.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import app.santanu.love.munu.Global
import app.santanu.love.munu.R
import app.santanu.love.munu.ui.quotes.QuotesActivity
import app.santanu.love.munu.util.ChromeTabUtil
import app.santanu.love.munu.util.Method
import com.squareup.picasso.Picasso

class MainActivity : AppCompatActivity() {

    private lateinit var shareImageView: AppCompatImageView
    private lateinit var bigImageView: AppCompatImageView
    private lateinit var writeNoteTextView: AppCompatTextView
    private lateinit var privacyTextView: AppCompatTextView
    private lateinit var conditionTextView: AppCompatTextView
    private lateinit var viewMoreQuotesTextView: AppCompatTextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()
        setBigImage()
        shareApp()
        viewMoreQuotes()
        writeNote()
        privacyPolicy()
        termConditions()
    }

    private fun initView() {
        shareImageView = findViewById(R.id.aciv_share_app)
        bigImageView = findViewById(R.id.aciv_big_image)
        writeNoteTextView = findViewById(R.id.actv_write_note)
        privacyTextView = findViewById(R.id.actv_privacy_policy)
        conditionTextView = findViewById(R.id.actv_term_condition)
        viewMoreQuotesTextView = findViewById(R.id.actv_view_more_quotes)
    }

    private fun setBigImage() {
        val bigImage = "file:///android_asset/image/header_image.png"
        Picasso.get()
            .load(bigImage)
            .into(bigImageView)
    }

    private fun shareApp() {
        shareImageView.setOnClickListener {
            Method(this@MainActivity).shareMore(shareContent())
        }
    }

    private fun viewMoreQuotes() {
        viewMoreQuotesTextView.setOnClickListener {
            val moreQuoteIntent = Intent(this@MainActivity, QuotesActivity::class.java)
            startActivity(moreQuoteIntent)
        }
    }

    private fun writeNote() {
        writeNoteTextView.setOnClickListener {
            Method(this@MainActivity).openAppInPlayStore(this.packageName)
        }
    }

    private fun privacyPolicy() {
        privacyTextView.setOnClickListener {
            ChromeTabUtil(this@MainActivity).openCustomTab(
                Global.PRIVACY_URL,
                getColor(R.color.lightPeach)
            )
        }
    }

    private fun termConditions() {
        conditionTextView.setOnClickListener {
            ChromeTabUtil(this@MainActivity).openCustomTab(
                Global.TERM_AND_CONDITION_URL,
                getColor(R.color.lightPeach)
            )
        }
    }

    private fun shareContent(): String {
        val appUrl = "https://play.google.com/store/apps/details?id=${this.packageName}"
        return "\uD83C\uDF38 Every heartbeat has a story...\n" +
                "\n" +
                "Discover our journey, our moments, and everything we hold close.\n" +
                "\n" +
                "\uD83D\uDC49 To love, to live, to Munu.\n" +
                "\n" +
                "\uD83D\uDC96 Install now: $appUrl\n" +
                "\n" +
                "#LoveStory #Munu #MadeWithLove"
    }
}