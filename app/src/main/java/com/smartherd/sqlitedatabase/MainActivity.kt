package com.smartherd.sqlitedatabase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    fun insertData(view: View) {
        val name = Name.text.toString()
        val age = Age.text.toString()
        val phone = Phone.text.toString()
        val studentInfo = StudentInfo(name, age, phone)
        val mySQLiteOpenHelper = MySQLiteOpenHelper(this)
        mySQLiteOpenHelper.insertRecord(studentInfo)
    }

    fun readData(view: View) {
        val mySQLiteOpenHelper = MySQLiteOpenHelper(this)
        val data = mySQLiteOpenHelper.readRecord()
        var result = ""
        for (item in data) {
            result += "${item.name} ${item.age} ${item.phone} \n"
        }
        textView.text = result
    }

    fun updateData(view: View) {
        val name = Name.text.toString()
        val age = Age.text.toString()
        val phone = Phone.text.toString()
        val studentInfo = StudentInfo(name, age, phone)
        val mySQLiteOpenHelper = MySQLiteOpenHelper(this)
        mySQLiteOpenHelper.updateRecord(studentInfo)
    }

    fun deleteData(view: View) {
        val phone = Phone.text.toString()
        val mySQLiteOpenHelper = MySQLiteOpenHelper(this)
        mySQLiteOpenHelper.deleteRecord(phone)
    }
}
