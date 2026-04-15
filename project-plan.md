# Project Plan

## Week 9 [First Week]:

1.) **Repo Setup**
- [X] GitHub Repo initiated
- [X] Contributors (team) added
- [X] README added
- [X] Add Team Repo link to Class Project list

2.) **Problem/Solution/Plan Documentation**
- [X] Problem Statement
- [X] Solution Description
- [X] Project Plan
- [X] List of service resources and calls

3.) **Team Charter/Ground Rules**
- [X] Establish common rules for team
- [X] Meeting time/dates
- [X] Work/submission plan

4.) **Choose and Setup Documentation Platform**
- [X] OneNote

---

## Week 10 [Second Week]:

1.) **Data Model & Database Schema**
- [X] Confirm all entities
    - [X] Venue
    - [X] Cabinet
    - [X] Condition
    - [X] Manufacturer
- [X] Create ER diagram
- [X] Set up Database (MySQL)
- [X] Define relationships

2.) **Project Setup**
- [X] Initialize Project (Java/Maven/IntelliJ)
- [X] Set up package structure
    - [X] Controller
    - [X] Entity
    - [X] Dao/Persistence
- [X] Configure dependencies
    - [X] JPA/Hibernate

3.) **Implement Core Entities**
- [X] Create Java classes
    - [X] Venue
    - [X] Cabinet
    - [X] Condition
    - [X] Manufacturer
- [X] Add annotations (@Entity, @Id, etc)

4.) **Build Basic CRUD Endpoints**
- [X] Venues
    - [X] GET /venues
    - [X] GET /venues/(id)
    - [X] POST /venues
    - [X] PUT /venues/(id)
    - [X] DELETE /venues/(id)
- [X] Cabinets
    - [X] GET /cabinets
    - [X] GET /cabinets/(id)
    - [X] POST /cabinets
    - [X] PUT /cabinets/(id)
    - [X] DELETE /cabinets/(id)

5.) **Basic Testing**
- [X] Test endpoints using:
    - [X] Postman or similar
- [X] Verify CRUD functionality

---

## Week 11 [Third Week]:

1.) **Implement Filtering & Search**
- [X] GET /venues/?location=…
- [X] GET /venues/?openNow=true
- [X] GET /cabinets?manufacturer=…
- [X] GET /cabinets?year=…
- [X] GET /cabinets/condition=…

2.) **Data Validation & Error Handling**
- [X] Validate inputs
    - [X] Required fields
    - [X] Proper formats (time, price)
- [X] Add
    - [X] 400 (Bad Request)
    - [X] 404 (Not Found)
    - [X] 500 (Server Error)
- [X] Create global exception handler

3.) **Business Logic Improvements**
- [X] Cascade delete
    - [X] Deleting a venue removes its cabinets
- [X] Prevent invalid updates
    - [X] Cabinet must belong to a valid venue
- [X] Prevent duplicate entries?

4.) **Add Manufacturer Support**
- [X] Manufacturer entity
- [X] Link to Cabinet
- [X] Add filtering
    - [X] ?manufacturer=Atari

5.) **Unit & Integration Testing**
- [X] Write tests
    - [X] Dao
- [X] Ensure good coverage

6.) **Documentation**
- [X] API endpoint documentation
- [X] Example requests/responses

---

## Week 12 [Fourth Week] - PROJECT DUE WEDNESDAY 4/15/26 BY 9PM:

1.) **Final Testing & Debugging**
- [X] Test all endpoints thoroughly
- [X] Fix bugs
- [X] Edge cases
    - [X] Empty results
    - [X] Invalid IDs
    - [X] Deleting linked data

2.) **Complete documentation**
- [X] User-facing documentation
    - [X] What the API does
    - [X] How to use endpoints
- [X] Developer documentation
    - [X] Endpoint list
    - [X] Parameters
    - [X] Sample JSON

3.) **JavaDoc & Code Cleanup**
- [X] Add JavaDoc
    - [X] Classes
    - [X] Methods
- [X] Refactor if necessary
    - [X] Improve readability
    - [X] Remove unused code

4.) **Deploy API**
- [X] Note: Will we deploy via AWS or local?
- [X] Ensure:
    - [X] Public endpoint works
    - [X] API accessible

5.) **GitHub finalization**
- [X] Clean repo structure if needed
- [X] Ensure commit history shows contributions

6.) **Presentation Prep**
- [X] Create presentation/demo
    - [X] Problem
    - [X] Solution
    - [X] Architecture
    - [X] Live demo
- [X] Assign presentation roles
    - [X] Practice timing for presentation

7.) **Team Deliverables**
- [X] Finalize team log/journal
- [X] Contributions summary
- [X] Final check over
