package com.bsu.studymate.ui

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.bsu.studymate.databinding.ActivityLoginBinding
import com.bsu.studymate.db.AuthRepository
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {

    private lateinit var b: ActivityLoginBinding
    private lateinit var prefs: SharedPreferences
    private lateinit var repo: AuthRepository

    private val PREFS = "auth"
    private val KEY_UID = "user_id"
    private val KEY_GROUP = "group_no"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(b.root)

        prefs = getSharedPreferences(PREFS, MODE_PRIVATE)
        repo  = AuthRepository(this)

        // авто-логин
        if (prefs.contains(KEY_UID)) startSchedule()

        b.btnLogin.setOnClickListener {
            val email = b.etEmail.text?.toString()?.trim().orEmpty()
            val pass  = b.etPassword.text?.toString()?.trim().orEmpty()

            lifecycleScope.launch {
                val user = repo.login(email, pass)
                if (user == null) {
                    Snackbar.make(b.root, "Неверные данные", Snackbar.LENGTH_SHORT).show()
                } else {
                    prefs.edit()
                        .putLong(KEY_UID, user.id)
                        .putInt(KEY_GROUP, user.groupNo)
                        .putString("first_name", user.firstName)
                        .putString("last_name",  user.lastName)
                        .apply()
                    startSchedule()
                }
            }
        }
    }

    private fun startSchedule() {
        startActivity(Intent(this, ScheduleActivity::class.java))
        finish()
    }
}