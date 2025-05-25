package com.bsu.studymate.db

import android.content.Context
import com.bsu.studymate.data.Lesson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ScheduleRepository(ctx: Context) {
    private val helper = AppOpenHelper(ctx)

    suspend fun getForGroupDay(group: Int, day: String): List<Lesson> =
        withContext(Dispatchers.IO) {
            val res = mutableListOf<Lesson>()
            helper.readableDatabase.query(
                DbConst.T_SCHEDULE, null,
                "${DbConst.S_GROUP_NO}=? AND ${DbConst.S_DAY}=?",
                arrayOf(group.toString(), day),
                null, null,
                DbConst.S_TIME
            ).use { c ->
                while (c.moveToNext()) {
                    res += Lesson(
                        id       = c.getLong(c.getColumnIndexOrThrow(DbConst.S_ID)),
                        day      = day,
                        groupNo  = group,
                        subject  = c.getString(c.getColumnIndexOrThrow(DbConst.S_SUBJECT)),
                        time     = c.getString(c.getColumnIndexOrThrow(DbConst.S_TIME)),
                        room     = c.getString(c.getColumnIndexOrThrow(DbConst.S_ROOM))
                    )
                }
            }
            res
        }
}