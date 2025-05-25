package com.bsu.studymate

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import com.bsu.studymate.ui.LoginActivity
import com.bsu.studymate.ui.ScheduleActivity

class MainActivity : AppCompatActivity() {

    private val PREFS     = "auth"
    private val KEY_UID   = "user_id"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)

        val prefs: SharedPreferences = getSharedPreferences(PREFS, MODE_PRIVATE)

        val next = if (prefs.contains(KEY_UID)) {
            Intent(this, ScheduleActivity::class.java)
        } else {
            Intent(this, LoginActivity::class.java)
        }

        startActivity(next)
        finish()
    }
}