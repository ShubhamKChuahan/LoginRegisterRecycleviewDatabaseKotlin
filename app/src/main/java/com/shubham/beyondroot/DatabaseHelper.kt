package com.shubham.beyondroot

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import java.util.*

class DatabaseHelper(context: Context?) : SQLiteOpenHelper(context, DATABASE_NAME, null, 1) {
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("create table " + TABLE_NAME + " (NAME TEXT PRIMARY KEY,EMAIL TEXT NOT NULL,PHONE TEXT NOT NULL,GENDER TEXT NOT NULL,CITY TEXT NOT NULL,PASSWORD TEXT NOT NULL)")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME)
        onCreate(db)
    }

    fun insertData(name: String?, email: String?, phone: String?, gender: String?, city: String?, password: String?): Boolean {
        return try {
            val db = this.writableDatabase
            val contentValues = ContentValues()
            contentValues.put(COL_1, name)
            contentValues.put(COL_2, email)
            contentValues.put(COL_3, phone)
            contentValues.put(COL_4, gender)
            contentValues.put(COL_5, city)
            contentValues.put(COL_6, password)
            val result = db.insert(TABLE_NAME, null, contentValues)
            if (result == -1L) false else true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    fun NameData(name: String): Boolean {
        var query: String? = null
        val db = this.writableDatabase
        query = "SELECT * FROM " + TABLE_NAME + " WHERE " + COL_1 + " like '%" + name + "%'"
        val cursor = db.rawQuery(query, null)
        if (cursor.count <= 0) {
            cursor.close()
            return false
        }
        cursor.close()
        return true
    }

    fun checkUserLogin(name: String, password: String): Boolean {
        val db = this.writableDatabase
        val whereclause = "NAME=? and PASSWORD=?"
        val whereargs = arrayOf(name, password)
        val cursor = db.query(
                TABLE_NAME, arrayOf("NAME", "PASSWORD"),
                whereclause,
                whereargs,
                null, null, null
        )
        val count = cursor.count
        cursor.close()
        return count > 0
    }

    fun listContacts(): ArrayList<Contacts> {
        val sql = "select * from " + TABLE_NAME
        val db = this.readableDatabase
        val storeContacts = ArrayList<Contacts>()
        val cursor = db.rawQuery(sql, null)
        if (cursor.moveToFirst()) {
            do {
                val name = cursor.getString(0)
                val email = cursor.getString(1)
                val phone = cursor.getString(2)
                val gender = cursor.getString(3)
                val city = cursor.getString(4)
                storeContacts.add(Contacts(name, email, phone, gender, city))
            } while (cursor.moveToNext())
        }
        cursor.close()
        return storeContacts
    }

    companion object {
        const val DATABASE_NAME = "User.db"
        const val TABLE_NAME = "user_table"
        const val COL_1 = "Name"
        const val COL_2 = "Email"
        const val COL_3 = "Phone"
        const val COL_4 = "Gender"
        const val COL_5 = "City"
        const val COL_6 = "Password"
    }
}