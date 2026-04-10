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
- [ ] Test endpoints using:
    - [ ] Postman or similar
- [ ] Verify CRUD functionality

---

## Week 11 [Third Week]:

1.) **Implement Filtering & Search**
- [ ] GET /venues/?location=…
- [ ] GET /venues/?openNow=true
- [ ] GET /cabinets?manufacturer=…
- [ ] GET /cabinets?year=…
- [ ] GET /cabinets/condition=…

2.) **Data Validation & Error Handling**
- [ ] Validate inputs
    - [ ] Required fields
    - [ ] Proper formats (time, price)
- [ ] Add
    - [ ] 400 (Bad Request)
    - [ ] 404 (Not Found)
    - [ ] 500 (Server Error)
- [ ] Create global exception handler

3.) **Business Logic Improvements**
- [ ] Cascade delete
    - [ ] Deleting a venue removes its cabinets
- [ ] Prevent invalid updates
    - [ ] Cabinet must belong to a valid venue
- [ ] Prevent duplicate entries?

4.) **Add Manufacturer Support**
- [ ] Manufacturer entity
- [ ] Link to Cabinet
- [ ] Add filtering
    - [ ] ?manufacturer=Atari

5.) **Unit & Integration Testing**
- [ ] Write tests
    - [ ] Dao
- [ ] Ensure good coverage

6.) **Documentation**
- [ ] API endpoint documentation
- [ ] Example requests/responses

---

## Week 12 [Fourth Week] - PROJECT DUE WEDNESDAY 4/15/26 BY 9PM:

1.) **Final Testing & Debugging**
- [ ] Test all endpoints thoroughly
- [ ] Fix bugs
- [ ] Edge cases
    - [ ] Empty results
    - [ ] Invalid IDs
    - [ ] Deleting linked data

2.) **Complete documentation**
- [ ] User-facing documentation
    - [ ] What the API does
    - [ ] How to use endpoints
- [ ] Developer documentation
    - [ ] Endpoint list
    - [ ] Parameters
    - [ ] Sample JSON

3.) **JavaDoc & Code Cleanup**
- [ ] Add JavaDoc
    - [ ] Classes
    - [ ] Methods
- [ ] Refactor if necessary
    - [ ] Improve readability
    - [ ] Remove unused code

4.) **Deploy API**
- [ ] Note: Will we deploy via AWS or local?
- [ ] Ensure:
    - [ ] Public endpoint works
    - [ ] API accessible

5.) **GitHub finalization**
- [ ] Clean repo structure if needed
- [ ] Ensure commit history shows contributions

6.) **Presentation Prep**
- [ ] Create slides/demo
    - [ ] Problem
    - [ ] Solution
    - [ ] Architecture
    - [ ] Recorded demo
- [ ] Assign presentation roles
    - [ ] Practice timing for presentation

7.) **Team Deliverables**
- [ ] Finalize team log/journal
- [ ] Contributions summary
- [ ] Final check over
