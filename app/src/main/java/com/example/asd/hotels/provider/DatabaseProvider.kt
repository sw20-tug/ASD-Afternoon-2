package com.example.asd.hotels.provider

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.asd.hotels.DB_TABLE.DBName
import com.example.asd.hotels.dummy.HotelData

class hotelReaderDbHelper(context: Context) : SQLiteOpenHelper(
    context, DBName.DATABASE_NAME, null, DBName.DATABASE_VERSION
) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(DBName.SQL_DELETE_ENTRIES)
        db.execSQL(DBName.CREATE_LOCATION_TABLE)
        db.execSQL(DBName.CREATE_ACTIVITY_AVAILABILITY_TABLE)
        db.execSQL(DBName.CREATE_ACTIVITY_TABLE)
        db.execSQL(DBName.CREATE_CATEGORY_TABLE)
        db.execSQL(DBName.CREATE_CUSTOMER_TABLE)
        db.execSQL(DBName.CREATE_HOTEL_TABLE)
        db.execSQL(DBName.CREATE_PHOTOGALLERY_TABLE)
        db.execSQL(DBName.CREATE_RATING_TABLE)
        db.execSQL(DBName.CREATE_USER_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(DBName.SQL_DELETE_ENTRIES)
        onCreate(db)
    }

    override fun onDowngrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        onUpgrade(db, oldVersion, newVersion)
    }

    companion object {
        // If you change the database schema, you must increment the database version.
        const val DATABASE_VERSION = 3
        const val DATABASE_NAME = DBName.DATABASE_NAME
    }
}

class DatabaseProvider(context: Context) {
    val dbHelper = hotelReaderDbHelper(context)

    fun insert_location(location_name: String): Long? {
        val db = dbHelper.writableDatabase
        // Create a new map of values, where column names are the keys
        val values = ContentValues().apply {
            put(DBName.HOTEL_LOCATION_NAME_COLUMN, location_name)
        }

        // Insert the new row, returning the primary key value of the new row
        return db?.insert(DBName.LOCATION_TABLE_NAME, null, values)
    }

    fun insert_category(location_name: String): Long? {
        val db = dbHelper.writableDatabase
        // Create a new map of values, where column names are the keys
        val values = ContentValues().apply {
            put(DBName.HOTEL_CATEGORY_NAME_COLUMN, location_name)
        }

        // Insert the new row, returning the primary key value of the new row
        return db?.insert(DBName.CATEGORY_TABLE_NAME, null, values)
    }

    fun insert_photo(path: Int): Long? {
        val db = dbHelper.writableDatabase
        // Create a new map of values, where column names are the keys
        val values = ContentValues().apply {
            put(DBName.HOTEL_PHOTOGALLERY_DIRECTION_COLUMN, path)
        }

        // Insert the new row, returning the primary key value of the new row
        return db?.insert(DBName.PHOTOGALLERY_TABLE_NAME, null, values)
    }

    fun insert_hotel(
        category_id: Int, location_id: Int, name: String, capacity: Int, price: Int,
        description: String, photogallery_id: Int
    ) {
        val db = dbHelper.writableDatabase
        // Create a new map of values, where column names are the keys
        val values = ContentValues().apply {
            put(DBName.HOTEL_LOCATION_ID_COLUMN, location_id)
            put(DBName.HOTEL_NAME_COLUMN, name)
            put(DBName.HOTEL_CAPACITY_COLUMN, capacity)
            put(DBName.HOTEL_PRICE_COLUMN, price)
            put(DBName.HOTEL_DESCRIPTION_COLUMN, description)
            put(DBName.HOTEL_PHOTOGALLERY_ID_COLUMN, photogallery_id)
        }

        // Insert the new row, returning the primary key value of the new row
        val newRowId = db?.insert(DBName.HOTEL_TABLE_NAME, null, values)
    }

    fun get_location(id: Int): String {
        val db = dbHelper.readableDatabase

        // See: https://developer.android.com/training/data-storage/sqlite
        val cursor = db.query(
            DBName.LOCATION_TABLE_NAME,
            null,
            "${DBName.HOTEL_LOCATION_ID_COLUMN} = ?",
            arrayOf(id.toString()),
            null,
            null,
            null
        )
        var retVal = ""
        with(cursor) {
            while (moveToNext()) {
                retVal = getString(getColumnIndexOrThrow(DBName.HOTEL_LOCATION_NAME_COLUMN))
            }
        }

        return retVal
    }

    fun get_photo(id: Int): Int {
        val db = dbHelper.readableDatabase

        val cursor = db.query(
            DBName.PHOTOGALLERY_TABLE_NAME,
            null,
            "${DBName.HOTEL_PHOTOGALLERY_ID_COLUMN} = ?",
            arrayOf(id.toString()),
            null,
            null,
            null
        )

        var retVal = 0
        with(cursor) {
            while (moveToNext()) {
                    retVal = getInt(getColumnIndexOrThrow(DBName.HOTEL_PHOTOGALLERY_DIRECTION_COLUMN))
            }
        }

        return retVal
    }

    fun get_hotels(id: Int): HotelData {
        val db = dbHelper.readableDatabase

        val cursor = db.query(
            DBName.HOTEL_TABLE_NAME,
            null,
            "${DBName.HOTEL_ID_COLUMN} = ?",
            arrayOf(id.toString()),
            null,
            null,
            null
        )

        val returnVal = HotelData(0, 0, "", 0, "", 0, 0, "", 0 , 0)
        with(cursor) {
            while (moveToNext()) {
                returnVal.hotel_id = cursor.getInt(cursor.getColumnIndex(DBName.HOTEL_ID_COLUMN))
                returnVal.category = cursor.getInt(cursor.getColumnIndex(DBName.HOTEL_CATEGORY_ID_COLUMN))
                val loc_id = cursor.getInt(cursor.getColumnIndex(DBName.HOTEL_LOCATION_ID_COLUMN))
                returnVal.location = get_location(loc_id)
                val image_id = cursor.getInt(cursor.getColumnIndex(DBName.HOTEL_PHOTOGALLERY_ID_COLUMN))
                returnVal.image = get_photo(image_id)
                returnVal.hotel_name = cursor.getString(cursor.getColumnIndex(DBName.HOTEL_NAME_COLUMN))
                returnVal.hotel_capacity = cursor.getInt(cursor.getColumnIndex(DBName.HOTEL_CAPACITY_COLUMN))
                returnVal.price = cursor.getInt(cursor.getColumnIndex(DBName.HOTEL_PRICE_COLUMN))
                returnVal.hotel_description = cursor.getString(cursor.getColumnIndex(DBName.HOTEL_DESCRIPTION_COLUMN))
            }
        }

        return returnVal
    }

}