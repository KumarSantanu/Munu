package app.santanu.love.munu.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import app.santanu.love.munu.ui.MainActivity
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class LauncherActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)

        splashScreen.setKeepOnScreenCondition { true }

        lifecycleScope.launch {
            delay(1000)
            navigateToAuthScreen()
        }
    }

    private fun navigateToAuthScreen() {
        val intent = Intent(this@LauncherActivity, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}