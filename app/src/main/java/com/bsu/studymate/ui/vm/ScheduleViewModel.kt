package com.bsu.studymate.ui.vm

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.bsu.studymate.db.ScheduleRepository

class ScheduleViewModel(app: Application) : AndroidViewModel(app) {
    private val repo = ScheduleRepository(app)
    suspend fun load(group: Int, day: String) = repo.getForGroupDay(group, day)
}