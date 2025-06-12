package app.santanu.love.munu.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import app.santanu.love.munu.Global
import app.santanu.love.munu.R
import app.santanu.love.munu.required.CheckBeforeRelease
import app.santanu.love.munu.ui.quotes.QuotesActivity
import app.santanu.love.munu.util.ChromeTabUtil
import app.santanu.love.munu.util.Method
import com.google.android.material.button.MaterialButton
import com.squareup.picasso.Picasso

class MainActivity : AppCompatActivity() {

    private lateinit var shareImageView: AppCompatImageView
    private lateinit var bigImageView: AppCompatImageView
    private lateinit var privacyButton: MaterialButton
    private lateinit var conditionButton: MaterialButton
    private lateinit var viewMoreQuotesTextView: MaterialButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()
        setBigImage()
        shareApp()
        viewMoreQuotes()
        setDevPage()
        privacyPolicy()
        termConditions()
    }

    private fun initView() {
        shareImageView = findViewById(R.id.aciv_share_app)
        bigImageView = findViewById(R.id.aciv_big_image)
        privacyButton = findViewById(R.id.mb_home_privacy_policy)
        conditionButton = findViewById(R.id.mb_home_term_condition)
        viewMoreQuotesTextView = findViewById(R.id.mb_view_more_quotes)
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

    private fun setDevPage() {
        val includeDevPage = findViewById<View>(R.id.include_dev)
        val writeNotButton = includeDevPage.findViewById<MaterialButton>(R.id.mb_write_me_note)
        val appVersionTextView =
            includeDevPage.findViewById<AppCompatTextView>(R.id.actv_dev_app_version)

        val appLastUpdateTextView =
            includeDevPage.findViewById<AppCompatTextView>(R.id.actv_dev_app_last_update)

        appVersionTextView.text = getAppVersion()
        appLastUpdateTextView.text = getLatUpdate()

        writeNotButton.setOnClickListener {
            writeNote()
        }
    }

    private fun writeNote() {
        Method(this@MainActivity).openAppInPlayStore(this.packageName)
    }

    private fun getAppVersion(): String {
        return "Version ${CheckBeforeRelease.APP_VERSION}"
    }

    private fun getLatUpdate(): String {
        return "Last Updated: ${CheckBeforeRelease.APP_LAST_UPDATE}"
    }

    private fun privacyPolicy() {
        privacyButton.setOnClickListener {
            ChromeTabUtil(this@MainActivity).openCustomTab(
                Global.PRIVACY_URL,
                getColor(R.color.lightPeach)
            )
        }
    }

    private fun termConditions() {
        conditionButton.setOnClickListener {
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