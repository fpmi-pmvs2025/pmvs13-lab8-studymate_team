package com.bsu.studymate.db

import android.content.Context
import com.bsu.studymate.data.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AuthRepository(ctx: Context) {

    private val helper = AppOpenHelper(ctx)

    suspend fun login(email: String, pass: String): User? =
        withContext(Dispatchers.IO) {
            helper.readableDatabase.use { db ->
                db.query(
                    DbConst.T_USERS,
                    null,
                    "${DbConst.U_EMAIL}=? AND ${DbConst.U_PASS}=?",
                    arrayOf(email, pass),
                    null, null, null
                ).use { c ->
                    if (c.moveToFirst()) {
                        User(
                            id        = c.getLong(c.getColumnIndexOrThrow(DbConst.U_ID)),
                            email     = email,
                            pass      = pass,
                            firstName = c.getString(c.getColumnIndexOrThrow(DbConst.U_FIRSTNAME)),
                            lastName  = c.getString(c.getColumnIndexOrThrow(DbConst.U_LASTNAME)),
                            groupNo   = c.getInt(c.getColumnIndexOrThrow(DbConst.U_GROUP_NO))
                        )
                    } else null
                }
            }
        }
}