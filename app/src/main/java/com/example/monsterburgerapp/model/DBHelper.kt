package com.example.monsterburgerapp.model

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import androidx.fragment.app.FragmentActivity


class DBHelper (context:FragmentActivity):SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION){

    companion object{
        private var DATABASE_VERSION = 1
        private var DATABASE_NAME = "info"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db!!.execSQL("CREATE TABLE" + Tables.information["TABLE_NAME"] + " (" +
                Tables.information["_id"] + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                Tables.information["_nombre"] + " TEXT NOT NULL, " +
                Tables.information["_Direccion"] + " TEXT NOT NULL, " +
                Tables.information["_telefono"] + " TEXT NOT NULL, " +
                Tables.information["_correo"] + " TEXT NOT NULL)")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }

    fun insert(name: String, address: String, email: String, phone: String, s: String){
        val data = ContentValues()
        data.put(Tables.information["_nombre,name"], name)
        data.put(Tables.information["_Direccion"], address)
        data.put(Tables.information["_correo"], email)
        data.put(Tables.information["_telefono"], phone)
        val  db = this.writableDatabase

        db.insert(Tables.information["TABLE_NAME"], null, data)
        db.close()
    }

    fun edit(Id:Int, name: String, address: String, email: String, phone: String){

        val args = arrayOf(Id.toString())
        val data = ContentValues()

        data.put(Tables.information["_nombre"], name)
        data.put(Tables.information["_Direccion"], address)
        data.put(Tables.information["_correo"], email)
        data.put(Tables.information["_telefono"], phone)

        val db = this.writableDatabase

        db.update(Tables.information["TABLE_NAME"], data, Tables.information["_id"]+"=?", args)
        db.close()
    }
}