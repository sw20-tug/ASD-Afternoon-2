package com.example.asd.hotels

// model class

data class Hotel(
    var id: Int = -1, 
    var category_id: Int = -1,
    var location_id: Int = -1,
    var name: String, 
    var capacity: Int = 0,
    var price: Int = 0,
    var description: String,
    var photogallery_id = -1
    
    constructor (
        id: Int, 
        category_id: Int, 
        location_id: Int, 
        name: String,
        capacity: Int,
        price: Int,
        description: String,
        photogallery_id: Int
    )
    
)

data class Customer(
    var id: Int = -1,
    var user_id: Int = -1,
    var name: String
    
    constructor (
        id: Int,
        user_id: Int,
        name: String
    )
)

//rating (rating_id, customer_id, hotel_id, stars, comment)

data class Rating(
    var id: Int = -1,
    var customer_id: Int = -1,
    var hotel_id: Int = -1,
    var stars: Int = -1,
    var comment: String
    
    constructor (
        id: Int,
        customer_id: Int,
        hotel_id: Int,
        stars: Int,
        comment: String
    )
)

//category (category_id, category_name)

data class Category(
    var id: Int = -1,
    var name: String
    
    constructor (
        id: Int,
        name: String
    )
)

//location (location_id, location_name)

data class Location(
    var id: Int = -1,
    var name: String
    
    constructor (
        id: Int,
        name: String
    )
)

//activity (activity_id, activity_name)

data class Activity(
    var id: Int = -1,
    var name: String
    
    constructor (
        id: Int,
        name: String
    )
)

//activity_availability (activity_id, hotel_id)

data class ActivityAvailability(
    var activity_id: Int = -1,
    var hotel_id: Int = -1
    
    constructor (
        activity_id: Int,
        hotel_id: Int
    )
)

//user (user_id, user_name, user_password, user_isadmin)

data class User(
    var id: Int = -1,
    var name: String,
    var password: String,
    var isadmin: Bool = False
    
    constructor (
        id: Int,
        name: String,
        password: String,
        isadmin: Bool
    )
)

//photogallery (photogallery_id, photogallery_directory)

data class Photogallery(
    var id: Int -1,
    var name: String
    
    constructor (
        id: Int,
        name: String
    )
)

