package com.bsu.studymate.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


object DbConst {
    const val DB_NAME = "studymate.db"
    const val DB_VER  = 6

    const val T_USERS    = "users"
    const val U_ID       = "_id"
    const val U_EMAIL    = "email"
    const val U_PASS     = "pass"
    const val U_FIRSTNAME = "first_name"
    const val U_LASTNAME  = "last_name"
    const val U_GROUP_NO = "group_no"

    const val T_SCHEDULE = "schedule"
    const val S_ID       = "_id"
    const val S_DAY      = "day"
    const val S_GROUP_NO = "group_no"
    const val S_SUBJECT  = "subject"
    const val S_TIME     = "time"
    const val S_ROOM     = "room"
}

class AppOpenHelper(ctx: Context)
    : SQLiteOpenHelper(ctx, DbConst.DB_NAME, null, DbConst.DB_VER) {

    override fun onCreate(db: SQLiteDatabase) {

        db.execSQL("""
        CREATE TABLE ${DbConst.T_USERS}(
            ${DbConst.U_ID}        INTEGER PRIMARY KEY AUTOINCREMENT,
            ${DbConst.U_EMAIL}     TEXT UNIQUE NOT NULL,
            ${DbConst.U_PASS}      TEXT NOT NULL,
            ${DbConst.U_FIRSTNAME} TEXT NOT NULL,
            ${DbConst.U_LASTNAME}  TEXT NOT NULL,
            ${DbConst.U_GROUP_NO}  INTEGER NOT NULL
        );
    """.trimIndent())

        db.execSQL("""
        INSERT INTO ${DbConst.T_USERS}
            (${DbConst.U_EMAIL}, ${DbConst.U_PASS},
             ${DbConst.U_FIRSTNAME}, ${DbConst.U_LASTNAME}, ${DbConst.U_GROUP_NO})
        VALUES
            ('ivanov@bsu.by','1111','Иван','Иванов',1),
            ('petrova@bsu.by','2222','Анна','Петрова',2),
            ('smith@bsu.by',  '3333','John','Smith',3),
            ('lee@bsu.by',    '4444','Min','Lee',4);
    """)

        db.execSQL("""
            CREATE TABLE ${DbConst.T_SCHEDULE}(
                ${DbConst.S_ID}       INTEGER PRIMARY KEY AUTOINCREMENT,
                ${DbConst.S_DAY}      TEXT NOT NULL,
                ${DbConst.S_GROUP_NO} INTEGER NOT NULL,
                ${DbConst.S_SUBJECT}  TEXT NOT NULL,
                ${DbConst.S_TIME}     TEXT NOT NULL,
                ${DbConst.S_ROOM}     TEXT NOT NULL
            );
        """.trimIndent())

        val days      = listOf("Понедельник","Вторник","Среда","Четверг","Пятница")
        val times     = listOf("08:15-09:35","09:45-11:05","11:15-12:35","13:00-14:20")

        val subjectsByDay = mapOf(
            "Понедельник" to listOf("МатАнализ","Физкультура","Программирование","История"),
            "Вторник"     to listOf("ОиМП","Английский язык","Физика","Философия"),
            "Среда"       to listOf("ДМИМЛ","ТПМП","Алгебра","Экономика"),
            "Четверг"     to listOf("ПМиВС","ЛинАл","Химия","ОБЖ"),
            "Пятница"     to listOf("АиТЧ","Информатика","Социология","Проектная работа")
        )

        val sql = StringBuilder(
            "INSERT INTO ${DbConst.T_SCHEDULE}(" +
                    "${DbConst.S_DAY},${DbConst.S_GROUP_NO}," +
                    "${DbConst.S_SUBJECT},${DbConst.S_TIME},${DbConst.S_ROOM}) VALUES"
        )

        for (group in 1..4) {
            for ((dayIdx, day) in days.withIndex()) {
                val subjList = subjectsByDay.getValue(day)

                times.forEachIndexed { pairIdx, time ->
                    val subj = subjList[(pairIdx + group - 1) % subjList.size]

                    val room = "${dayIdx + 1}${group}${pairIdx + 1}".toInt()

                    sql.append("('$day',$group,'$subj','$time','$room'),")
                }
            }
        }
        sql.setLength(sql.length - 1)
        db.execSQL(sql.toString())
    }

    override fun onUpgrade(db: SQLiteDatabase, oldV: Int, newV: Int) {
        db.execSQL("DROP TABLE IF EXISTS ${DbConst.T_USERS}")
        db.execSQL("DROP TABLE IF EXISTS ${DbConst.T_SCHEDULE}")
        onCreate(db)
    }
}