package com.example.asd.hotels

val DATABASE_NAME = "HotelDB"

val TABLE_NAME_HOTEL = "Hotels"
val TABLE_NAME_CUSTOMER = "Customers"
val TABLE_NAME_USER = "Users"
val TABLE_NAME_RATING = "Ratings"
val TABLE_NAME_CATEGORY = "Categories"
val TABLE_NAME_LOCATION = "Locations"
val TABLE_NAME_ACTIVITY = "Activities"
val TABLE_NAME_ACTIVITY_AVAILABILITY = "ActivityAvailability"
val TABLE_NAME_PHOTOGALLERY = "Photogalleries"

val COL_ID = "id"
val COL_HOTEL_ID = "hotel_id"
val COL_ACTIVITY_ID = "activity_id"
val COL_CUSTOMER_ID = "customer_id"
val COL_USER_ID = "user_id"
val COL_PHOTOGALLERY_ID = "photogallery_id"

val COL_NAME = "name"
val COL_CAPACITY = "capacity"
val COL_PRICE = "price"
val COL_DESCRIPTION = "description"

val COL_PASSWORD = "password"

val COL_STARS = "stars"
val COL_COMMENT = "comment"

val COL_DIRECTORY = "directory"


class DatabaseHandler (context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, 1) {
    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_TABLE_HOTEL = "CREATE TABLE $TABLE_NAME_HOTEL (id INTEGER PRIMARY KEY AUTOINCREMENT, category_id INTEGER, location_id INTEGER, name TEXT, capacity INTEGER, price INTEGER, description TEXT, photogallery_id INTEGER);"

        val CREATE_TABLE_CUSTOMER = "CREATE TABLE $TABLE_NAME_CUSTOMER (id INTEGER PRIMARY KEY AUTOINCREMENT, user_id INTEGER, name TEXT);"

        val CREATE_TABLE_USER = "CREATE TABLE $TABLE_NAME_USER (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, password TEXT);"

        val CREATE_TABLE_RATING = "CREATE TABLE $TABLE_NAME_RATING (id INTEGER PRIMARY KEY AUTOINCREMENT, customer_id INTEGER, hotel_id INTEGER, stars INTEGER, comment TEXT);"
        
        val CREATE_TABLE_CATEGORY = "CREATE TABLE $TABLE_NAME_CATEGORY (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT);"
        
        val CREATE_TABLE_LOCATION = "CREATE TABLE $TABLE_NAME_LOCATION (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT);"
        
        val CREATE_TABLE_ACTIVITY = "CREATE TABLE $TABLE_NAME_ACTIVITY (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT);"
        
        val CREATE_TABLE_ACTIVITY_AVAILABILITY = "CREATE TABLE $TABLE_NAME_ACTIVITY_AVAILABILITY (id INTEGER PRIMARY KEY AUTOINCREMENT, activity_id INTEGER, hotel_id INTEGER);"
        
        val CREATE_TABLE_PHOTOGALLERY = "CREATE TABLE $TABLE_NAME_PHOTOGALLERY (id INTEGER PRIMARY KEY AUTOINCREMENT, directory TEXT);"

        db?.execSQL(CREATE_TABLE_HOTEL)
        db?.execSQL(CREATE_TABLE_CUSTOMER)
        db?.execSQL(CREATE_TABLE_USER)
        db?.execSQL(CREATE_TABLE_RATING)
        db?.execSQL(CREATE_TABLE_CATEGORY)
        db?.execSQL(CREATE_TABLE_LOCATION)
        db?.execSQL(CREATE_TABLE_ACTIVITY)
        db?.execSQL(CREATE_TABLE_ACTIVITY_AVAILABILITY)
        db?.execSQL(CREATE_TABLE_PHOTOGALLERY)

    }
    
    // on upgrade of database version
    override fun onUpgrade(db SQLiteDatabase?, oldVersion: Int, newVersion: Int) { 
        TODO(reason: "NOT IMPLEMENTED")
    }
    
    fun insertHotel(hotel: Hotel) {
        val db = this.writableDatabase
        var cv = contentValues()
        cv.put(COL_NAME)
        cv.put(COL_CATEGORY_ID)
        cv.put(COL_LOCATION_ID)
        cv.put(COL_NAME)
        cv.put(COL_CAPACITY)
        cv.put(COL_PRICE)
        cv.put(COL_PHOTOGALLERY_ID)
        
        var result = db.insert(TABLE_NAME_HOTEL, nullColumnHack null, cv)
        if (result == -1L) {
            /* Write some error message "Insert Hotels failed!" */
        }
        db.close()
    }
    
    fun getHotels() : MutableList<Hotel> {
        var hotel_list : MutableList<Hotel> = ArrayList()
        val db = this.readableDatabase
        val query = "SELECT * FROM $TABLE_NAME_HOTEL;" 
        val result = db.rawQuery(query)
        
        if (result.moveToFirst()) {
            do {
                var hotel = Hotel()
                hotel.id = result.getString(columnIndex result.getColumnIndex(COL_ID)).toInt()
                hotel.category_id = result.getString(columnIndex result.getColumnIndex(COL_CATEGORY_ID)).toInt()
                hotel.location_id = result.getString(columnIndex result.getColumnIndex(COL_LOCATION_ID)).toInt()
                hotel.name = result.getString(columnIndex result.getColumnIndex(COL_NAME)).toString()
                hotel.capacity = result.getString(columnIndex result.getColumnIndex(COL_CAPACITY)).toInt()
                hotel.price = result.getString(columnIndex result.getColumnIndex(COL_PRICE)).toInt()
                hotel.photogallery_id = result.getString(columnIndex result.getColumnIndex(COL_PHOTOGALLERY_ID)).toInt()
                
                list.add(hotel_list)
            } while (result.moveToNext()); 
        }
        result.close()
        
        db.close()
        return hotel_list
    }
}
