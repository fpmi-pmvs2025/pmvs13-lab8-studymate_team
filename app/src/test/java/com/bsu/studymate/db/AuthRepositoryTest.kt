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
class AuthRepositoryTest {
    private lateinit var context: Context
    private lateinit var authRepository: AuthRepository
    private lateinit var dbHelper: AppOpenHelper

    @Before
    fun setup() {
        context = ApplicationProvider.getApplicationContext()
        dbHelper = AppOpenHelper(context)
        authRepository = AuthRepository(context)
        
        // Create test user
        dbHelper.writableDatabase.use { db ->
            db.execSQL("""
                INSERT INTO ${DbConst.T_USERS} (
                    ${DbConst.U_EMAIL}, ${DbConst.U_PASS}, 
                    ${DbConst.U_FIRSTNAME}, ${DbConst.U_LASTNAME}, 
                    ${DbConst.U_GROUP_NO}
                ) VALUES (?, ?, ?, ?, ?)
            """.trimIndent(), arrayOf(
                "test@example.com", "password123",
                "John", "Doe", 1
            ))
        }
    }

    @After
    fun cleanup() {
        dbHelper.close()
    }

    @Test
    fun `test successful login`() = runBlocking {
        val user = authRepository.login("test@example.com", "password123")
        
        assertNotNull(user)
        assertEquals("test@example.com", user?.email)
        assertEquals("password123", user?.pass)
        assertEquals("John", user?.firstName)
        assertEquals("Doe", user?.lastName)
        assertEquals(1, user?.groupNo)
    }

    @Test
    fun `test failed login with wrong password`() = runBlocking {
        val user = authRepository.login("test@example.com", "wrongpassword")
        assertNull(user)
    }

    @Test
    fun `test failed login with non-existent email`() = runBlocking {
        val user = authRepository.login("nonexistent@example.com", "password123")
        assertNull(user)
    }
} 