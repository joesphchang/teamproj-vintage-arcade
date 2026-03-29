# Vintage Arcade RESTful Web Services (Team Project)
--- 

## Overview 



--- 

## Resources

### Venue 

### Cabinets 



--- 

## Service Calls

Service Calls 

 

Venues 

GET /venues – Retrieve a list of all venues 

GET /venues/{id} – Retrieve details for a specific venue, including its cabinets 

POST /venues – Add a new venue (admin only) 

PUT /venues/{id} – Update venue information (admin only) 

DELETE /venues/{id} – Remove a venue (admin only) 

Cabinets 

GET /cabinets – Retrieve a list of all arcade games across venues 

GET /cabinets/{id} – Retrieve details of a specific arcade game 

POST /cabinets – Add a new arcade cabinet to a venue (admin only) 

PUT /cabinets/{id} – Update arcade cabinet information (admin only) 

DELETE /cabinets/{id} – Remove an arcade cabinet from a venue (admin only) 

 

Filtering & Searching Examples 

GET /venues?location=Madison – Get venues in Madison 

GET /cabinets?manufacturer=Atari – Get all cabinets manufactured by Atari 

GET /cabinets?condition=Excellent&year=1985 – Get cabinets in excellent condition from 1985 


--- 

## Curl List
