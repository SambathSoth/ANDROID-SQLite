package com.smartherd.sqlitedatabase

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.ContactsContract
import android.widget.Toast

const val DATABASE_NAME = "Student Information"
const val TABLE_NAME = "StudentInformation"
const val COL1 = "Name"
const val COL2 = "Age"
const val COL3 = "Phone"
class MySQLiteOpenHelper(private val context: Context):  SQLiteOpenHelper(context, DATABASE_NAME, null, 1){
    override fun onCreate(database: SQLiteDatabase?) {
        val query = "CREATE TABLE $TABLE_NAME ($COL1 VARCHAR(256), $COL2 VARCHAR(256), $COL3 VARCHAR(256));"
        database?.execSQL(query)
        Toast.makeText(context, "Table Created", Toast.LENGTH_SHORT).show()
    }

    fun insertRecord(studentInfo: StudentInfo) {
        val database = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(COL1, studentInfo.name)
        contentValues.put(COL2, studentInfo.age)
        contentValues.put(COL3, studentInfo.phone)
        val result = database.insert(TABLE_NAME, null, contentValues)

        if (result == -1L) {
            Toast.makeText(context, "Insert Fail", Toast.LENGTH_SHORT).show()
        }
        else {
            Toast.makeText(context, "Insert Success", Toast.LENGTH_SHORT).show()
        }
    }

    fun readRecord(): MutableList<StudentInfo> {
        val database = this.readableDatabase
        val query = "SELECT * FROM $TABLE_NAME"
        val data = database.rawQuery(query, null)
        val studentData = mutableListOf<StudentInfo>()

        if (data.moveToFirst()) {
            do {
                val name = data.getString(0)
                val age = data.getString(1)
                val phone = data.getString(2)
                val studentInfo = StudentInfo(name, age, phone)
                studentData.add(studentInfo)
            } while (data.moveToNext())
        }
        return studentData
    }

    fun updateRecord(studentInfo: StudentInfo) {
        val database = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(COL1, studentInfo.name)
        contentValues.put(COL2, studentInfo.age)
        contentValues.put(COL3, studentInfo.phone)
        val result = database.update(TABLE_NAME, contentValues, "$COL3 = ${studentInfo.phone} AND $COL2 = ${studentInfo.age}", null)
        if (result == -1) {
            Toast.makeText(context, "Update Fail", Toast.LENGTH_SHORT).show()
        }
        else {
            Toast.makeText(context, "Update Success", Toast.LENGTH_SHORT).show()
        }
    }

    fun deleteRecord(phone: String) {
        val database = this.writableDatabase
        val result = database.delete(TABLE_NAME, "$COL3 = $phone", null)
        if (result == -1) {
            Toast.makeText(context, "Delete Fail", Toast.LENGTH_SHORT).show()
        }
        else {
            Toast.makeText(context, "Delete Success", Toast.LENGTH_SHORT).show()
        }
    }
    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {}
}