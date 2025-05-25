package com.bsu.studymate.ui

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.bsu.studymate.databinding.ActivityScheduleBinding
import com.bsu.studymate.ui.adapter.LessonAdapter
import com.bsu.studymate.ui.vm.ScheduleViewModel
import kotlinx.coroutines.launch
import androidx.core.content.edit

class ScheduleActivity : AppCompatActivity() {

    private lateinit var b: ActivityScheduleBinding
    private val vm: ScheduleViewModel by viewModels()
    private lateinit var prefs: SharedPreferences
    private lateinit var adapter: LessonAdapter

    private val PREFS = "auth"
    private val KEY_GROUP = "group_no"
    private val KEY_FIRST_NAME = "first_name"
    private val KEY_LAST_NAME = "last_name"

    private val days = listOf(
        "Понедельник", "Вторник", "Среда",
        "Четверг", "Пятница"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityScheduleBinding.inflate(layoutInflater)
        setContentView(b.root)

        prefs = getSharedPreferences(PREFS, MODE_PRIVATE)
        val group = prefs.getInt(KEY_GROUP, -1)
        val first = prefs.getString(KEY_FIRST_NAME, "")!!
        val last  = prefs.getString(KEY_LAST_NAME, "")!!

        setSupportActionBar(b.toolbar)
        supportActionBar?.title = "$first $last"

        adapter = LessonAdapter()
        b.recycler.layoutManager = LinearLayoutManager(this)
        b.recycler.adapter = adapter

        b.spinnerDay.adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_dropdown_item,
            days
        )

        b.spinnerDay.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>, view: View?, pos: Int, id: Long
            ) { loadDay(days[pos], group) }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

        b.spinnerDay.setSelection(0, false)
        loadDay(days[0], group)

        b.btnLogout.setOnClickListener { logout() }
    }

    private fun loadDay(day: String, group: Int) {
        lifecycleScope.launch {
            val lessons = vm.load(group, day)
            adapter.submitList(lessons)
        }
    }

    private fun logout() {
        prefs.edit { clear() }
        startActivity(
            Intent(this, LoginActivity::class.java)
                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
        )
        finish()
    }
}