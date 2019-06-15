package com.wolo.a222.Storage

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.wolo.a222.Model.SKU.Sku


class DBHelper(context: Context): SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VER ) {
    override fun onCreate(db: SQLiteDatabase?) {
       val CREATE_TABLE_QUERY = ("CREATE_TABLE $TABLE_NAME ($ID INTEGER PRIMARY KEY, $SKU_NAME TEXT, $SKU_PRICE TEXT)")

       db!!.execSQL(CREATE_TABLE_QUERY)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
       db!!.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db!!)
    }

    companion object{
            private val DATABASE_VER = 1
            private val DATABASE_NAME = "GOODS.db"

            //Table
            private val TABLE_NAME ="GOODS"
            private val ID ="Id"
            private val SKU_NAME ="Sku_name"
            private val SKU_PRICE ="Sku_price"
        }

    //CRUD
    val allGoods:List<Sku>
        get() {
            val lstSku = ArrayList<Sku>()
            val selectQuery = "SELECT * FROM $TABLE_NAME"
            val db = this.writableDatabase
            val cursor = db.rawQuery(selectQuery, null)
            if (cursor.moveToFirst())
            {
                do {
                    var id = cursor.getInt(cursor.getColumnIndex(ID))
                    var name = cursor.getString(cursor.getColumnIndex(SKU_NAME))
                    var price = cursor.getString(cursor.getColumnIndex(SKU_PRICE))
                    val sku = Sku(id, name, price)
                    lstSku.add(sku)
                } while (cursor.moveToNext())
            }
            db.close()
         return lstSku
        }

    fun addSku(sku: Sku)
    {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(ID, sku.Id)
        values.put(SKU_NAME, sku.name)
        values.put(SKU_PRICE, sku.price)

        db.insert(TABLE_NAME, null, values)
        db.close()
    }

    fun updateSku(sku: Sku): Int
    {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(ID, sku.Id)
        values.put(SKU_NAME, sku.name)
        values.put(SKU_PRICE, sku.price)

       return db.update(TABLE_NAME, values, "$ID=?", arrayOf(sku.Id.toString()))

    }


    fun deleteSku(sku: Sku)
    {
        val db = this.writableDatabase

        db.delete(TABLE_NAME, "$ID=?", arrayOf(sku.Id.toString()))
        db.close()
    }
}