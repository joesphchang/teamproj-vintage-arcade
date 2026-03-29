# Project Plan

## Week 9 [First Week]:

1.) **Repo Setup**
- [ ] GitHub Repo initiated
- [ ] Contributors (team) added
- [ ] README added
- [ ] Add Team Repo link to Class Project list

2.) **Problem/Solution/Plan Documentation**
- [ ] Problem Statement
- [ ] Solution Description
- [ ] Project Plan
- [ ] List of service resources and calls

3.) **Team Charter/Ground Rules**
- [ ] Establish common rules for team
- [ ] Meeting time/dates
- [ ] Work/submission plan

4.) **Choose and Setup Documentation Platform**
- [ ] OneNote

---

## Week 10 [Second Week]:

1.) **Data Model & Database Schema**
- [ ] Confirm all entities
    - [ ] Venue
    - [ ] Cabinet
    - [ ] Condition
    - [ ] Manufacturer
- [ ] Create ER diagram
- [ ] Set up Database (MySQL)
- [ ] Define relationships
    - [ ] Ex. 1: Venue -> Cabinets (1 to many)
    - [ ] Ex. 2: Cabinet -> Condition (Many to many)

2.) **Project Setup**
- [ ] Initialize Project (Java/Maven/IntelliJ)
- [ ] Set up package structure
    - [ ] Controller
    - [ ] Entity
    - [ ] Dao
- [ ] Configure dependencies
    - [ ] JPA/Hibernate

3.) **Implement Core Entities**
- [ ] Create Java classes
    - [ ] Venue
    - [ ] Cabinet
    - [ ] Condition
    - [ ] Manufacturer
- [ ] Add annotations (@Entity, @Id, etc)

4.) **Build Basic CRUD Endpoints**
- [ ] Venues
    - [ ] GET /venues
    - [ ] GET /venues/(id)
    - [ ] POST /venues
    - [ ] PUT /venues/(id)
    - [ ] DELETE /venues/(id)
- [ ] Cabinets
    - [ ] GET /cabinets
    - [ ] GET /cabinets/(id)
    - [ ] POST /cabinets
    - [ ] PUT /cabinets/(id)
    - [ ] DELETE /cabinets/(id)

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