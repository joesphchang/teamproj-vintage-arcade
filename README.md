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


The Arcades API is a RESTful web service designed to allow users and developers to access, manage, and discover information about arcade venues and their game cabinets. The API will provide structured, easy to use endpoint  that expose venue and arcade cabinet data. This service can be used by developers to create applications, such as arcade discovery websites, mobile apps, or game rental management systems. The service will be designed using Java and Maven following RESTful principles, with JSON as the primary data exchange format. This ensures that developers outside the team can easily consume the API in their own applications. 

 

The API provides capabilities for 
1. Viewing Venues – Retrieve a list of arcade venues including name, location, opening and closing times, and the games available.
2. Viewing Arcade Cabinets – Access detailed information about individual games, including manufacturer, year of release, condition, and price per play.
3. Searching and Filtering – Filter venues by location or operating hours, and search for games by name, manufacturer, year, or condition.
4. Managing Data – Allow administrative users to add new venues, update existing arcade cabinets, or remove outdated data. 
--- 

## Resources (Entities)

### Venue

Represents a physical arcade location that contains multiple arcade cabinets.

Property    	   |              Description      	                          |        Type / Format
|---|---|---|
venueId	        |              Unique identifier for the venue	            |         int
name	           |              Name of the arcade	                         |         string
location	       |              Physical address                            |         string
openFrom	       |              Opening time (HH:mm)	                       |         string
openTo	         |              Closing time (HH:mm)	                       |         string
cabinets	       |              List of arcade cabinets at the venue	       |         list



Cabinet

Represents an arcade machine located at a venue.

Property	            |         Description	                                     |  Type / Format
|---|---|---|
gameId	              |         Unique identifier for the game	                  |   int
gameName	            |         Name of the arcade game	                         |   string
manufacturer	        |         Company that made the game	                      |   string
year	                |         Year the game was manufactured	                  |   int
conditionId	         |         Unique identifier for the condition	             |   int
pricePerPlay	        |         Cost to play the game once	                      |   double
venueId	             |         ID of the venue where the game is located	       |   int
manufacturerId       |         (optional)	Unique identifier for the manufacturer|	  int


Condition

Represents the operational condition of an arcade cabinet.

Property	            |         Description	                                       | Type / Format
|---|---|---|
conditionId	         |         Unique identifier for the condition	                | int
status	              | Condition label (e.g., Excellent, Good, Fair, Out of Order)| 	string


Optional Supporting Resources

Manufacturer

Provides additional information about companies that produce arcade machines.

Property	             |         Description	                               |     Type / Format
|---|---|---|
manufacturerId	       |         Unique identifier for the manufacturer	    |      int
name	                 |         Manufacturer name	                         |      string
country	              |         Country of origin	                         |      string
foundedYear	          |         Year the company was founded	              |      int



Resource Relationships
1. A Venue can contain many Cabinets.
2. Each Cabinet belongs to one Venue.
3. Each Cabinet has one Condition.
4. A Cabinet may optionally reference a Manufacturer.



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

