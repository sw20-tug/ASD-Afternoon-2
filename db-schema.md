# DB-Schema

````
hotel (hotel_id, category_id, location_id, hotel_name, hotel_capacity, hotel_price, hotel_description, photogallery_id)
customer (customer_id, user_id, customer_name)
rating (customer_id, hotel_id, stars, comment)
category (category_id, category_name)
location (location_id, location_name)
activity (activity_id, activity_name)
activity_availability (activity_id, hotel_id)
user (user_id, user_name, user_password, user_isadmin)
photogallery (photogallery_id, photogallery_directory)
````
