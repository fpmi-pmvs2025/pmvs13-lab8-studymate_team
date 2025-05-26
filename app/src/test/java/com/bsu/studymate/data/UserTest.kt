package com.bsu.studymate.data

import org.junit.Assert.*
import org.junit.Test

class UserTest {
    @Test
    fun `test user creation with default id`() {
        val user = User(
            email = "test@example.com",
            pass = "password123",
            firstName = "John",
            lastName = "Doe",
            groupNo = 1
        )
        
        assertEquals(0L, user.id)
        assertEquals("test@example.com", user.email)
        assertEquals("password123", user.pass)
        assertEquals("John", user.firstName)
        assertEquals("Doe", user.lastName)
        assertEquals(1, user.groupNo)
    }

    @Test
    fun `test user creation with custom id`() {
        val user = User(
            id = 1L,
            email = "test@example.com",
            pass = "password123",
            firstName = "John",
            lastName = "Doe",
            groupNo = 1
        )
        
        assertEquals(1L, user.id)
    }
} 