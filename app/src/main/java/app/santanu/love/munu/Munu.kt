package app.santanu.love.munu

import android.app.Application
import android.widget.Toast
import app.santanu.love.munu.required.CheckBeforeRelease
import app.santanu.love.munu.util.ext.copy
import com.onesignal.OneSignal
import com.onesignal.debug.LogLevel
import com.onesignal.notifications.INotificationClickEvent
import com.onesignal.notifications.INotificationClickListener

class Munu : Application() {

    override fun onCreate() {
        super.onCreate()

        initOnesignal()
    }

    private fun initOnesignal() {
        OneSignal.Debug.logLevel = LogLevel.VERBOSE
        OneSignal.initWithContext(this, CheckBeforeRelease.ONESIGNAL_APP_ID)
        OneSignal.Notifications.addClickListener(onesignalListener)
    }

    private val onesignalListener = object : INotificationClickListener {
        override fun onClick(event: INotificationClickEvent) {
            val quote = event.notification.body ?: ""
            val actionId = event.result.actionId

            when (actionId) {
                "copy" -> {
                    this@Munu.copy(quote)
                    Toast.makeText(this@Munu, getString(R.string.quote_copied), Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
    }
}