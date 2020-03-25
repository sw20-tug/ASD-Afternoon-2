# Database schema

````
hotel (hotel_id, category_id, location_id, hotel_name, hotel_capacity, hotel_price, hotel_description, photogallery_id)
customer (customer_id, user_id, customer_name)
rating (rating_id, customer_id, hotel_id, stars, comment)
category (category_id, category_name)
location (location_id, location_name)
activity (activity_id, activity_name)
activity_availability (activity_id, hotel_id)
user (user_id, user_name, user_password, user_isadmin)
photogallery (photogallery_id, photogallery_directory)
````
## Assumptions

* Each hotel is located at exactly one location.
* Every customer is a user, but not every user is a customer.
* Photo galleries are identified by a directory where photos are saved in.
  That means, a photo cannot be used in more than one gallery (unless it is 
  stored redundandly).
* A comment can only be stored when rating a hotel.
* All the IDs are generated automatically by the DBMS (auto-increment).
