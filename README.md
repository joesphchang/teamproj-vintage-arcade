# :joystick: Vintage Arcade Finder API :joystick:
--- 

## Overview 
Vintage Arcade Enthusiasts currently lack a centralized, reliable resource for locating specific classic game cabinets. 
Information on venue locations and machine inventories is often scattered across outdated websites or social media, 
making it difficult for players to find particular games like Ms. Pac-Man or Donkey Kong in their immediate area. 
This project establishes a standardized RESTful Web Service to aggregate and deliver real-time arcade data, connecting 
players with the vintage gaming experiences they seek.

*** This RESTFul Web Service provides endpoints for developers to use to maintain Vintage Arcade info and venue locations in a single place. ***

The Service provides the following:

* Gets all venues in plain text and json
* Gets a venue by id in plain text and json, given the id
* Gets all arcade games across venues in plain text and json
* Gets arcade game by id in plain text and json, given the id
* Adds a venue
* Adds a game
* Updates venues information given by venue id
* Updates cabinets given by cabinet id
* Removes a venue and its associated cabinets along with it
* Removes a cabinet within a venue

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
