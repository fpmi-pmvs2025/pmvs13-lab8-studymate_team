package com.bsu.studymate.data

import org.junit.Assert.*
import org.junit.Test

class LessonTest {
    @Test
    fun `test lesson creation with default id`() {
        val lesson = Lesson(
            day = "Monday",
            groupNo = 1,
            subject = "Mathematics",
            time = "09:00",
            room = "101"
        )
        
        assertEquals(0L, lesson.id)
        assertEquals("Monday", lesson.day)
        assertEquals(1, lesson.groupNo)
        assertEquals("Mathematics", lesson.subject)
        assertEquals("09:00", lesson.time)
        assertEquals("101", lesson.room)
    }

    @Test
    fun `test lesson creation with custom id`() {
        val lesson = Lesson(
            id = 1L,
            day = "Monday",
            groupNo = 1,
            subject = "Mathematics",
            time = "09:00",
            room = "101"
        )
        
        assertEquals(1L, lesson.id)
    }
} 