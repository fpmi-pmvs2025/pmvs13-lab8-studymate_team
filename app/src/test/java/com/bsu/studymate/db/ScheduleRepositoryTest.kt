package com.bsu.studymate.db

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(manifest = Config.NONE)
class ScheduleRepositoryTest {
    private lateinit var context: Context
    private lateinit var scheduleRepository: ScheduleRepository
    private lateinit var dbHelper: AppOpenHelper

    @Before
    fun setup() {
        context = ApplicationProvider.getApplicationContext()
        dbHelper = AppOpenHelper(context)
        scheduleRepository = ScheduleRepository(context)
        
        // Create test schedule entries
        dbHelper.writableDatabase.use { db ->
            db.execSQL("""
                INSERT INTO ${DbConst.T_SCHEDULE} (
                    ${DbConst.S_DAY}, ${DbConst.S_GROUP_NO}, 
                    ${DbConst.S_SUBJECT}, ${DbConst.S_TIME}, 
                    ${DbConst.S_ROOM}
                ) VALUES (?, ?, ?, ?, ?)
            """.trimIndent(), arrayOf(
                "Monday", 1, "Mathematics", "09:00", "101"
            ))
            db.execSQL("""
                INSERT INTO ${DbConst.T_SCHEDULE} (
                    ${DbConst.S_DAY}, ${DbConst.S_GROUP_NO}, 
                    ${DbConst.S_SUBJECT}, ${DbConst.S_TIME}, 
                    ${DbConst.S_ROOM}
                ) VALUES (?, ?, ?, ?, ?)
            """.trimIndent(), arrayOf(
                "Monday", 1, "Physics", "10:30", "202"
            ))
        }
    }

    @After
    fun cleanup() {
        dbHelper.close()
    }

    @Test
    fun `test get schedule for group and day`() = runBlocking {
        val lessons = scheduleRepository.getForGroupDay(1, "Monday")
        
        assertEquals(2, lessons.size)
        
        val firstLesson = lessons[0]
        assertEquals("Mathematics", firstLesson.subject)
        assertEquals("09:00", firstLesson.time)
        assertEquals("101", firstLesson.room)
        
        val secondLesson = lessons[1]
        assertEquals("Physics", secondLesson.subject)
        assertEquals("10:30", secondLesson.time)
        assertEquals("202", secondLesson.room)
    }

    @Test
    fun `test get empty schedule for non-existent group`() = runBlocking {
        val lessons = scheduleRepository.getForGroupDay(999, "Monday")
        assertTrue(lessons.isEmpty())
    }

    @Test
    fun `test get empty schedule for non-existent day`() = runBlocking {
        val lessons = scheduleRepository.getForGroupDay(1, "Sunday")
        assertTrue(lessons.isEmpty())
    }
} 